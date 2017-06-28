package cn.com.pepper.common.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.pepper.common.dao.UserDao;
import cn.com.pepper.common.model.User;
import cn.com.pepper.common.service.LoginService;
import cn.com.pepper.util.MD5Util;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public User login(HttpServletRequest request) {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Util.encrypt(username, password);
		logger.debug("--------------------> " + password);
		User user = userDao.selectByUsernameAndPassword(username,password,null);
		logger.debug("--------------------> " + user);
		return user;

	}
	
	@Override
	public String register(User user) {
        String message = null;
        String password = MD5Util.encrypt(user.getUsername(), user.getPassword());
		logger.debug("--------------------> " + password);
		user.setPassword(password);
		User existUser = userDao.selectByUsernameAndPassword(user.getUsername(), user.getPassword(),null);
		if (existUser != null) {
			message = "username '" + user.getUsername() + "' has been occupied,please change another";
		}
		else {
			user.setUsertype(1);
			user.setUserstatus(1);
			message = userDao.insertSelective(user)>0 ? "success" : "fail";
		}
		return message;
	}

	

}
