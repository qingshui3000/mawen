## 码问平台 mawen

### 资料
[前端目标网站](https://elasticsearch.cn/)  
[BootStrap文档](https://v3.bootcss.com/)  
[GitHub OAuth授权登录](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
### 工具
[Git](https://git-scm.com/)  
[Visual Paradigm 社区版](https://www.visual-paradigm.com/cn/)  
[XMind 思维导图](https://www.xmind.cn/)    
[OkHttp](https://square.github.io/okhttp/)  

###脚本
```sql
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

```