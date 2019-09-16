/**
 * 
 */
package com.ydy.controller.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alibaba.fastjson.JSON;
import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.RemoteApiException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumSystem;
import com.ydy.utils.DateUtil;
import com.ydy.vo.email.EmailVo;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ErrorVo;
import com.ydy.vo.other.ResultVo;

/**
 * 控制器全局处理器
 * 
 * @author xuzhaojie
 *
 *         2019年5月12日 上午8:58:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@Value("${img_httpurl}")
	private String imgHttpurl;

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, BusinessException e) {
		ResultVo vo = new ResultVo();
		vo.setErrorEnum(e.getErrorEnum());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = DataNotFoundException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, DataNotFoundException e) {
		ResultVo vo = new ResultVo();
		vo.setErrorEnum(e.getErrorEnum());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vo);
	}

	@ExceptionHandler(value = ValidateException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, ValidateException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.DATA_NOT_MATCH);
		vo.setErrors(e.getErrors());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = RemoteApiException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, RemoteApiException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.REMOTE_IP_ERROR);
		vo.putError("error", e.getMessage());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.PARAM_FORMAT_ERROR);
		vo.putError("error", e.getMessage());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.PARAM_FORMAT_ERROR);
		vo.putError("error", e.getMessage());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, HttpMediaTypeNotAcceptableException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.PARAM_FORMAT_ERROR);
		vo.putError("error", e.getMessage());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler(value = IOException.class)
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionMyHandler(HttpServletRequest req, IOException e) {
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.NET_IO_ERROR);
		vo.putError("error", e.getMessage());
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<BaseVo> exceptionHandler(HttpServletRequest req, Exception e) {
		String requestURI = req.getRequestURI();
		e.printStackTrace();
		String msg = requestURI + "出现异常:" + e.getMessage();
		log.error(msg);
		EmailVo emailVo = new EmailVo();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		emailVo.setContent(msg + ":" + sw.toString());
		emailVo.setEmail("531592003@qq.com");
		emailVo.setTitle(imgHttpurl + "异常日志" + DateUtil.getCurrentTimeFullStr());
		ErrorVo vo = new ErrorVo();
		vo.setErrorEnum(EnumSystem.SYSTEM_ERROR);
		vo.putError("error", msg);
		log.error(JSON.toJSONString(vo));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(vo);
	}
}
