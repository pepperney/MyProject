package cn.com.learn.designPattern.P12__Observer;

public class Client {

	public static void main(String[] args) {

		AbstractSubject subject = new MySubject();
		subject.addObserver(new Observer1());
		subject.addObserver(new Observer2());

		subject.changeStatus();
	}

}
