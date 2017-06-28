package cn.com.learn.designPattern.P09__Facade;

public class Client {

	/**如果我们没有Computer类，那么，CPU、Memory、
	 * Disk他们之间将会相互持有实例，产生关系，这样会造成
	 * 严重的依赖，修改一个类，可能会带来其他类的修改，有了Computer类，
	 * 他们之间的关系被放在了Computer类里，这样就起到了解耦的作用
	 * @param args
	 */
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.startUp();
		System.out.println("*********************");
		computer.shutdown();

	}

}
