package cn.com.learn.clone.implentsCloneable;

public class Car implements Cloneable{
	String brand;
	
	public Car(String brand){
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	protected Car clone() throws CloneNotSupportedException {
		return (Car) super.clone();
	}

	@Override
	public String toString() {
		return brand;
	} 
}
