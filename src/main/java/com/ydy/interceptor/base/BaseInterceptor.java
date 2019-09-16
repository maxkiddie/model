/**
 * 
 */
package com.ydy.interceptor.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.ydy.vo.other.BaseVo;

/**
 * @author xuzhaojie
 *
 *         2018年11月12日 下午4:51:38
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
	protected static void handleResponse(HttpServletResponse response, BaseVo vo) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		PrintWriter out = null;
		out = response.getWriter();
		out.append(JSONObject.toJSONString(vo));
		out.flush();
		out.close();
	}

	protected static Object getSessionAttr(HttpServletRequest request, String key) throws IOException {
		return request.getSession().getAttribute(key);
	}

	protected static void setSessionAttr(HttpServletRequest request, String key, Object value) throws IOException {
		request.getSession().setAttribute(key, value);
	}

	protected static void removeSessionAttr(HttpServletRequest request, String key) throws IOException {
		request.getSession().removeAttribute(key);
	}

}
