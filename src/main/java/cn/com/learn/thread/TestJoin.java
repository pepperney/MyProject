package cn.com.learn.thread;


/**
 * 方法join()的作用是等待线程对象被销毁。
 * 对于本例，即：使所属线程对象test正常执行run()方法中
 * 的任务而使当前线程main无限期的阻塞，等待test销毁后再
 * 继续执行main后面的代码。
 * @Description 
 * @author niepei
 * @date 2017年4月27日 下午2:56:54 
 * @version V1.3.1
 */
public class TestJoin extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" begin at "+ System.currentTimeMillis()/1000);
		try {			
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"   end at "+ System.currentTimeMillis()/1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName()+" begin at "+ System.currentTimeMillis()/1000);
		TestJoin test = new TestJoin();
		test.setName("test");
		test.start();
		test.join();
		System.out.println(Thread.currentThread().getName()+"   end at "+ System.currentTimeMillis()/1000);
		
		/**
		 * 打印结果如下：
		 * 
		 * main begin at 1493264143 
		 * test begin at 1493264143 
		 * test   end at 1493264144 
		 * main   end at 1493264144
		 */
		
		/**
		 * 如果不调用join打印结果如下：
		 * main begin at 1493264274
		 * main   end at 1493264274
		 * test begin at 1493264274
		 * test   end at 1493264275
		 */

	}
}
