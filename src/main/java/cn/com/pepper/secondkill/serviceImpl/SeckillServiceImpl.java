package cn.com.pepper.secondkill.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import cn.com.pepper.common.model.Constant;
import cn.com.pepper.exception.MyException;
import cn.com.pepper.secondkill.dao.SeckillItemDao;
import cn.com.pepper.secondkill.dao.SeckillRecordDao;
import cn.com.pepper.secondkill.model.Exposer;
import cn.com.pepper.secondkill.model.Seckill;
import cn.com.pepper.secondkill.model.SeckillExecution;
import cn.com.pepper.secondkill.model.SeckillRecord;
import cn.com.pepper.secondkill.service.SeckillService;
import cn.com.pepper.util.RedisService;
import cn.com.pepper.util.SerializeUtil;

@Service("seckillService")
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
	private final String salt = "shsdssljdd'l.";

	// 注入Service依赖
	@Autowired 
	private SeckillItemDao seckillItemDao;

	@Autowired 
	private SeckillRecordDao seckillRecordDao;

	@Autowired
	private RedisService redisService;

	public List<Seckill> getSeckillList() {
		return seckillItemDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		return seckillItemDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// 从redis获取商品详情，如果获取不到则去数据库查询
		Seckill seckill = (Seckill) SerializeUtil.deSerialize(redisService.get(SerializeUtil.serialize(seckillId)));
		if (seckill == null) {
			seckill = seckillItemDao.queryById(seckillId);
			if (seckill == null) { // 说明查不到这个秒杀产品的记录
				return new Exposer(false, seckillId);
			} else {
				redisService.set(SerializeUtil.serialize(seckillId), SerializeUtil.serialize(seckill));
			}
		}
		Long startTime = seckill.getStartTime().getTime();
		Long endTime = seckill.getEndTime().getTime();
		Long nowTime = new Date().getTime();
		if (startTime > nowTime || endTime < nowTime) {//活动未开始或者已经结束
			return new Exposer(false, seckillId, nowTime, startTime, endTime);
		}
		// h活动正在进行，返回秒杀商品的id、用来给接口加密的md5
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	
	
	/**
	 * 使用注解控制事务方法的优点: 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作、只读操作不要事务控制
	 */
	@Transactional// 秒杀是否成功，成功:减库存，增加明细；失败:抛出异常，事务回滚
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws MyException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new MyException("seckill data has been rewritten");// 秒杀数据被重写了
		}
		int insertCount = seckillRecordDao.insertRecord(seckillId, userPhone);//秒杀记录表增加购买明细
		if (insertCount <= 0) {// 看是否该明细被重复插入，即用户是否重复秒杀
			throw new MyException("seckill repeated");
		} else {
			int updateCount = seckillItemDao.reduceNumber(seckillId, new Date());//商品表减库存
			if (updateCount <= 0) {// 没有更新库存记录，说明秒杀结束
				throw new MyException("seckill is closed");
			} else {
				SeckillRecord successKilled = seckillRecordDao.getRecordById(seckillId, userPhone);
				return new SeckillExecution(seckillId, Constant.SECKILL_SUCCEESD, successKilled);
			}
		}

	}

	@Override
	public SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5) {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new MyException("seckill data rewrite");// 秒杀数据被重写了
		}
		Date nowTime = new Date();
		Map<String, Object> map = new HashMap<>();
		map.put("seckillId", seckillId);
		map.put("killTime", nowTime);
		map.put("phone", userPhone);
		map.put("result", null);// 执行完之后result被赋值
		try {
			seckillItemDao.killByProcedure(map);
			int result = MapUtils.getInteger(map, "result", -2);
			if (result == 1) {
				SeckillRecord sk = seckillRecordDao.getRecordById(seckillId, userPhone);
				return new SeckillExecution(seckillId, Constant.SECKILL_SUCCEESD, sk);
			} else {
				return new SeckillExecution(seckillId, Constant.SECKILL_FAILED);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new SeckillExecution(seckillId, Constant.INNER_ERROR);
		}
	}
}
