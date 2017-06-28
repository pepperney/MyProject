package cn.com.learn.designPattern.P04__Builder;

import java.util.ArrayList;
import java.util.List;

public class CarDesigner {

	private List<String> sequence = new ArrayList<String>();

	private BenzBuilder benzBuilder = new BenzBuilder();

	private BMWBuilder bmwBuilder = new BMWBuilder();

	public AbstractCar getBenz() {
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("engineBoom");
		this.sequence.add("stop");
		this.benzBuilder.setSequence(sequence);
		return (Benz) this.benzBuilder.getCar();
	}
	
	
	public AbstractCar getBMW() {
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("engineBoom");
		this.sequence.add("alarm");
		this.sequence.add("stop");
		this.bmwBuilder.setSequence(sequence);
		return (BMW) this.bmwBuilder.getCar();
	}
}
