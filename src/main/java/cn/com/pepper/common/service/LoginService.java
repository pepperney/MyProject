package cn.com.pepper.common.service;

import javax.servlet.http.HttpServletRequest;

import cn.com.pepper.common.model.User;

public interface LoginService {

	public User login(HttpServletRequest request);
	
	public String register(User user);

}
