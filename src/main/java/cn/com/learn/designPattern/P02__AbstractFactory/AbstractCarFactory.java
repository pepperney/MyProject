package cn.com.learn.designPattern.P02__AbstractFactory;

public abstract class AbstractCarFactory {

	public abstract GasCar createGasCar();
	
	public abstract ElectroCar createElectroCar();
}
