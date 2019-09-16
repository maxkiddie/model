/**
 * 
 */
package com.ydy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ydy.mapper.base.BaseMapper;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author xuzhaojie
 *
 *         2018年11月8日 下午3:37:23
 */
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = { "com.ydy.mapper" }, markerInterface = BaseMapper.class)
public class ModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelApplication.class, args);
	}

}
