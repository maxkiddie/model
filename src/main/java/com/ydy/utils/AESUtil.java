package com.ydy.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {
	private static final String KEY = createKey("xujiachengxuzhaojie");

	/**
	 * 获取字符串形式的AES密钥
	 *
	 * @param password 指定随机源的种子
	 * @return 字符串密钥
	 */
	public static String createKey(String password) {
		try {
			// 指定加密算法的名称为AES
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			// 初始化密钥生成器，指定密钥的长度为128(单位:bit),
			SecureRandom secureRandom = new SecureRandom(password.getBytes());
			keyGenerator.init(128, secureRandom);
			// 生成原始对称密钥
			SecretKey secretKey = keyGenerator.generateKey();
			// 返回编码格式的密钥
			byte[] enCodeFormat = secretKey.getEncoded();
			// 将编码格式的密钥进行Base64编码，方便进行保存
			return Base64.encodeBase64String(enCodeFormat);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 明文加密
	 *
	 * @param strKey  string格式的密钥
	 * @param content 加密前的原内容
	 * @return 密文
	 */
	public static String encrypt(String content) {
		try {
			// 生成密钥
			SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(KEY.getBytes()), "AES");
			// 根据指定算法生成密码器
			Cipher ciper = Cipher.getInstance("AES");
			// 初始化密码器，加密(ENCRYPT_MODE),解密(DECRYPT_MODE)
			ciper.init(Cipher.ENCRYPT_MODE, key);
			// 获取加密内容的字节数组
			byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
			byte[] aesBytes = ciper.doFinal(contentBytes);
			// 将加密后的密文转为字符串返回
			return Base64.encodeBase64String(aesBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 密文解密
	 *
	 * @param strKey  string密钥
	 * @param content 加密后的内容
	 * @return 明文
	 */
	public static String decrypt(String content) {
		try {
			// 生成密钥
			SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(KEY.getBytes()), "AES");
			// 根据指定算法生成密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 初始化密码器，加密(ENCRYPT_MODE),解密(DECRYPT_MODE)
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 先将密文进行Base64解码
			byte[] aesBytes = Base64.decodeBase64(content);
			// 将密文进行解密
			byte[] contentBytes = cipher.doFinal(aesBytes);
			// 将解密后的内容转成字符串并返回
			return new String(contentBytes, StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encodeBase64(String str) {
		return Base64Util.encode(encrypt(str));
	}

	public static String decodeBase64(String str) {
		return decrypt(Base64Util.decode(str));
	}

}
