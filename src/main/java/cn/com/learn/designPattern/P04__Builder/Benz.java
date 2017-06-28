package cn.com.learn.designPattern.P04__Builder;

public class Benz extends AbstractCar{

	@Override
	public void start() {
		System.out.println("Benz is starting");		
	}

	@Override
	public void engineBoom() {
		System.out.println("Benz is engineBooming");		
		
	}

	@Override
	public void alarm() {
		System.out.println("Benz is alarming");		
		
	}

	@Override
	public void stop() {
		System.out.println("Benz is stoping");			
	}

	
	
}
