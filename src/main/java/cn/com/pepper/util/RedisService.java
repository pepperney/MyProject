package cn.com.pepper.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component("redisService")
public class RedisService {

	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

	@Autowired
	private JedisPool jedisPool;

	
	private int expire = 3600;// 默认过期时间一个小时
	
	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	
	/*******************************************   删    除   key  ***************************************************/ 

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public boolean del(byte[] key) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (jedis != null) {
				long count = jedis.del(key);
				if (count > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService delete key [" + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;

	}

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public boolean del(String key) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (jedis != null) {
				long count = jedis.del(key);
				if (count > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService delete key [ " + key + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}
	
	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public boolean delkeys(byte[] keys) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				Set<byte[]> result = jedis.keys(keys);
				Iterator<byte[]> iter = result.iterator();
				while (iter.hasNext()) {
					jedis.del(iter.next());
				}
			}
		} catch (Exception e) {
			logger.error("redisService delkeys error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return true;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public boolean delkeys(String keys) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				Set<String> result = jedis.keys(keys);
				Iterator<String> iter = result.iterator();
				while (iter.hasNext()) {
					jedis.del(iter.next());
				}
			}
		} catch (Exception e) {
			logger.error("redisService delkeys error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return true;
	}
	
	/**
	 * 模糊删除key
	 * 
	 * @Description
	 * @author peng.wang
	 * @param likeKey
	 * @return
	 */
	public boolean delLikeKey(String likeKey) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (jedis != null) {
				Set<String> keys = jedis.keys(likeKey + "*");
				jedis.del(keys.toArray(new String[keys.size()]));
				result = true;
			}
		} catch (Exception e) {
			logger.error("redisService delete key [" + likeKey + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}
	
	
	
	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public boolean flushDB() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				jedis.flushDB();
			}
		} catch (Exception e) {
			logger.error("redisService flushDB error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return true;
	}
	
	
	/*******************************************   设  置  过  期  时  间    ***************************************************/ 
	
	
	/**
	 * 设置过期时间
	 * 
	 * @param key
	 */
	public boolean expire(byte[] key, int expire) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (jedis != null) {
				jedis.expire(key, expire);
				result = true;
			}
		} catch (Exception e) {
			logger.error("redisService expire key [" + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;

	}

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 */
	public boolean expire(String key, int expire) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (jedis != null) {
				jedis.expire(key, expire);
				result = true;
			}
		} catch (Exception e) {
			logger.error("redisService expire key [" + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;

	}
	
	
	
	
	
	/*******************************************   添     加    key-value    ***************************************************/ 
	
	
	
	
	/**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public boolean set(byte[] key, byte[] value, int expire) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				String count = jedis.set(key, value);
				if (null != count) {
					jedis.expire(key, expire);
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService set key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public boolean set(String key, String value, int expire) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				String count = jedis.set(key, value);
				if (null != count) {
					jedis.expire(key, expire);
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService set key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 添加key value (字节)(序列化)
	 * 
	 * @param key
	 * @param value
	 */
	public boolean set(byte[] key, byte[] value) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				String count = jedis.set(key, value);
				if (null != count) {
					jedis.expire(key, this.getExpire());
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService set key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public boolean set(String key, String value) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				String count = jedis.set(key, value);
				if (null != count) {
					jedis.expire(key, this.getExpire());
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("redisService set key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	
	/*******************************************   根   据   key  获   取     value    ***************************************************/ 
	
	
	
	
	/**
	 * 获取redis value (byte [] )(反序列化)
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		byte[] result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.get(key);
			}
		} catch (Exception e) {
			logger.error("redisService get key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}
	
	/**
	 * 获取redis value (String)
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.get(key);
			}
		} catch (Exception e) {
			logger.error("redisService get key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	

	/**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		Set<String> result = new HashSet<String>();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.keys(pattern);
			}
		} catch (Exception e) {
			logger.error("redisService keys key [ " + pattern + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}
	
	
	
	
	
	/*******************************************   通  过  正  则  匹   配  keys    ***************************************************/ 
	
	
	
	/**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<byte[]> keys(byte[] pattern) {
		Set<byte[]> result = new HashSet<byte[]>();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.keys(pattern);
			}
		} catch (Exception e) {
			logger.error("redisService keys key [ " + pattern + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}
	
	

	/*******************************************   检  查  key 是  否  已  经  存  在    ***************************************************/ 
	
	
	
	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.exists(key);
			}
		} catch (Exception e) {
			logger.error("redisService exists key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(byte[] key) {
		Jedis jedis = null;
		boolean result = false;
		try {
			jedis = jedisPool.getResource();
			if (null != jedis) {
				result = jedis.exists(key);
			}
		} catch (Exception e) {
			logger.error("redisService exists key [ " + key.toString() + " ] error", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return result;
	}

	/*******************************************   缓   存   大   小    ***************************************************/ 

	/**
	 * 缓存大小
	 * 
	 * @return
	 */
	public Long dbSize() {
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try {
			dbSize = jedis.dbSize();
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return dbSize;
	}

	
	public void destroy() {
		jedisPool.destroy();
	}

}
