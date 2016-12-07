-- 金华电信导入用户白名单

CREATE TABLE `user_jhdx` (
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID主键',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `upToDate` date DEFAULT NULL COMMENT '截至日期',
  `flux` varchar(35) DEFAULT NULL COMMENT '流量',
  `total` int(10) DEFAULT NULL COMMENT '总次数',
  `status` varchar(100) DEFAULT NULL COMMENT '状态',
  `gameId` varchar(35) DEFAULT NULL COMMENT 'GameID',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='金华电信导入用户白名单';

--绑定表

CREATE TABLE `user_phone` (
  `openid` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '微信号',
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '微信名称',
  `phone_number` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='绑定表';



-- 游戏参与表

CREATE TABLE `play_record` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `openid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户openid',
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '微信名称',
  `game_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '游戏ID',
  `level` smallint(4) DEFAULT NULL COMMENT '中奖等级',
  `award_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '奖品名称',
  `paly_time` datetime DEFAULT NULL COMMENT '游戏时间',
  `phone_number` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参与游戏表';


