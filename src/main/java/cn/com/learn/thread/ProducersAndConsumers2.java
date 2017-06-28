package cn.com.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用ReentrantLock和Condition对象实现生产者-消费者模式
 * @Description 
 * @author niepei
 * @date 2017年4月26日 下午6:13:06 
 * @version V1.3.1
 */
public class ProducersAndConsumers2 {

	private List<String> list = new ArrayList<>();
	
	private Lock lock = new ReentrantLock();
	
	private Condition con = lock.newCondition();
	
	public void produce(){
		try {
			lock.lock();
			while(list.size()==1){
				System.out.println(Thread.currentThread().getName() + " begin waiting ");
				con.await();
				System.out.println(Thread.currentThread().getName() + "   end waiting ");
			}
			list.add("nulo");
			con.signalAll();
			System.out.println("produced by " + Thread.currentThread().getName() + ",now size = " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public String consume(){
		String value = "";
		try {
			lock.lock();
			while(list.size()==0){
				System.out.println(Thread.currentThread().getName() + " begin waiting ");
				con.await();
				System.out.println(Thread.currentThread().getName() + "   end waiting ");
			}
			list.remove(0);
			con.signalAll();
			System.out.println("consumed by " + Thread.currentThread().getName() + ",now size = " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return value;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ProducersAndConsumers1 pool = new ProducersAndConsumers1();

		// 一生产一消费
		new ProducerThread("producer",pool).start();
		new ConsumerThread("consumer",pool).start();
		
		/**
		 * 打印结果如下： 
		 * consumer begin waiting 
		 * producer end waiting 
		 * produced by producer,now size = 1 
		 * producer begin waiting 
		 * consumer end waiting
		 * consumed by consumer,now size = 0 
		 * consumer begin waiting 
		 * producer end waiting
		 * produced by producer,now size = 1
		 * producer begin waiting
		 * consumer end   waiting 
		 * consumed by consumer,now size = 0 
		 * consumer begin waiting 
		 */
		

	}
	
}
