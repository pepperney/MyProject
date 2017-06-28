package cn.com.pepper.util;

public class RandomStrGenerator {

	/**
	 * 生成随机字符串
	 * @Description 
	 * @author niepei
	 * @param size 要生成的字符串长度
	 * @return
	 */
	private static String getRandomStr(int size){
		final String str  = "0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			int index = (int) Math.round((Math.random()*(str.length()-1)));
			sb.append(str.charAt(index));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRandomStr(7));
	}


	


}
