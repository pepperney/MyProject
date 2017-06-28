package cn.com.learn.designPattern.P04__Builder;

import java.util.List;

public class BenzBuilder extends CarBuilder{
	
	private AbstractCar benz = new Benz();;

	@Override
	public void setSequence(List<String> sequence) {
	   this.benz.setSequence(sequence);
	}

	@Override
	public AbstractCar getCar() {
		return this.benz;
	}

}
