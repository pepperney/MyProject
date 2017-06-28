package cn.com.pepper.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.pepper.common.model.User;
/**
 * 自定义拦截器，拦截未登录的请求
 * @Description 
 * @author niepei
 * @date 2017年6月23日 下午4:30:26 
 * @version V1.3.1
 */
public class MyInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

//		logger.debug("---------------------------------------------------------> MyInterceptor has begin  ");
		boolean  flag = false;
		User user = (User) request.getSession().getAttribute(request.getSession().getId());
		if (null != user) {
			flag = true;
		} else {
			logger.debug("-----------> can't find user in session,maybe you are not login!");
			response.sendRedirect("/MyProject/login.html");//如果用户未登录，跳转到登录页
//			this.returnErrorInfo(response);//如果如果用户未登录，返回错误信息
		}
//		logger.debug("---------------------------------------------------------> MyInterceptor has end  ");
		return flag;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {

	}

	@SuppressWarnings("unused")
	private void returnErrorInfo(HttpServletResponse response)throws IOException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("code", -1);
		jsonObj.put("msg", "please login ！");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(jsonObj.toJSONString());
	}

}
