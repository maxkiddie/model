package com.ydy.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

	@Value("${img_httpurl}")
	private String imgHttpurl;

	@Value("${mail_username}")
	private String fromMail;

	public String getImgHttpurl() {
		return imgHttpurl;
	}

	public void setImgHttpurl(String imgHttpurl) {
		this.imgHttpurl = imgHttpurl;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

}
