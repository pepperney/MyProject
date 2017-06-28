package cn.com.learn.dynamicProxy.reflect;

public class Car {
	private String trademark;
	private String color;
	private int speed;

	public Car() {

	}

	public Car(String trademark) {
		this.trademark = trademark;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int speedUp(int addSpeed) {
		this.setSpeed(this.speed + addSpeed);
		return this.getSpeed();
	}

}
