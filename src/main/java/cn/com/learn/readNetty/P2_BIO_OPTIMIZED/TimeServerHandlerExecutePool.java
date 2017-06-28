package cn.com.learn.readNetty.P2_BIO_OPTIMIZED;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
	
	private ExecutorService executor;

	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), // 核心线程数
				maxPoolSize, // 线程池最大线程数
				120L, // keepAliveTime
				TimeUnit.SECONDS, // 时间单位
				new ArrayBlockingQueue<Runnable>(queueSize));// 阻塞队列
	}

	public void execute(Runnable task) {
		executor.execute(task);
	}
}
