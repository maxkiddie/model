/**
 * 
 */
package com.ydy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ydy.interceptor.CheckFormRepeatInterceptor;
import com.ydy.interceptor.DownloadInterceptor;
import com.ydy.interceptor.ParamInterceptor;

/**
 * 拦截器配置
 * 
 * @author xuzhaojie
 *
 *         2018年11月12日 下午4:07:07
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	@Autowired
	private ParamInterceptor paramInterceptor;

	@Autowired
	private CheckFormRepeatInterceptor checkFormRepeatInterceptor;
	@Autowired
	private DownloadInterceptor downloadInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 这里可以添加多个拦截器
		registry.addInterceptor(checkFormRepeatInterceptor).addPathPatterns("/**");
		registry.addInterceptor(paramInterceptor).addPathPatterns("/**");
		registry.addInterceptor(downloadInterceptor).addPathPatterns("/**");
	}

}