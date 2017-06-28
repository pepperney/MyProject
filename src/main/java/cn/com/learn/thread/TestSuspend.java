package cn.com.learn.thread;

public class TestSuspend {

	@SuppressWarnings("deprecation")
	public synchronized void printString() {
		System.out.println("【 " + Thread.currentThread().getName() + "】 has begin ");
		if (Thread.currentThread().getName().equals("A")) {
			System.out.println("【 " + Thread.currentThread().getName() + "】 has been suspend");
			Thread.currentThread().suspend();
		}
		System.out.println("【 " + Thread.currentThread().getName() + "】 has end ");
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("【 " + Thread.currentThread().getName() + "】 has begin ");

		final TestSuspend test = new TestSuspend();

		Thread thread1 = new Thread() {
			@Override
			public void run() {
				test.printString();
			}
		};
		thread1.setName("A");
		thread1.start();
		Thread.sleep(1000);

		Thread thread2 = new Thread() {
			@Override
			public void run() {
				System.out.println("【 " + Thread.currentThread().getName() + "】 can't start,because printString() is locked by Thread 【A】 and suspend forever ");
				test.printString();
			}
		};
		thread2.setName("B");
		thread2.start();

		System.out.println("【 " + Thread.currentThread().getName() + "】 has end ");
	}

}
