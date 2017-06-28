package cn.com.learn.designPattern.P03__Singleton;

/**
 * 线程安全的懒汉式单例模式--lazy loading
 * @Description 
 * @author niepei
 * @date 2017年2月9日 下午2:27:20 
 * @version V1.3.1
 */
public class Single_1 {

	private Single_1() {
	}

	private static Single_1 instance = null;

	public synchronized static Single_1 getInstance() {
		if (instance == null) {
			instance = new Single_1();
		}
		return instance;
	}

}
