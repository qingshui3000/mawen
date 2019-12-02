drop database if exists `mawen`;
create database `mawen`;
use `mawen`;

drop table if exists `user`;
create table `user`(
    `id` int(11) not null auto_increment primary key ,
    `name` varchar(100) not null comment '姓名',
    `account_id` varchar(50) not null comment 'github id',
    `token` char(36) not null,
    `gmt_create` bigint not null comment '创建时间戳',
    `gmt_modified` bigint not null comment '修改时间戳'
);

drop table if exists `question`;
create table `question`(
    `id` int(11) not null auto_increment primary key ,
    `title` varchar(50) not null comment '标题',
    `description` MESSAGE_TEXT comment '问题描述',
    `gmt_create` bigint not null comment '创建时间戳',
    `gmt_modified` bigint not null comment '修改时间戳',
    `creator` int(11) not null comment '创建者',
    `comment_count` int not null comment '评论数',
    `view_count` int not null comment '浏览数',
    `like_count` int not null comment '点赞数',
    `tag` varchar(255) comment '标签'
);