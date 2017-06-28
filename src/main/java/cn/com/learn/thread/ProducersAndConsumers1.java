package cn.com.learn.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用synchronized与wait,notify实现生产者-消费者模式
 * 
 * @Description
 * @author niepei
 * @date 2017年4月26日 下午4:27:21
 * @version V1.3.1
 */
public class ProducersAndConsumers1 {

	private List<String> list = new ArrayList<>();

	public synchronized void produce() {
		try {
			while (list.size() == 1) {
				System.out.println(Thread.currentThread().getName() + " begin waiting ");
				this.wait();
				System.out.println(Thread.currentThread().getName() + " end   waiting ");
			}
			list.add("nulo");
			this.notifyAll();
			System.out.println("produced by " + Thread.currentThread().getName() + ",now size = " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized String consume() {
		String value = "";
		try {
			while (list.size() == 0) {
				System.out.println(Thread.currentThread().getName() + " begin waiting ");
				this.wait();
				System.out.println(Thread.currentThread().getName() + " end   waiting ");
			}
			value = list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("consumed by " + Thread.currentThread().getName() + ",now size = " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
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
		 * 
		 */
		
		
		
		
		
		// 一生产多消费
		/*new ProducerThread("producer",pool).start();
		new ConsumerThread("consumer1",pool).start();
		new ConsumerThread("consumer2",pool).start();*/
		
		// 多生产一消费
		/*new ProducerThread("producer1",pool).start();
		new ProducerThread("producer2",pool).start();
		new ConsumerThread("consumer",pool).start();*/
		
		// 多生产多消费
		/*new ProducerThread("producer1",pool).start();
		new ProducerThread("producer2",pool).start();
		new ConsumerThread("consumer1",pool).start();
		new ConsumerThread("consumer2",pool).start();*/
		

	}
}

/**
 * 
 * 生产者线程
 * 
 * @Description
 * @author niepei
 * @date 2017年4月26日 下午4:44:17
 * @version V1.3.1
 */
class ProducerThread extends Thread {
	private ProducersAndConsumers1 pool;

	public ProducerThread(String name,ProducersAndConsumers1 pool) {
		super(name);
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			pool.produce();
		}

	}
}

/**
 * 
 * 消费者线程
 * 
 * @Description
 * @author niepei
 * @date 2017年4月26日 下午4:44:29
 * @version V1.3.1
 */
class ConsumerThread extends Thread {
	private ProducersAndConsumers1 pool;

	public ConsumerThread(String name,ProducersAndConsumers1 pool) {
		super(name);
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			pool.consume();
		}

	}
}
