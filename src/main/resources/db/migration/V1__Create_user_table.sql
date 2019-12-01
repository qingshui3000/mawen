drop table if exists `user`;
create table `user`(
    `id` int(11) not null auto_increment primary key ,
    `name` varchar(100) not null comment '姓名',
    `account_id` varchar(50) not null comment 'github id',
    `token` char(36) not null,
    `gmt_create` bigint not null comment '创建时间戳',
    `gmt_modified` bigint not null comment '修改时间戳'
);