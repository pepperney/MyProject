CREATE TABLE `t_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名 ',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `mobile` int(11) DEFAULT NULL COMMENT '手机',
  `qq` int(15) DEFAULT NULL COMMENT 'QQ号',
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `nickname` varchar(30) DEFAULT NULL COMMENT '微信昵称',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `usertype` int(11) DEFAULT NULL COMMENT '用户类型:0-admin；1-普通用户',
  `userstatus` int(11) DEFAULT '0' COMMENT '用户状态:0-未激活；1-激活',
  PRIMARY KEY (`userid`),
  KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_wechat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `appid` varchar(255) NOT NULL,
  `appsecret` varchar(255) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `modify_time` bigint(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_city` (
  `cityid` int(11) NOT NULL AUTO_INCREMENT,
  `cityname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

