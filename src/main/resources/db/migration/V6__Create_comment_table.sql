drop table if exists comment;
create table comment
(
	id bigint auto_increment,
	parent_id int not null,
	type int not null,
	commentor int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count int default 0 null,
	constraint comment_pk
		primary key (id)
);

