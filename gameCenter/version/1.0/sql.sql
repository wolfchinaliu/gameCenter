exceluser 建表语句

CREATE TABLE exceluser(
eu_id VARCHAR(20) PRIMARY KEY NOT NULL,
lab1 VARCHAR(20),
lab2 VARCHAR(20),
lab3 VARCHAR(20),
state VARCHAR(1),
recharge_time DATETIME,
game_id VARCHAR(35)	-- 就是gameＩＤ
);


CREATE TABLE `game_excel` (
  `game_id` varchar(36) NOT NULL COMMENT '主键ID',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `title` varchar(150) DEFAULT '' COMMENT '标题',
  `reserved` varchar(150) DEFAULT NULL COMMENT '预留字段',
  `flux` varchar(20) DEFAULT NULL COMMENT '流量',
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE user_record(
user_record_id BIGINT(20) PRIMARY KEY COMMENT '主键ID' ,
open_id VARCHAR(50) COMMENT 'openID',
game_id VARCHAR(50) COMMENT 'gameID',
phone VARCHAR(20) COMMENT '手机号',
award VARCHAR(1) COMMENT '奖品标识',
play_time DATETIME COMMENT '参与活动时间',
nick_name VARCHAR(20) COMMENT '用户微信名称',
reserved1 VARCHAR(20) COMMENT '预留字段一',
reserved2 VARCHAR(20) COMMENT '预留字段二'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

