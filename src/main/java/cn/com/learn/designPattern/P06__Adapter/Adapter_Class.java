
package cn.com.learn.designPattern.P06__Adapter;

/**
 * 类的适配器模式，使用继承的方式
 * @author pepper
 *
 */
public class Adapter_Class extends Adaptee implements ITarget {

	@Override
	public void method1() {

		this.method2();
	}

}
