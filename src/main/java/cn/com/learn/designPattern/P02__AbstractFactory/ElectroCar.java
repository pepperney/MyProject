package cn.com.learn.designPattern.P02__AbstractFactory;

public abstract class ElectroCar {
	
	public abstract void showCarInfo();

	public void engineBy() {
		System.out.println("this car is engineBy electricity!");
	}
}
