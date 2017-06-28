package cn.com.learn.thread;

public class TestDamonThread extends Thread {

	public long i;

	public TestDamonThread(String threadName) {
		super(threadName);
	}
	
	@Override
	public void run() {
		
		while(true){
			i++;
			System.out.println("--> i = " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	

		
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println("【 " + Thread.currentThread().getName() + "】 has begin ");
		
		TestDamonThread daemon = new TestDamonThread("A");
		daemon.setDaemon(true);// 设置为守护线程-->当进程中不存在非守护线程了，守护线程会自动销毁，典型的守护线程就是GC线程，当JVM实例中最后一个非守护线程执行结束之后，它才会和JVM一起结束工作
		daemon.start();
		Thread.sleep(5000);
				
		System.out.println("【 " + Thread.currentThread().getName() + "】 has end ");

	}

	

}
