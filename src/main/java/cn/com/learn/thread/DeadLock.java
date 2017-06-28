package cn.com.learn.thread;


/**
 * 测试线程死锁的问题
 * @Description 
 * @author niepei
 * @date 2017年4月27日 下午2:28:56 
 * @version V1.3.1
 */
public class DeadLock implements Runnable {

	private String username;

	private Object lock1 = new Object();

	private Object lock2 = new Object();

	public void setUserame(String username) {
		this.username = username;
	}

	@Override
	public void run() {
		if (username.equals("a")) {
			synchronized (lock1) {
				System.out.println("username = " + username);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (lock2) {
					System.out.println("按 lock1 -> lock2 代码顺序执行了");
				}
			}
		}
		if (username.equals("b")) {
			synchronized (lock2) {
				System.out.println("username = " + username);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (lock1) {
					System.out.println("按 lock2 -> lock1 代码顺序执行了");
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		DeadLock dt = new DeadLock();
		
		dt.setUserame("a");
		Thread a = new Thread(dt);
		a.setName("A");
		a.start();
		
		Thread.sleep(1000);
		
		dt.setUserame("b");
		Thread b = new Thread(dt);
		b.setName("B");
		b.start();
	}

}
