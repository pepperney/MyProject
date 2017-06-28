package cn.com.learn.jdk8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnRegix {
	public static void main(String[] args) {

		String str = "a little boy whose age is 5 years old and phone number is 133 5678 9562!";
        // 以下正则表达式表示匹配一个手机号码： \s 表示一个空格，\d表示数字，{3}表示前面的内容连续出现的次数
		String regix = "\\s\\d{3}\\s\\d{4}\\s\\d{4}";
		// Pattern类的对象p表示编译后的正则表达式，compile()方法内的参数为正则表达式的内容
		Pattern p = Pattern.compile(regix);
		// matcher()方法返回一个Matcher对象
		Matcher m = p.matcher(str);
		while (m.find()) {
			//group()方法返回与正则表达式匹配的内容
			System.out.println(m.group());
			//start()方法返回匹配的起始位置
			System.out.println(m.end()-1-m.start());
		}
	}
}
 