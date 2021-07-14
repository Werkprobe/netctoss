package com.netnoss.www.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class NetctUtil {
	/**
	 * MD5 Machen
	 * @param str
	 * @return MD5 String
	 * @throws Exception
	 */
	public static String getMd5(String str) throws Exception{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");	
			byte[] output=md.digest(str.getBytes());
			String temp=Base64.encodeBase64URLSafeString(output);
			return URLEncoder.encode(temp,"utf-8");
		}catch (Exception e) {
				throw new Exception("Verschl¨¹sselung fehlgeschlagen",e);
		}
	}
	/**
	 * get UUID Number
	 * @return String
	 */
	public static String createUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static void main(String[] args) throws Exception {
		System.out.print(createUUID());
	}
	
}
