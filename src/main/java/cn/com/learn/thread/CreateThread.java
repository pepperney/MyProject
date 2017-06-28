package cn.com.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThread {

	public static void method1() {
		Thread thread1 = new ThreadA();
		thread1.setName("A");
		thread1.start();
	}

	public static void method2() {
		Thread thread2 = new Thread(new ThreadB());
		thread2.setName("B");
		thread2.start();
	}

	/**
	 * 执行 Callable方式，需要 FutureTask 实现类的支持，用于接收运算结果。 FutureTask 是 Future
	 * 接口的实现类,FutureTask 可用于 闭锁CountDowmLatch
	 * 
	 * @Description
	 * @author niepei
	 */
	public static Integer method3() {

		ThreadC c = new ThreadC();
		FutureTask<Integer> result = new FutureTask<>(c);
		Thread thread3 = new Thread(result);
		thread3.setName("C");
		thread3.start();
		Integer sum = 0;
		try {
			sum = result.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return sum;
	}

	public static void main(String[] args) {

		method1();
		method2();
		method3();

	}

}

/**
 * 创建执行线程的方式一：继承Thread类
 * 
 * @Description
 * @author niepei
 * @date 2017年4月27日 下午3:56:43
 * @version V1.3.1
 */
class ThreadA extends Thread {
	@Override
	public void run() {
		System.out.println("the thread name is " + Thread.currentThread().getName());
	}
}

/**
 * 创建执行线程的方式二：实现 Runnable 接口的方式
 * 
 * @Description
 * @author niepei
 * @date 2017年4月27日 下午3:55:33
 * @version V1.3.1
 */
class ThreadB implements Runnable {

	@Override
	public void run() {
		System.out.println("the thread name is " + Thread.currentThread().getName());
	}

}

/**
 * 创建执行线程的方式三：实现 Callable 接口。
 * 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。 
 * @Description
 * @author niepei
 * @date 2017年4月27日 下午3:56:19
 * @version V1.3.1
 */

class ThreadC implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("the thread name is " + Thread.currentThread().getName());
		int sum = 0;
		for (int i = 0; i <= 100000; i++) {
			sum += i;
		}
		return sum;
	}

}
