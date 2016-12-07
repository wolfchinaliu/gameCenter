package com.shiliu.game.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Auth popl_lu
 * @Date 2016年7月13日 上午10:57:43
 * @authEmail popl_lu@sian.cn
 * @CalssName com.shiliu.game.utils.StringUtils
 * @dec String 的工具类
 */
public class StringUtils {
	/**
	 * 判断null 或者 进行trim的空串
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

	/**
	 * 判断null 或者 进行trim的空串 或者内容为NULL的字符串
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNULL(String s) {
		return isEmpty(s) || "NULL".equals(s.toUpperCase());
	}

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/***
	 * 判断时候为数值 这里把0 和0开头的数值去除了
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (str.matches("^[1-9]+[0-9]*$") || str.matches("^[1-9]+[0-9]*\\.[0-9]+$")) {
			return true;
		} else {
			return false;
		}
	}

	/***
	 * 随机获取des密钥
	 * @return
	 */
	public static String randomDesKey(){
		String keySource = "0123456789!@#$%^&*()QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?/qwertyuiopasdfghjklzxcvbnm,.';[]";
		char[] keyArray = new char[8];
		for(int i = 0;i < 8;i++){
			int a = (int)((Math.random()) * keySource.length());
			keyArray[i] = keySource.charAt(a);
		}
		return new String(keyArray);
	}
	public static void main(String[] args) {
		System.out.println(isDouble(""));
		System.out.println(isDouble("0"));
		System.out.println(isDouble("0.0"));
		System.out.println(isDouble("123"));
		System.out.println(isDouble(".3"));
		System.out.println(isDouble("3.3"));
		System.out.println(isDouble("3."));
		System.out.println(Integer.parseInt("012"));
		for(int i = 0;i < 10;i++){
			System.out.println(randomDesKey());
		}
	}
}
