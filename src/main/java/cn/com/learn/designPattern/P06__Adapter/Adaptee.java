package cn.com.learn.designPattern.P06__Adapter;


/**
 * 
 * 有一个目标接口Target，有一个方法method1，现在 有一个Adaptee类，拥有一个方法method2，
 * 希望通过Adapter类适配后，使得Target接口能完成Adaptee的功能。
 * @author pepper
 *
 */

public class Adaptee {
	
	public void method2(){
		
		System.out.println("this is method2");
	}

}
