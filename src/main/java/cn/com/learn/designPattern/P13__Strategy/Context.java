package cn.com.learn.designPattern.P13__Strategy;

public class Context {

	private Strategy stragety;

	public Context(Strategy stragety) {
		this.stragety = stragety;
	}

	public void operate() {
		this.stragety.operate();
	}
}
