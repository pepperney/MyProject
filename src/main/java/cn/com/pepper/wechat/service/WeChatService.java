package cn.com.pepper.wechat.service;

import javax.servlet.http.HttpServletRequest;

import cn.com.pepper.common.model.User;
import cn.com.pepper.exception.MyException;
import cn.com.pepper.util.ReturnData;

public interface WeChatService {
	
	User selectUserByOpenId(String openId);

	ReturnData<User> saveBindUser(HttpServletRequest request)throws MyException;

	int saveUnBindUser(HttpServletRequest request);
	
	User getWeChatUserInfo(HttpServletRequest request)throws MyException;

	
}
