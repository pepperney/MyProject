package cn.com.learn.designPattern.P04__Builder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCar {

	private List<String> sequence = new ArrayList<String>();

	public abstract void start();

	public abstract void engineBoom();

	public abstract void alarm();

	public abstract void stop();

	public final void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
	
	public void run() {
		for (String str : sequence) {
			if (str.equals("start")) {
				this.start();
			}
			else if (str.equals("engineBoom")) {
				this.engineBoom();
			}
			else if (str.equals("alarm")) {
				this.alarm();
			}
			else if (str.equals("stop")) {
				this.stop();
			}
		}
	}

}
