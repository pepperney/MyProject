package cn.com.learn.designPattern.P06__Adapter;


public class Test {

	/**
	 * 当希望将一个类转换成满足另一个新功能的的类时，可以适配器模式
	 * @param args
	 */
	public static void main(String[] args) {

		ITarget target1= new Adapter_Class();  
        target1.method1();  
   
        ITarget target2 = new Adapter_Object(new Adaptee());
        target2.method1();

	}

}
