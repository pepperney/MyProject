package cn.com.learn.thread;

/**
 * 当多个线程进行操作共享数据时，volatile关键字可以保证内存中的数据可见。 相较于 synchronized 是一种较为轻量级的同步策略。
 * 
 * volatile 是线程同步的轻量级实现，所以性能肯定比synchronized好，并且volatile只能用于修饰变量，
 * 而synchronized既可以用于方法，又可以用于代码块
 * 
 * 多线程访问volatile不会发生阻塞，而synchronized会出现阻塞
 * 
 * volatile能保证数据的可见性，但是不能保证原子性，而synchronized既能保证原子性也能间接保证可见性（ 通过将私有内存和公共内存的数据做同步实现）
 * 
 * volatile解决的是变量在多个线程之间的可见性，而synchronized解决的是多个线程之间访问资源的同步性
 * 
 * 线程安全包括原子性和同步性，java的同步机制都是围绕这两个方面来确保线程安全的
 * 
 * synchronized关键字可以保证在同一时刻只有一个线程可以执行某个方法或者或者某段代码块，他包含两个特征：互斥性和可见性。
 * synchronized不仅可以解决一个线程看到对象处于不一致的状态，还可以保证进入同步方法或者同步代码块的每个线程，都看到由
 * 同一个锁保护之前所有的修改结果。
 * 
 * 
 * 注意： 
 * 1. volatile 不具备“互斥性” 
 * 2. volatile 不能保证变量的“原子性”
 * 
 */
public class TestVolatile {
	
	
	/**
	 * 
	 * @Description 
	 * @author niepei
	 */
	public static void method1(){
		Thread5 thread = new Thread5();
		thread.start();
		while (true) {
			if (thread.getFlag()==true) {
				System.out.println("--------> flag is true");
				break;
			}
		}
	}
	
	/**
	 * 启动100个线程,每个线程对count进行100次累加，预期结果应该为10000，
	 * 但是多数情况下该数字是小于10000的，解决此问题的方法是对Thread6中的
	 * addCount方法修改为同步方法，加synchronized
	 * @Description 
	 * @author niepei
	 * @param args
	 */
	public static void method2(){
		for (int i = 0; i < 100; i++) {
			new TestVolatile2().start();
		}
	}
	
	
	

	public static void main(String[] args) {
		method1();
		method2();
	}

}

class Thread5 extends Thread {

	private volatile boolean flag = false;

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flag = true;

		System.out.println("flag is " + getFlag());

	}

}



class Thread6 extends Thread {

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

}
