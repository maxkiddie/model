/**
 * 
 */
package com.ydy.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSONObject;
import com.ydy.annotation.CtrlParam;
import com.ydy.ienum.EnumSystem;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.vo.other.ErrorVo;

/**
 * 控制器参数拦截器
 * 
 * @author xuzhaojie
 *
 *         2018年11月12日 下午4:04:07
 */
@Component
public class ParamInterceptor extends BaseInterceptor {

	private final static Logger log = LoggerFactory.getLogger(ParamInterceptor.class);

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
			// 用java原来的反射是反射不出参数名称，现在依靠spring的工具来获取(备注：java8编译器可以，不过默认是关闭的)
			LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
			String[] paramNames = u.getParameterNames(m.getMethod());// 获取参数的具体名称
			MethodParameter[] mp = m.getMethodParameters();// 用jdk自带反射出参数对象数组
			int i = 0;// 数组坐标
			ErrorVo errorVo = null;
			for (MethodParameter p : mp) {// 循环数组
				CtrlParam annotation = p.getParameterAnnotation(CtrlParam.class);// 判断该参数上是否有这个注解
				if (annotation != null) {// 如果注解不为空
					String value = request.getParameter(paramNames[i]);// 根据参数名称从request获取请求参数值
					if (value == null || "".equals(value)) {// 判断request参数值是否为空
						if (errorVo == null) {
							errorVo = new ErrorVo();
							errorVo.setErrorEnum(EnumSystem.PARAM_ERROR);
						}
						if ("".equals(annotation.value())) {
							errorVo.putError(paramNames[i], paramNames[i] + "不能为空;");
						} else {
							errorVo.putError(paramNames[i], annotation.value() + "(" + paramNames[i] + ")不能为空;");
						}
						log.error(request.getRequestURI().toString() + "->" + paramNames[i] + "不能为空");
					}
				}
				i++;
			}
			if (errorVo != null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				PrintWriter out = null;
				out = response.getWriter();
				out.append(JSONObject.toJSONString(errorVo));
				return false;
			}
			return true;
		} else {
			return true;
		}
	}

}
