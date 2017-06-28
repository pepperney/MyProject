package cn.com.learn.designPattern.P03__Singleton;

/**
 * JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。
 * 这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕.
 * 同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
 * 
 * @author pepper
 * 
 */
public class Single_2 {

	private Single_2() {
	}

	private static Single_2 instance = new Single_2();

	public static Single_2 getInstance() {
		return instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}

}
