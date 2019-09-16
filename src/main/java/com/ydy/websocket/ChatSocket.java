/**
 * 
 */
package com.ydy.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuzhaojie
 *
 *         2019年6月20日 下午3:01:19
 */
@ServerEndpoint("/chat/{name}")
@RestController
public class ChatSocket {
	// 用来记录当前连接数的变量
	private static volatile int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
	private static CopyOnWriteArraySet<ChatSocket> webSocketSet = new CopyOnWriteArraySet<ChatSocket>();

	// 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
	private Session session;

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatSocket.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("name") String name) throws Exception {
		this.session = session;
		System.out.println(this.session.getId());
		webSocketSet.add(this);
		LOGGER.info("Open a websocket. name={}", name);
	}

	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		LOGGER.info("Close a websocket. ");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		LOGGER.info("Receive a message from client: " + message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		LOGGER.error("Error while websocket. ", error);
	}

	public void sendMessage(String message) throws Exception {
		if (this.session.isOpen()) {
			this.session.getBasicRemote().sendText("Send a message from server. ");
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		ChatSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		ChatSocket.onlineCount--;
	}
}
