package cn.com.pepper.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class MyExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		logger.error("Catch Exception: ", ex);
		String message = null;
		MyException customException = null;
		if (ex instanceof MyException) {
			customException = (MyException) ex;
		} else {
			customException = new MyException("未知错误！");
		}
		message = customException.getMessage();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("code", -1);
		jsonObj.put("msg", message);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.append(jsonObj.toJSONString());
		return null;
	}

}
