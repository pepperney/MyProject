package cn.com.pepper.util;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;
/**
 * 基于redis实现分布式锁
 * 代码参考博客：http://www.cnblogs.com/wuhuajun/p/5242644.html
 * 原理参考博客：http://www.cnblogs.com/it-cen/p/4984272.html
 * @author niepei
 * 
 */
public class RedisLock {

	@Autowired
	private JedisPool jedisPool;
	
	private static final String LOCK_PREFIX = "lock:";

	/**
	 * 获取分布式锁
	 * 
	 * @param key     竞争获取锁key
	 * @param timeout 获取锁超时时间/ms
	 * @param expire  锁的超时时间/ms
	 * @return 获取锁标识
	 */
	public String lock(String key, long timeout, long expire) {
		Jedis jedis = null;
		boolean broken = false;
		String identifier = null;//锁的标识
		try {
			jedis = jedisPool.getResource();
			identifier = UUID.randomUUID().toString();
			key = LOCK_PREFIX + key;
			int lockExpire = (int) (expire / 1000);

			long end = System.currentTimeMillis() + timeout;
			while (System.currentTimeMillis() < end) {//获取锁的等待时间之内完成加锁操作
				if (jedis.setnx(key, identifier) == 1) {
					jedis.expire(key, lockExpire);
				}
				if (jedis.ttl(key) == -1) {// 当key没有设置过期时间时
					jedis.expire(key, lockExpire);
				}

				try {
					Thread.sleep(10);// 短暂休眠 避免出现活锁
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
		} catch (JedisException e) {
			if (jedis != null) {
				broken = true;
				jedis.close();
			}
		} finally {
			if (jedis != null && !broken) {
				jedis.close();
			}
		}
		return identifier;
	}

	/**
	 * 释放锁
	 * 
	 * @param key         竞争获取锁key
	 * @param identifier  释放锁标识
	 * @return
	 */
	public boolean unlock(String key, String identifier) {
		Jedis jedis = null;
		boolean broken = false;
		key = LOCK_PREFIX + key;
		boolean flag = false;
		try {
			jedis = jedisPool.getResource();
			while (true) {
				jedis.watch(key);//监控key，如果调用后key值发生变化，则整个事务会执行失败,另外，事务中某个操作失败，并不会回滚其他操作
				if (identifier.equals(jedis.get(key))) {
					Transaction trans = jedis.multi();//MULTI 标记一个事务块的开始
					trans.del(key);//释放锁
					List<Object> results = trans.exec();//由 EXEC 命令原子性(atomic)地执行。
					if (results == null) {
						continue;
					}
					flag = true;
				}
				jedis.unwatch();//取消对key的监控
				break;
			}

		} catch (JedisException je) {
			if (jedis != null) {
				broken = true;
				jedis.close();
			}
		} finally {
			if (jedis != null && !broken) {
				jedis.close();
			}
		}
		return flag;
	}
}
