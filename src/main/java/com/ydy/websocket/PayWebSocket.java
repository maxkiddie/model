package com.ydy.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

@ServerEndpoint(value = "/confim/{orderId}")
@RestController
public class PayWebSocket {

	private static Logger log = LoggerFactory.getLogger(PayWebSocket.class);
	private static final AtomicInteger OnlineCount = new AtomicInteger(0);
	private static ConcurrentHashMap<Long, Set<Session>> sessionMap = new ConcurrentHashMap<Long, Set<Session>>();// concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
	private static ConcurrentHashMap<String, Long> orderIdMap = new ConcurrentHashMap<String, Long>();// 存储session对应哪个订单ID

	private void addSession(Long orderId, Session session) {
		if (orderId == null)
			return;
		Set<Session> set = sessionMap.get(orderId);
		if (set == null) {
			set = new CopyOnWriteArraySet<Session>();
		}
		set.add(session);
		sessionMap.put(orderId, set);
		orderIdMap.put(session.getId(), orderId);
		OnlineCount.incrementAndGet();
		log.info("开启监听订单{}，当前连接数为：{}", orderId, OnlineCount.get());
	}

	private void removeSession(Session session) {
		Long orderId = orderIdMap.get(session.getId());
		if (orderId == null)
			return;
		Set<Session> set = sessionMap.get(orderId);
		if (set == null) {
			return;
		}
		set.remove(session);
		if (set.isEmpty()) {
			sessionMap.remove(orderId);
		}
		orderIdMap.remove(session.getId());
		OnlineCount.decrementAndGet();
		log.info("关闭监听订单{}，当前连接数为：{}", orderId, OnlineCount.get());
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("orderId") Long orderId) {
		addSession(orderId, session);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		removeSession(session);
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端的消息：{}", message);
		sendMessage(session, "This's from server:" + message);
	}

	/**
	 * 出现错误
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
		error.printStackTrace();
	}

	/**
	 * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
	 * 
	 * @param session
	 * @param message
	 */
	private static void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.error("发送消息出错：{}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 指定Session发送消息
	 * 
	 * @param sessionId
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(Long orderId, String message) {
		if (orderId == null)
			return;
		Set<Session> set = sessionMap.get(orderId);
		if (!CollectionUtils.isEmpty(set)) {
			for (Session s : set) {
				if (s.isOpen()) {
					sendMessage(s, message);
				}
			}
		}
	}

}