CREATE TABLE `t_seckill_item` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';



CREATE TABLE `t_seckill_record` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀记录表';


INSERT INTO `mydb`.`t_seckill_item` (`seckill_id`, `name`, `number`, `start_time`, `end_time`, `create_time`)
VALUES 
('1', 'iphone/5000￥', '10', '2017-06-27 16:29:21', '2017-06-28 16:29:52', '2017-06-09 15:51:54'),
('2', 'oppo/2300￥', '12', '2017-06-26 00:00:00', '2017-06-27 16:29:52', '2017-06-09 15:51:54'),
('3', 'vivo/2400￥', '30', '2017-06-22 16:29:21', '2017-06-24 16:29:52', '2017-06-09 15:51:54'),
('4', 'honor/2250', '40', '2017-06-27 16:29:21', '2017-06-28 16:29:52', '2017-06-09 15:51:54');





CREATE PROCEDURE `execute_seckill`(IN `seckillId` bigint,IN `phone` bigint,IN `killTime` timestamp,OUT `result` integer)
BEGIN
	DECLARE insert_count INT DEFAULT 0;
	START TRANSACTION;
	INSERT IGNORE INTO t_seckill_record (seckill_id,user_phone,state,create_time)	VALUES (seckillId,phone,0,killTime);
	SELECT ROW_COUNT() INTO insert_count;
	IF(insert_count = 0) THEN 
		ROLLBACK;
		SET result = -1;
	ELSEIF(insert_count<0) THEN
		ROLLBACK;
		SET result = -2;
	ELSE 
		UPDATE t_seckill_item SET number = number -1 WHERE seckill_id = seckill_id AND end_time > killTime AND start_time < killTime AND number > 0;
		SELECT ROW_COUNT() INTO insert_count;
		IF(insert_count = 0) THEN 
			ROLLBACK;
			SET result = 0;
		ELSEIF(insert_count<0) THEN
			ROLLBACK;
			SET result = -2;
		ELSE 
			COMMIT;
			SET result = 1;
		END IF;
	END IF;
END