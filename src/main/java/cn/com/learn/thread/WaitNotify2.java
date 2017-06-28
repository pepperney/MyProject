package cn.com.learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock和Condition对象实现等待-通知机制
 * 
 * @Description
 * @author niepei
 * @date 2017年4月25日 下午5:37:35
 * @version V1.3.1
 */
public class WaitNotify2 {

	private Lock lock = new ReentrantLock();
	private Condition con = lock.newCondition();

	public void waitMethod() {

		try {
			lock.lock();// 获得对象锁
			System.out.println("  wait time begin at " + System.currentTimeMillis() / 1000);
			con.await();// 线程进入等待
			System.out.println("  wait time   end at " + System.currentTimeMillis() / 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
	public void notityMethod(){
		try {
			lock.lock();
			System.out.println("notify time begin at " + System.currentTimeMillis() / 1000);
			con.signal();// 唤醒线程
			System.out.println("notify time   end at " + System.currentTimeMillis() / 1000);
		} finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		WaitNotify2 wn2 = new WaitNotify2();
		Thread4 thread = new Thread4(wn2);
		thread.start();
		Thread.sleep(1000);
		wn2.notityMethod();
	}
	
	
	/**
	 * 打印结果为： 
	 *   wait time begin at 1493194430 
	 * notify time begin at 1493194431
	 * notify time   end at 1493194431 
	 *   wait time   end at 1493194431
	 * 
	 */

}

class Thread4 extends Thread {
	private WaitNotify2 wn2;

	public Thread4(WaitNotify2 wn2) {
		this.wn2 = wn2;
	}

	@Override
	public void run() {
		wn2.waitMethod();
	}

}
