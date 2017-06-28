package cn.com.pepper.common.service;

import java.util.List;

import cn.com.pepper.common.model.User;
import cn.com.pepper.exception.MyException;

public interface UserService {

	int updateUserInfo(User user)throws MyException;

	int updateBatch(List<User> userList)throws MyException;

}
