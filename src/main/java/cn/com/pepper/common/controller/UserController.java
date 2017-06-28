package cn.com.pepper.common.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.common.model.User;
import cn.com.pepper.common.service.UserService;
import cn.com.pepper.exception.MyException;
import cn.com.pepper.util.ReturnData;

@RestController
@RequestMapping(value="/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 更新个人信息
	 * @Description 
	 * @author niepei
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
	public ReturnData<String> updateUserInfo(@RequestBody User user,HttpSession session) throws MyException {
		logger.debug("-----------------  updateUserInfo is start    -----------------");
		ReturnData<String> rd = new ReturnData<String>();
		user.setUserid(((User) session.getAttribute(session.getId())).getUserid());
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("update succeed!");
		rd.setTotal(userService.updateUserInfo(user));
		logger.debug("-----------------  updateUserInfo is end      -----------------");
		return rd;
	}
	
	/**
	 * 批量更新
	 * @Description 
	 * @author niepei
	 * @param userList
	 * @return
	 * @throws MyException
	 */
	@RequestMapping(value="/updateBatch",method = RequestMethod.POST)
	public ReturnData<String> updateBatch(@RequestBody List<User> userList) throws MyException {
		logger.debug("-----------------  updateBatch is start    -----------------");
		ReturnData<String> rd = new ReturnData<String>();
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("update succeed!");
		rd.setTotal(userService.updateBatch(userList));
		logger.debug("-----------------  updateBatch is end      -----------------");
		return rd;
	}
}
