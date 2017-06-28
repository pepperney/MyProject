package cn.com.learn.designPattern.P04__Builder;

import java.util.List;

public class BMWBuilder extends CarBuilder {
	
	private AbstractCar bmw = new  BMW();

	@Override
	public void setSequence(List<String> sequence) {
		this.bmw.setSequence(sequence);
	}

	@Override
	public AbstractCar getCar() {
		return this.bmw;
	}

}
