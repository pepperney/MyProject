package cn.com.learn.designPattern.P04__Builder;

public class BMW extends AbstractCar{

	@Override
	public void start() {
		System.out.println("BMW is starting");
		
	}

	@Override
	public void engineBoom() {
		System.out.println("BMW is engineBooming");		
	}

	@Override
	public void alarm() {
		System.out.println("BMW is alarming");	
		
	}

	@Override
	public void stop() {
		System.out.println("BMW is stoping");	
		
	}

}
