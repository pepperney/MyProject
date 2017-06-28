package cn.com.pepper.wechat.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import cn.com.pepper.exception.MyException;

public interface MessageService {
	/**
	 * 
	 * @Description 微信接入验证校验方法
	 * @author niepei 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public boolean checkSignature(String signature, String timestamp, String nonce);
	
	/**
	 * @Description 接收用户消息并回复
	 * @author niepei 
	 * @param request
	 * @throws MyException
	 * @throws IOException
	 */
	public String handleMessage(HttpServletRequest request);
}
