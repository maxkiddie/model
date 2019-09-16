/**
 * 
 */
package com.ydy.controller.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ydy.constant.SystemConstant;
import com.ydy.exception.BusinessException;
import com.ydy.ienum.EnumSystem;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 上午9:18:24
 */
public abstract class BaseController {
	private final static Logger log = LoggerFactory.getLogger(BaseController.class);



	public boolean compareCode(Object code, HttpServletRequest request) {
		if (code == null) {
			return false;
		}
		if (SystemConstant.DEFAULT_CODE.equalsIgnoreCase(code.toString())) {
			return true;
		}
		Object myCode = request.getSession().getAttribute(SystemConstant.SESSION_CODE);
		if (myCode == null) {
			log.error(request.getSession().getId() + "系统内部无生成code");
			throw new BusinessException(EnumSystem.CODE_PRD_ERROR);
		}
		log.info(request.getSession().getId() + "=>code:" + code + ",系统内部code:" + myCode);
		return code.toString().equalsIgnoreCase(myCode.toString());
	}

	public void removeCode(HttpServletRequest request) {
		request.getSession().removeAttribute(SystemConstant.SESSION_CODE);
	}

	protected static Object getSessionAttr(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	protected static void setSessionAttr(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	protected static void removeSessionAttr(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	/**
	 * 设置http响应头，下载文件
	 * 
	 * @param filename
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	protected void setDownloadFile(String filename, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setContentType("application/force-download");// 设置强制下载不打开
		// 获得请求头中的User-Agent
		String agent = request.getHeader("User-Agent");
		// 根据不同浏览器进行不同的编码
		String filenameEncoder = "";
		if (agent.contains("MSIE")) {
			// IE浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			filenameEncoder = "=?utf-8?B?" + Base64.getEncoder().encodeToString(filename.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
		}
		response.addHeader("Content-Disposition", "attachment;fileName=" + filenameEncoder);// 设置文件名
	}
}
