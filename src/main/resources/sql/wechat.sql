CREATE TABLE `t_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '�û�ID',
  `username` varchar(50) NOT NULL COMMENT '�û��� ',
  `realname` varchar(50) DEFAULT NULL COMMENT '��ʵ����',
  `password` varchar(255) NOT NULL COMMENT '����',
  `email` varchar(30) DEFAULT NULL COMMENT '����',
  `mobile` int(11) DEFAULT NULL COMMENT '�ֻ�',
  `qq` int(15) DEFAULT NULL COMMENT 'QQ��',
  `openid` varchar(255) DEFAULT NULL COMMENT '΢��openid',
  `nickname` varchar(30) DEFAULT NULL COMMENT '΢���ǳ�',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '΢��ͷ��',
  `usertype` int(11) DEFAULT NULL COMMENT '�û�����:0-admin��1-��ͨ�û�',
  `userstatus` int(11) DEFAULT '0' COMMENT '�û�״̬:0-δ���1-����',
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

