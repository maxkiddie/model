/**
 * 
 */
package com.ydy.utils;

import java.io.File;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuzhaojie
 *
 *         2018年12月17日 上午10:13:04
 */
public class PathUtil {
	private final static Logger log = LoggerFactory.getLogger(PathUtil.class);

	public static String getPath() {
		String filePath = PathUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			filePath = URLDecoder.decode(filePath, "utf-8");// 转化为utf-8编码
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (filePath.contains("sale")) {
			// 截取路径中的jar包名
			filePath = filePath.substring(0, filePath.lastIndexOf("sale"));
		}
		File file = new File(filePath);
		return file.getPath().replace("file:", "") + File.separator;
	}
}
