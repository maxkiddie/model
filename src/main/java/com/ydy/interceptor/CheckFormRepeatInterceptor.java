/**
 * 
 */
package com.ydy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.ydy.annotation.CheckFormRepeat;
import com.ydy.ienum.EnumSystem;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ResultVo;

/**
 * 检查表单重复提交拦截器
 * 
 * @author xuzhaojie
 *
 *         2018年12月26日 下午4:04:07
 */
@Component
public class CheckFormRepeatInterceptor extends BaseInterceptor {

	private final static Logger log = LoggerFactory.getLogger(CheckFormRepeatInterceptor.class);

	private final static String KEY = "F_K";

	@Value("${timeout:5000}")
	private Long timeout;// 5秒不能重复提交

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
			CheckFormRepeat an = m.getMethod().getAnnotation(CheckFormRepeat.class);
			if (an == null) {
				return true;
			}
			String key = KEY + request.getRequestURI();// 作为键
			Long time = (Long) getSessionAttr(request, key);
			log.info(key + ":" + time);
			if (isTimeout(time)) {
				setSessionAttr(request, key, System.currentTimeMillis());
				return true;
			} else {
				log.info("重复提交表单:" + request.getRequestURI());
				BaseVo vo = new ResultVo(EnumSystem.DATA_REPEAT);
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
		return System.currentTimeMillis() - time > timeout;
	}
}
