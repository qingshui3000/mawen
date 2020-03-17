create table `user_like`(
    `id` bigint(20) not null auto_increment,
    `liked_user_id` bigint(20) not null comment '被点赞人ID',
    `liked_post_id` bigint(20) not null comment '点赞人ID',
    `status` tinyint(1) default '1' comment '点赞状态，0取消，1点赞',
    `create_time` timestamp not null default current_timestamp comment '点赞时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key(`id`),
    index `liked_user_id`(`liked_user_id`),
    index `liked_post_id`(`liked_post_id`)
)comment '用户点赞记录表';