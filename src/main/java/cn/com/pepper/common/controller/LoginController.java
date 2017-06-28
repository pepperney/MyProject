package cn.com.pepper.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.common.model.User;
import cn.com.pepper.common.service.LoginService;
import cn.com.pepper.util.ReturnData;

@Controller
public class LoginController {



	private  Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;
	/**
	 * 登录
	 * @Description 
	 * @author niepei
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		logger.debug("-----------------  login is start    -----------------");
		
		User user = loginService.login(request);
		String URL = request.getRequestURL().toString();
		if (user!= null) {
			request.getSession().setAttribute(request.getSession().getId(), user);//登陆成功后将用户放入session
//			URL = URL.replace("login", "html/main.html");
			URL = URL.replace("login", "seckill/list");
		}
		else {
			URL = URL.replace("login", "login.html");
		}
		logger.debug("-----------------  login is end      -----------------");
		return new ModelAndView("redirect:"+URL);
	}
	
	/**
	 * 注册
	 * @Description 
	 * @author niepei
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public @ResponseBody ReturnData<String> register(@RequestBody User user) throws Exception {
		logger.debug("-----------------  register is start    -----------------");
		ReturnData<String> rd = new ReturnData<String>();
		String message = loginService.register(user);
		if (message.equals("success")) {
			rd.setCode(Constant.SUCCEED);
			rd.setMsg("register succeed!");
		}
		else {
			rd.setCode(Constant.FAILED);
			rd.setMsg("register failed! " + message);
		}
		logger.debug("-----------------  register is end      -----------------");
		return rd;
	}
	/**
	 * 注销
	 * @Description 
	 * @author niepei
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public @ResponseBody void logout(HttpServletRequest request) throws Exception {
		logger.debug("-----------------  logout is start    -----------------");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("user");
		}
		logger.debug("-----------------  logout is end      -----------------");
		
	}
}
