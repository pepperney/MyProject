package cn.com.pepper.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.com.pepper.common.model.Constant;
import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
public final class MD5Util {

	
	public static String encrypt(String username, String password) {

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			password = username + password + Constant.LOGIN_SALT;
			password = base64en.encode(md5.digest(password.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return password;

	}

	public static void main(String rsg[]){

		String username = "pepper";
		String password = "123456";
		password = encrypt(username, password);
		System.out.println(password);
	}

}
