
package cn.com.learn.designPattern.P03__Singleton;

public class Client {
public static void main(String[] args) {
	
	for (int i = 0; i < 3; i++) {
		Singleton single = Singleton.getInstance();
		single.say();
	}
}
}
