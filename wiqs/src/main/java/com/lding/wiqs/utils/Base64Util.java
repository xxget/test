package com.lding.wiqs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>Title:Base64Util</p>
 * <p>Description: Base64加密方法</p>
 * @author xixuguang
 * @date 2017年4月18日 上午11:18:03
 *
 */
public class Base64Util {

	/**
	 * 加密
	 * @param binaryData
	 * @return
	 */
	public static String encode(byte[] binaryData) {
		try {
			return new String(Base64.encodeBase64(binaryData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密
	 * @param binaryData
	 * @return
	 */
	public static String encode(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] binaryData = new byte[is.available()];
			is.read(binaryData);
			is.close();
			
			return new String(Base64.encodeBase64(binaryData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 解密
	 * @param base64Str
	 * @return
	 */
	public static byte[] decode(String base64Str) {
		try {
			return Base64.decodeBase64(base64Str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串加密
	 * @param str
	 * @return str
	 */
	public static String getBase64(String str){
		byte[] b = null;
		String s = null;
		
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(b != null){
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}
	
	/**
	 * 字符串解密
	 * @param str
	 * @return str
	 */
	public static String getFromBase64(String s){
		byte[] b = null;
		String result = null;
		if(s != null){
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(encode("c:/a.JPG"));
		System.out.println(getBase64("c:/a.JPG"));
		System.out.println(getBase64("abc"));
		System.out.println(getFromBase64("NjY2"));
	}
}
