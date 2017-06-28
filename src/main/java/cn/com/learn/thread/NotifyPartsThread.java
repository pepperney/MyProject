package cn.com.learn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 使用ReentrantLock和Condition对象实现通知部分线程的功能
 * @Description 
 * @author niepei
 * @date 2017年4月25日 下午6:35:41 
 * @version V1.3.1
 */
public class NotifyPartsThread {

	private Lock lock = new ReentrantLock();
	public Condition conA = lock.newCondition();
	public Condition conB = lock.newCondition();

	
	public void awaitA() {
		try {
			lock.lock();// 获得对象锁
			System.out.println("begin awaitA time is " + System.currentTimeMillis() / 1000 + ",ThreadName is " + Thread.currentThread().getName());
			conA.await();
			System.out.println("  end awaitA time is " + System.currentTimeMillis() / 1000 + ",ThreadName is " + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void awaitB() {
		try {
			lock.lock();// 获得对象锁
			System.out.println("begin awaitB time is " + System.currentTimeMillis() / 1000 + ",ThreadName is " + Thread.currentThread().getName());
			conB.await();
			System.out.println("  end awaitB time is " + System.currentTimeMillis() / 1000 + ",ThreadName is " + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAllB(){
		try {
			lock.lock();
			System.out.println("  signalAllB time is " + System.currentTimeMillis() / 1000 + ",ThreadName is " + Thread.currentThread().getName());
			conB.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		NotifyPartsThread npt = new NotifyPartsThread();
		Thread2 threadA = new Thread2(npt);
		threadA.setName("A");
		threadA.start();
		Thread3 threadB = new Thread3(npt);
		threadB.setName("B");
		threadB.start();
		Thread.sleep(1000);
		npt.signalAllB();
		
	}
	
	/**
	 * 打印结果如下，1000ms后，只有线程B被唤醒了
	 * begin awaitA time is 1493170752,ThreadName is A 
	 * begin awaitB time is 1493170752,ThreadName is B 
	 * 	 signalAllB time is 1493170755,ThreadName is main 
	 *   end awaitB time is 1493170755,ThreadName is B
	 */

}



class Thread2 extends Thread{
	private NotifyPartsThread npt;
	public Thread2(NotifyPartsThread npt){
		this.npt = npt;
	}
	@Override
	public void run() {
		npt.awaitA();
	}
}


class Thread3 extends Thread{
	private NotifyPartsThread npt;
	public Thread3(NotifyPartsThread npt){
		this.npt = npt;
	}
	@Override
	public void run() {
		npt.awaitB();
	}
}