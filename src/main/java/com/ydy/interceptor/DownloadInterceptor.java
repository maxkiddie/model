package com.ydy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.ydy.annotation.Download;
import com.ydy.ienum.EnumSystem;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ResultVo;

/**
 * 下载拦截器
 * 
 *
 */
@Component
public class DownloadInterceptor extends BaseInterceptor {
	private final static Logger log = LoggerFactory.getLogger(DownloadInterceptor.class);

	private final static String KEY = "D_L";

	@Value("${downloadTimeout:10000}")
	private Long downloadTimeout;// 5秒不能重复提交

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
			Download an = m.getMethod().getAnnotation(Download.class);// 查看该方法有无权限注解
			if (an == null) {// 没有该注解，放行
				return true;
			}
			String key = KEY + request.getRequestURI();// 作为键
			Long time = (Long) getSessionAttr(request, key);
			log.info(key + ":" + time);
			if (isTimeout(time)) {
				setSessionAttr(request, key, System.currentTimeMillis());
				return true;
			} else {
				log.info("文件下载过快:" + request.getRequestURI());
				BaseVo vo = new ResultVo(EnumSystem.DOWNLOAD_TO_FAST);
				handleResponse(response, vo);
				return false;
			}
		} else {
			return true;
		}
	}

	private boolean isTimeout(Long time) {// 已经过了时间
		if (time == null)
			return true;
		return System.currentTimeMillis() - time > downloadTimeout;
	}
}
