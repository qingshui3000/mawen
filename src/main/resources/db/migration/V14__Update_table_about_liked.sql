alter table `user` add `like_count` int default '0' not null;
alter table `user_like` add `type` tinyint(1) comment '点赞类型，1问题，2评论';
alter table `user_like` add `target_id` bigint not null;

