package cn.com.learn.designPattern.P05__Prototype;

import java.util.Random;


/**
 * 
 * @Description:
 * 该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象
 * 一个原型类，只需要实现Cloneable接口，覆写clone方法,重点是super.clone()这句话，
 * super.clone()调用的是Object的clone()方法，而在Object类中，clone()是native的
 *   
 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。(被复制对象的内部其他引用类型的变量不会被复制（除了String）)
 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底
 * 
 * @author  pepper
 * @date 2016-7-27
 */
public class Client {

	private static int MAX_COUNT = 7;

	private static String getRandomString(int maxLength) {
		String source = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(source.charAt(random.nextInt(source.length())));
		}
		return sb.toString();
	}

	private static void sendMail(Mail cloneMail) {
		System.out.println("title: " + cloneMail.getTitle() + "\t receiver: "
				+ cloneMail.getReceiver() + "\t\tsend succeed!");

	}

	/**
	 * 
	 * @Description:  mail.clone方法将原有对象复制一份，产生一个新的对象然后再去修改细节
	 * @param  args
	 * @return 
	 * @throws CloneNotSupportedException  
	 * @author pepper
	 * @date 2016-7-26
	 */
	public static void main(String[] args) throws CloneNotSupportedException {

		int i = 0;
		Mail mail = new Mail(new AdvertisemeTemplate());
		mail.setSender("oracle@163.com");
		while (i < MAX_COUNT) {
			Mail cloneMail = mail.clone();
			cloneMail.setAppellation("Mr(Miss)" + getRandomString(5));
			cloneMail.setReceiver(getRandomString(8) + "@" + getRandomString(5)	+ ".com");
			sendMail(cloneMail);
			i++;
		}
	}

}
