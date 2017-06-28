package cn.com.pepper.secondkill.service;

import java.util.List;

import cn.com.pepper.exception.MyException;
import cn.com.pepper.secondkill.model.Exposer;
import cn.com.pepper.secondkill.model.Seckill;
import cn.com.pepper.secondkill.model.SeckillExecution;

/**
 * 业务接口:站在使用者(程序员)的角度设计接口 三个方面:1.方法定义粒度，方法定义的要非常清楚2.参数，要越简练越好 3.返回类型(return
 * 类型一定要友好/或者return异常，我们允许的异常) Created by codingBoy on 16/11/27.
 */
public interface SeckillService {

	/**
	 * 查询全部的秒杀记录
	 * 
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查询单个秒杀记录
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	// 再往下，是我们最重要的行为的一些接口

	/**
	 * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀操作，有可能失败，有可能成功，所以要抛出我们允许的异常
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws MyException;

	/**
	 * 通过使用存储过程来优化，提高高并发的效率，减少网络延迟带来的影响
	 * 
	 * @Description
	 * @author niepei
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @return
	 * @throws SeckillException
	 * @throws RepeatKillException
	 * @throws SeckillCloseException
	 */
	SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5);

}
