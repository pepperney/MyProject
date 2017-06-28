package cn.com.pepper.util;

import java.util.UUID;

//下面就是实现为数据库获取一个唯一的主键id的代码

public class UUIDGenerator {

	public UUIDGenerator() {
		
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {

		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		String returnString = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);

		return returnString;
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {

		if (number < 1) {

			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}

		return ss;
	}

	public static void main(String[] args) {

		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}

	}
}
