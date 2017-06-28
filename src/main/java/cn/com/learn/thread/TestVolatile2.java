package cn.com.learn.thread;


/**
 * 此类用于验证volatile关键字不具有非互斥性
 * @Description 
 * @author niepei
 * @date 2017年4月27日 下午2:31:14 
 * @version V1.3.1
 */
public class TestVolatile2 extends Thread {

	public static volatile int COUNT;

	public static void addCount() {
		for (int i = 0; i < 100; i++) {
			COUNT++;
		}
		System.out.println("COUNT = " + COUNT);
	}

	@Override
	public void run() {
		addCount();
	}

	/**
	 * 启动100个线程,每个线程对count进行100次累加，预期结果应该为10000，
	 * 但是多数情况下该数字是小于10000的，解决此问题的方法是对addCount方法加
	 * 修改为同步方法，加synchronized
	 * @Description 
	 * @author niepei
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			new TestVolatile2().start();
		}

	}

}
