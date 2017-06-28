package cn.com.learn.designPattern.P02__AbstractFactory;

public class CarFactory2 extends AbstractCarFactory{

	@Override
	public GasCar createGasCar() {
		
		return new BMWCar();
	}

	@Override
	public ElectroCar createElectroCar() {
		
		return new BYDCar();
	}
	
	

}
