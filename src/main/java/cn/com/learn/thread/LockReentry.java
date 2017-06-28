package cn.com.learn.thread;

/**
 * 此类用于演示锁重入的概念
 * 
 * 可重入锁：自己可以再次获取自己的内部锁。例如一个线程获得了某个对象的锁，此时这个对象还没有释放，当其再次想要获取这个对象的锁的时候还是可以获得，
 * 如果不可锁重入的话，则会造成死锁，并且当存在父子集成关系时，子类完全可以通过"可重入锁"调用父类的同步方法
 * 
 * @Description
 * @author niepei
 * @date 2017年4月25日 下午3:52:41
 * @version V1.3.1
 */
public class LockReentry {
	public synchronized void service1() {
		System.out.println("service1");
		this.service2();
	}

	public synchronized void service2() {
		System.out.println("service2");
		this.service3();
	}

	public synchronized void service3() {
		System.out.println("service3");
	}

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				LockReentry service = new LockReentry();
				service.service1();
			}
		};
		thread.start();
	}
}
