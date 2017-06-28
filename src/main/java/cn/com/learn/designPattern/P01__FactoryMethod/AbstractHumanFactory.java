package cn.com.learn.designPattern.P01__FactoryMethod;

public abstract class AbstractHumanFactory {

	public abstract <T extends Human> T createHuman(Class<T> human);
	
}
