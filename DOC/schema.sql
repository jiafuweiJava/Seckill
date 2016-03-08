
--【1】数据库表的DDL信息
CREATE TABLE  seckill_table(
	id  bigint(20)  NOT NULL  AUTO_INCREMENT   COMMENT "参与秒杀商品的ID",
    name varchar(10) NOT NULL COMMENT "商品的名字",
	num  bigint(20)  NOT NULL COMMENT "商品的数量",
	start_time  timestamp  NOT NULL default '0000-00-00 00:00:00'  COMMENT "秒杀开始的时间",
	end_time timestamp 		  NOT NULL default '0000-00-00 00:00:00' COMMENT "秒杀结束的时间",
	create_time timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP  COMMENT "秒杀创建的时间",
	PRIMARY KEY (id),
	KEY start_time_key (start_time),
	KEY end_time_key (end_time),
	KEY create_time_key (create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 Default CHARSET =UTF8 COMMENT "秒杀商品的信息";


CREATE TABLE seckill_information(
	seckill_id bigint(20) NOT NULL  COMMENT "商品的id",
	user_phone varchar(15) NOT NULL COMMENT "用户的手机号码",
	state tinyint(1) NOT  NULL Default -1 COMMENT "用户的状态默认-1  0成功状态 1已付钱 2发货状态",
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建的时间",
	PRIMARY KEY (seckill_id,user_phone),
	KEY create_time_key (create_time)
)ENGINE=InnoDB Default CHARSET=UTF8 COMMENT "秒杀明细表";


--【2】给秒杀表 "seckill_table"  插入数据
insert into seckill_table (name,num,start_time,end_time) 
values("商品1","10","2015-12-01 11:11:11","2015-12-02 00:00:00"),
	  ("商品2","11","2015-12-02 11:11:11","2015-12-03 00:00:00"),
	  ("商品3","12","2015-12-03 11:11:11","2015-12-04 00:00:00"),
	  ("商品4","13","2015-12-04 11:11:11","2015-12-05 00:00:00"),
	  ("商品5","14","2015-12-05 11:11:11","2015-12-06 00:00:00")


