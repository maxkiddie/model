package com.ydy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydy.constant.SystemConstant;
import com.ydy.utils.ImageUtil;

@Controller
@RequestMapping("code")
public class CodeController {
	private final static Logger log = LoggerFactory.getLogger(CodeController.class);

	@GetMapping("imgCode")
	public void imgCode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = ImageUtil.generateVerifyCode(4);
		request.getSession().setAttribute(SystemConstant.SESSION_CODE, verifyCode);
		log.info(request.getSession().getId() + "生成验证码:" + verifyCode);
		// 生成图片
		try { 
			ImageUtil.outputImage(response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
