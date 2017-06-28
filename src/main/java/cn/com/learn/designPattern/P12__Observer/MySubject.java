package cn.com.learn.designPattern.P12__Observer;


public class MySubject extends AbstractSubject{


	public void changeStatus() {

		System.out.println("I am subject,I have changed my status");
		this.notifyAllObserver();
		
	}
	
	

}
