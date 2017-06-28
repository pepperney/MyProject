package cn.com.learn.designPattern.P03__Singleton;

import java.util.Vector;

/**
 * 采用"影子实例"的办法为单例对象的属性同步更新
 * @author pepper
 *
 */
public class Single_3 {
	
	private static Single_3 instance;
	private Vector<?> properties = null;

	public Vector<?> getProperties() {
		return properties;
	}

	private static synchronized void synchronizedInit() {
		if (instance == null) {
			instance = new Single_3();
		}
	}

	public static Single_3 getInstance() {
		if (instance == null) {
			synchronizedInit();
		}
		return instance;
	}

	public void updateProperties() {
		Single_3 shadow = new Single_3();
		properties = shadow.getProperties();
	}
}
