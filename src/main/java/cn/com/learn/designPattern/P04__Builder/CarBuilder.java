package cn.com.learn.designPattern.P04__Builder;

import java.util.List;

public abstract class CarBuilder {

	public abstract void setSequence(List<String> sequence);

	public abstract AbstractCar getCar();

}
