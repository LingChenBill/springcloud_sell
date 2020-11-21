-- 用户
DROP TABLE IF EXISTS `user_info`;
create table `user_info` (
    `id` varchar(32) not null,
    `username` varchar(32) default '' comment '用户名字',
    `password` varchar(32) default '' comment '用户密码',
    `openid` varchar(64) default '' comment '微信openid',
    `role` tinyint(1) not null comment '1买家2卖家',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`id`)
);