package cn.com.pepper.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.common.model.User;
import cn.com.pepper.exception.MyException;
import cn.com.pepper.util.HttpUtil;
import cn.com.pepper.util.ReturnData;
import cn.com.pepper.wechat.model.AccessToken;
import cn.com.pepper.wechat.service.MessageService;
import cn.com.pepper.wechat.service.WeChatService;

@Controller
public class WeChatController {

	private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

	@Autowired
	private MessageService messageService;
	@Autowired
	private WeChatService weChatService;

	/**
	 * 
	 * @Description
	 * @author niepei 微信后台接入验证
	 * @param request
	 * @param response
	 * @throws MyException
	 * @throws IOException
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	public void getMethod(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {
		logger.debug("------------------------------------------------------>>   wechat-get  has start");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (messageService.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		logger.debug("------------------------------------------------------>>   wechat-get  has end");
	}

	/**
	 * @Description 接收用户消息并回复
	 * @author niepei
	 * @param request
	 * @param response
	 * @throws MyException
	 * @throws IOException
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.POST)
	public void postMethod(HttpServletRequest request, HttpServletResponse response) throws MyException, IOException {
		logger.debug("------------------------------------------------------>>   wechat-post  has start");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.print(messageService.handleMessage(request));
		} finally {
			out.close();
		}
		logger.debug("------------------------------------------------------>>   wechat-post  has end");
	}
	
	
	@RequestMapping(value = "/index")
	public ModelAndView  index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		logger.debug("------------------------------------------------------>   getOpenId  has start");
		String code = request.getParameter("code");
		logger.debug("------------------------------------------------------>   code:" + code);
		String state = request.getParameter("state");
		logger.debug("------------------------------------------------------>   state:" + state);
		String url = Constant.GET_ACCESS_TOKEN_URL.replace("CODE", code);
		JSONObject jsonObject = HttpUtil.doGet(url);
		if (jsonObject.get("errcode") != null) {
			logger.debug("------------------------------------------------------>   something is wrong :" + jsonObject.getString("errmsg"));
		} else {
			AccessToken token = new AccessToken();
			token.setAccessToken(jsonObject.getString("access_token"));
			token.setExpiresIn(Integer.parseInt(jsonObject.getString("expires_in")));
			token.setOpenid(jsonObject.getString("openid"));
			token.setScope(jsonObject.getString("scope"));
			logger.debug("------------------------------------------------------>   token:" + token.getAccessToken());
			request.getSession().setAttribute("openId", jsonObject.getString("openid"));
			User user = weChatService.selectUserByOpenId(jsonObject.getString("openid"));
			if(user==null){
				mav = new ModelAndView("redirect:"+"http://1.pepper.applinzi.comhtml/register.html");
			}else{
				mav = new ModelAndView("redirect:"+"http://1.pepper.applinzi.comhtml/main.html");
			}	
		}
		logger.debug("------------------------------------------------------>   getOpenId  has end");
		return mav;
	}
	
	
	
	@RequestMapping(value = "/wechatBind")
	public @ResponseBody ReturnData<User> wechatBind(HttpServletRequest request){
		logger.debug("------------------------------------------------------>   wechatBind  has start"); 
		ReturnData<User> rd = weChatService.saveBindUser(request);
		logger.debug("------------------------------------------------------>   wechatBind  has start");
		return rd;
	}
	
	@RequestMapping(value = "/wechatUnBind")
	public @ResponseBody ReturnData<User> wechatUnBind(HttpServletRequest request){
		logger.debug("------------------------------------------------------>   wechatUnBind  has start"); 
		ReturnData<User> rd = new ReturnData<>();
		weChatService.saveUnBindUser(request);
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("success");
		logger.debug("------------------------------------------------------>   wechatUnBind  has start");
		return rd;
	}
	
	
	@RequestMapping(value = "/getWeChatUserInfo")
	public @ResponseBody ReturnData<User> getWeChatUserInfo(HttpServletRequest request){
		logger.debug("------------------------------------------------------>   getWeChatUserInfo  has start"); 
		ReturnData<User> rd = new ReturnData<>();
		User user = weChatService.getWeChatUserInfo(request);
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("success");
		rd.setData(user);
		logger.debug("------------------------------------------------------>   getWeChatUserInfo  has start");
		return rd;
	}
}
