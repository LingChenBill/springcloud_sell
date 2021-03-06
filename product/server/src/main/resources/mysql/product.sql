-- 类目
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
	`category_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '类目名字',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`category_id`),
	unique key `uqe_category_type` (`category_type`)
);

-- 插入数据
INSERT INTO `product_category` (`category_id`, `category_name`, `category_type`, `create_time`, `update_time`)
VALUES
	(1, '热榜', 11, '2020-09-30 16:40:22', '2020-10-31 12:40:22'),
	(2, '好吃的', 22, '2020-09-30 13:20:21', '2020-10-31 13:34:22');


-- 商品
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名字',
	`product_price` decimal(8, 2) not null comment '单价',
	`product_stock` int not null comment '库存',
	`product_description` varchar(64) comment '描述',
	`product_icon` varchar(512) comment '小图',
	`product_status` tinyint(3) DEFAULT '0' comment '商品状态,0正常1下架',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`product_id`)
);

-- 插入数据
INSERT INTO `product_info` (`product_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_icon`, `product_status`, `category_type`, `create_time`, `update_time`)
VALUES
	('157875196366160022', '皮蛋粥', 0.02, 39, '好吃的皮蛋粥', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', 0, 1, '2020-09-30 11:40:22', '2020-10-31 13:40:23'),
	('157875196366164846', '慕斯蛋糕', 10.90, 200, '美味爽口', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', 1, 1, '2020-09-30 12:40:24', '2020-10-31 14:40:24'),
	('157875196366162707', '蜜汁鸡翅', 0.90, 982, '好吃', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', 0, 1, '2020-09-29 15:40:25', '2020-10-31 15:40:26');