package cn.com.learn.designPattern.P06__Adapter;

/**
 * 对象的的适配器模式，持有Adaptee的实例 
 * @author pepper
 * 
 */
public class Adapter_Object implements ITarget {
	
	private Adaptee adaptee;
	//构造方法
	public Adapter_Object(Adaptee adaptee){
		this.adaptee = adaptee;
	}

	@Override
	public void method1() {
		
		adaptee.method2();
	}




}
