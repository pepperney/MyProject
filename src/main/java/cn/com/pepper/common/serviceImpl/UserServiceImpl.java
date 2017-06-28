package cn.com.pepper.common.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.pepper.common.dao.UserDao;
import cn.com.pepper.common.model.User;
import cn.com.pepper.common.service.UserService;
import cn.com.pepper.exception.MyException;

@Service("userService")
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao userDao;

	@Override
	public int updateUserInfo(User user) throws MyException {
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateBatch(List<User> userList) throws MyException {
		
		return userDao.updateBatch(userList);
	}

}
