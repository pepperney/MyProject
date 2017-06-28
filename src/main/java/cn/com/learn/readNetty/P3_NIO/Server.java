package cn.com.learn.readNetty.P3_NIO;

public class Server {

	
	public static final Integer PORT = 10010;
	
	
	public static void main(String[] args) {
			
		new Thread(new TimeServer(PORT),"NIO-Server").start();
	}
	
}
