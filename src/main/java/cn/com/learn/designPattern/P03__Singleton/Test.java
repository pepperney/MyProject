package cn.com.learn.designPattern.P03__Singleton;

/**
 *在Java应用中，单例模式能保证在一个JVM中，该对象只有一个实例存在。这样的模式有几个好处：
 * 
 *1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 * 
 *2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 * 
 *3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程
 * 
 * @author pepper
 * 
 */
public class Test {

	public static void main(String[] args) {

		Object obj1 = Single_1.getInstance();
		Object obj2 = Single_1.getInstance();
		System.out.println(obj1.equals(obj2));

		Object obj3 = Single_2.getInstance();
		Object obj4 = Single_2.getInstance();
		System.out.println(obj3.equals(obj4));

	}

}
