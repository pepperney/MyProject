package cn.com.pepper.secondkill.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.pepper.secondkill.model.SeckillRecord;

public interface SeckillRecordDao {

    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入的行数
     */
    int insertRecord(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);


    /**
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     * @param seckillId
     * @return
     */
    SeckillRecord getRecordById(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}