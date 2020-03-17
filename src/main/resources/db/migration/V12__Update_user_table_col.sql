alter table user drop base64;
alter table user drop account_id;
alter table user add account varchar(64) not null after name;
alter table user add password varchar(64) not null after account;