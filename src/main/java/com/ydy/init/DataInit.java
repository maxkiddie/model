/**
 * 
 */
package com.ydy.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ydy.configuration.SystemConfig;
import com.ydy.constant.SystemConstant;

/**
 * @author xuzhaojie
 *
 *         2019年1月21日 下午5:07:55
 */
@Component // 到时候去掉注释即可用
public class DataInit implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(DataInit.class);

	@Autowired
	private SystemConfig systemConfig;

	@Override
	public void run(String... args) throws Exception {
		log.info("DataInit开始初始化...");
		SystemConstant.HTTP_HOST = systemConfig.getImgHttpurl();
		SystemConstant.FROM_EMAIL = systemConfig.getFromMail();
		log.info("DataInit初始化成功...");
	}

}
