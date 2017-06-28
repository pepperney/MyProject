package cn.com.learn.designPattern.P02__AbstractFactory;

public class CarFactory1 extends AbstractCarFactory {

	@Override
	public GasCar createGasCar() {

		return new BenzCar();
	}

	@Override
	public ElectroCar createElectroCar() {

		return new TeslaCar();
	}

}
