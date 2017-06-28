package cn.com.learn.designPattern.P02__AbstractFactory;

public class TeslaCar extends ElectroCar{

	@Override
	public void showCarInfo() {
		
		System.out.println("this is Tesla Car,it belongs ElectroCar");
		
	}

}
