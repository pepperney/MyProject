package cn.com.learn.thread;
/**
 * 类ThreadLocal可以使每个线程绑定自己的值，
 * 它解决的是变量在各个线程之间的隔离问题，也就是不
 * 同线程拥有自己的值，不同线程中的值是可以放入
 * ThreadLocal中保存的
 * @Description 
 * @author niepei
 * @date 2017年4月27日 下午2:09:08 
 * @version V1.3.1
 */
public class TestThreadLocal {

	public static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

	private ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		function1();
		/**
		 * 打印结果如下: 
		 * THREAD_LOCAL in <main> is null 
		 * THREAD_LOCAL in <test> is null 
		 * THREAD_LOCAL in <test> is test 
		 * THREAD_LOCAL in <main> is main		  
		 */
		
		
		new TestThreadLocal().function2();
		/**
		 * 打印结果如下: 
		 * threadLocal in <main> is null
		 * threadLocal in <test> is null
		 * threadLocal in <test> is test
		 * threadLocal in <main> is main
		 */
		
	}

	public static void function1() throws InterruptedException {
		System.out.println("THREAD_LOCAL in <" + Thread.currentThread().getName() + "> is " + THREAD_LOCAL.get());
		THREAD_LOCAL.set("main");
		Thread a = new Thread("test") {
			@Override
			public void run() {
				System.out.println(
						"THREAD_LOCAL in <" + Thread.currentThread().getName() + "> is " + THREAD_LOCAL.get());
				THREAD_LOCAL.set("test");
				System.out.println(
						"THREAD_LOCAL in <" + Thread.currentThread().getName() + "> is " + THREAD_LOCAL.get());
			}
		};
		a.start();
		a.join();
		System.out.println("THREAD_LOCAL in <" + Thread.currentThread().getName() + "> is " + THREAD_LOCAL.get());
	}

	public void function2() throws InterruptedException {

		System.out.println("threadLocal in <" + Thread.currentThread().getName() + "> is " + threadLocal.get());
		threadLocal.set("main");
		Thread a = new Thread("test") {
			@Override
			public void run() {
				System.out.println("threadLocal in <" + Thread.currentThread().getName() + "> is " + threadLocal.get());
				threadLocal.set("test");
				System.out.println("threadLocal in <" + Thread.currentThread().getName() + "> is " + threadLocal.get());
			}
		};
		a.start();
		a.join();
		System.out.println("threadLocal in <" + Thread.currentThread().getName() + "> is " + threadLocal.get());
	}

}
