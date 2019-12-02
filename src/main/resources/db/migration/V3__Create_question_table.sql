drop table if exists `question`;
create table `question`(
    `id` int(11) not null auto_increment primary key ,
    `title` varchar(50) not null comment '标题',
    `description` text comment '问题描述',
    `gmt_create` bigint not null comment '创建时间戳',
    `gmt_modified` bigint not null comment '修改时间戳',
    `creator` int(11) not null comment '创建者',
    `comment_count` int comment '评论数',
    `view_count` int comment '浏览数',
    `like_count` int comment '点赞数',
    `tag` varchar(255) comment '标签'
);