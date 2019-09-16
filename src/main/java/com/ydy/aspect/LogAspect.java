/**
 * 
 */
package com.ydy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xuzhaojie
 *
 *         2018年11月29日 下午5:56:41
 */
@Aspect // 作用是把当前类标识为一个切面供容器读取
@Component
public class LogAspect {

	private final static Logger log = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选)) 修饰符 通配符(*表示所有，可定义void\String
	 * 返回值为具体类型的方法) 包名 类名 方法名 参数 定义AOP扫描的路径，第一个路径只扫描controller中的一个方法，两个点表示任何参数
	 */
	@Pointcut("execution(public * com.ydy.controller.*.*(..))")
	public void log() {
	}

	/**
	 * 获取返回内容
	 * 
	 * @param object
	 */
	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturn(Object object) {
		// ServletRequestAttributes attributes = (ServletRequestAttributes)
		// RequestContextHolder
		// .getRequestAttributes();
	}

	/**
	 * 切面方法,记录日志
	 * 
	 * @return
	 * @throws Throwable
	 */
	@Around("log()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();// 1、开始时间
		// 访问目标方法的参数 可动态改变参数值
		// Object[] args = joinPoint.getArgs();
		// 方法名获取
		String str = joinPoint.getSignature().getDeclaringType().getSimpleName() + "."
				+ joinPoint.getSignature().getName();
		log.info(str + "——执行");
		// 调用实际方法
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Exception e) {
			throw e;
		}
		// 获取执行的方法
		log.info(str + "结束了, 耗时：{} ms", System.currentTimeMillis() - beginTime);
		return object;
	}

}
