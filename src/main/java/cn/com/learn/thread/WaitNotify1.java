package cn.com.learn.thread;

/**
 * 使用synchronized和wait，notify实现等待通知机制

 * wait() 使当前执行代码的线程进行等待，将当前线程置入"预执行队列"，并且在wait()方法所在处停止执行，直到接到通知或者中断。
 * 在调用wait之前,线程必须获得该对象的对象级别锁，即只能在同步方法或者同步代码块中才能调用wait方法。
 * 调用wait()方法后当前线程自动释放锁。在从wait()返回之前，线程与其他线程竞争重新获得锁。
 * 
 * 
 * notify()
 * 用来通知那些可能等待该对象的对象锁的其他线程，如果有多个线程等待，则由线程规划器随机选出其中一个呈wait()状态的线程对其发出notify(),
 * 并使它获取该对象的对象锁，值得注意的是在执行notify方法后当前线程不会马上释放该对象锁，呈wait状态的线程也不能马上获得该对象锁，
 * 要等到执行notify方法的 线程将线程执行完，也就是退出synchronized代码块后，当前线程才会释放锁，而呈wait状态的线程才可以获取该对象锁。
 * 当第一个获得了该对象锁的线程运行完毕之后它会释放掉该对象锁，
 * 此时如果对象没有再次使用notify方法，即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，还会阻塞在wait状态，
 * 直到这个对象发出一个notify或者notifyAll notify方法也要在同步方法或者同步代码块中才能调用，线程也必须获得该对象的对象级别锁，
 * 
 * @Description
 * @author niepei
 * @date 2017年4月26日 下午4:10:57
 * @version V1.3.1
 */
public class WaitNotify1 {

	public void waitMethod() {
		System.out.println("  wait time begin at " + System.currentTimeMillis() / 1000);
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("  wait time   end at " + System.currentTimeMillis() / 1000);
	}

	public void notifyMethod() {
		System.out.println("notify time begin at " + System.currentTimeMillis() / 1000);
		synchronized (this) {
			this.notify();
		}

		System.out.println("notify time   end at " + System.currentTimeMillis() / 1000);
	}

	public static void main(String[] args) throws InterruptedException {
		WaitNotify1 wn1 = new WaitNotify1();
		// 使用匿名内部类的方式启动两个线程分别调用wn1对象的waitMethod和notifyMethod
		new Thread(new Runnable() {
			@Override
			public void run() {
				wn1.waitMethod();

			}
		}).start();

		Thread.sleep(1000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				wn1.notifyMethod();

			}
		}).start();

		/**
		 * 打印结果为： 
		 *   wait time begin at 1493194588 
		 * notify time begin at 1493194589
		 * notify time   end at 1493194589 
		 *   wait time   end at 1493194589
		 * 
		 */

	}

}
