package cn.com.learn.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 
 * @Description
 * @author niepei
 * @date 2017年4月27日 上午9:49:05
 * @version V1.3.1
 */
public class ReadWriteLock {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			lock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + "在" + System.currentTimeMillis() / 1000 + "时刻获得了读锁");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}

	public void write() {
		try {
			lock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + "在" + System.currentTimeMillis() / 1000 + "时刻获得了写锁");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}


	public static void main(String[] args) {
		//读读共享
		testReadReadLock();
		/**
		 * 打印结果如下：
		 * R1在1493257634时刻获得了读锁
		 * R2在1493257634时刻获得了读锁
		 */
		
		//读写互斥
		testReadWriteLock();
		/**
		 * 打印结果如下： 
		 * R在1493258342时刻获得了读锁 
		 * W在1493258343时刻获得了写锁
		 */
		
		//写写互斥
		testWriteWriteLock();
		/**
		 * 打印结果如下： 
		 * W1在1493259822时刻获得了写锁
		 * W2在1493259823时刻获得了写锁
		 */
	}

	

	public static void testReadReadLock() {
		ReadWriteLock lock = new ReadWriteLock();
		new ReadThread("R1",lock).start();
		new ReadThread("R2",lock).start();
	}
	
	public static void testReadWriteLock() {
		ReadWriteLock lock = new ReadWriteLock();
		new ReadThread("R",lock).start();
		new WriteThread("W",lock).start();
	}
	
	public static void testWriteWriteLock() {
		ReadWriteLock lock = new ReadWriteLock();
		new WriteThread("W1",lock).start();
		new WriteThread("W2",lock).start();
	}

}
/**
 * 读线程
 * @Description 
 * @author niepei
 * @date 2017年4月27日 上午10:05:57 
 * @version V1.3.1
 */
class ReadThread extends Thread {
	private ReadWriteLock lock;

	public ReadThread(String name, ReadWriteLock lock) {
		super(name);
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.read();
	}
}
/**
 * 写线程
 * @Description 
 * @author niepei
 * @date 2017年4月27日 上午10:05:46 
 * @version V1.3.1
 */
class WriteThread extends Thread {
	private ReadWriteLock lock;

	public WriteThread(String name, ReadWriteLock lock) {
		super(name);
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.write();
	}
}
