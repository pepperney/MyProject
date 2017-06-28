package cn.com.pepper.wechat.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.pepper.common.dao.UserDao;
import cn.com.pepper.common.model.Constant;
import cn.com.pepper.common.model.User;
import cn.com.pepper.exception.MyException;
import cn.com.pepper.util.HttpUtil;
import cn.com.pepper.util.ReturnData;
import cn.com.pepper.wechat.service.WeChatService;
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService{

	@Autowired
	UserDao userMapper;
	
	@Override
	public User selectUserByOpenId(String openId) {
		
		return userMapper.selectUserByOpenId(openId);
	}

	@Override
	public ReturnData<User> saveBindUser(HttpServletRequest request) throws MyException {
		ReturnData<User> rd = new ReturnData<>();
		
		String openId = (String) request.getSession().getAttribute("openid");
		if (openId == null) {
			throw new MyException("maybe a little problem occurred!");
		}
		
		User user = (User) request.getSession().getAttribute(request.getSession().getId());
		
		if (user.getOpenid() != null) {
			throw new MyException("username '" + user.getUsername() + "' hased bind this account!");
		}
		user.setOpenid(openId);
		userMapper.updateByPrimaryKeySelective(user);
		
		rd.setCode(Constant.SUCCEED);
		rd.setMsg("success");
		return rd;
	}
	
	
	@Override
	public int saveUnBindUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(request.getSession().getId());
		return userMapper.weChatUnBind(user);
	}

	@Override
	public User getWeChatUserInfo(HttpServletRequest request) throws MyException {
		User user = (User) request.getSession().getAttribute(request.getSession().getId());
		String openId = (String) request.getSession().getAttribute("openid");
		String token = WeChatUtil.getAccessToken().getAccessToken();
		String url = Constant.GET_USER_INFO_URL.replace("OPENID", openId).replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = HttpUtil.doGet(url);
		user.setNickname((String)jsonObject.get("nickname"));
		user.setHeadimgurl((String)jsonObject.get("headimgurl"));
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}

	

}
