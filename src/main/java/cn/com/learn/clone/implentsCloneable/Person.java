package cn.com.learn.clone.implentsCloneable;

public class Person implements Cloneable {
	private String name;
	private Car car;
	
	
	public Person(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
	protected Person clone() throws CloneNotSupportedException {		
		return (Person)super.clone();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + "]";
	}
	
	
	
}
