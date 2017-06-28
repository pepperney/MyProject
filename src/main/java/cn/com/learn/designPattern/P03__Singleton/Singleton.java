package cn.com.learn.designPattern.P03__Singleton;

/**
 * 推荐使用这种方式创建单例--饿汉式
 * @Description 
 * @author niepei
 * @date 2017年2月9日 下午2:26:05 
 * @version V1.3.1
 */
public class Singleton {

	private static final Singleton single = new Singleton();

	private Singleton() {

	}

	public static final Singleton getInstance() {
		return single;
	}

	public void say() {
		System.out.println(" This is Instance one");
	}
}
