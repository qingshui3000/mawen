/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : mawen

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-03-17 15:59:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `commentor` bigint(20) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `like_count` int(11) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `comment_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='评论实体表';;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `description` text COMMENT '问题描述',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间戳',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间戳',
  `creator` bigint(20) NOT NULL COMMENT '创建者',
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数',
  `view_count` int(11) DEFAULT NULL COMMENT '浏览数',
  `like_count` int(11) DEFAULT NULL COMMENT '点赞数',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='问题实体表';;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `account` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `token` char(36) NOT NULL,
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间戳',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间戳',
  `bio` varchar(255) DEFAULT NULL,
  `userface_url` varchar(100) DEFAULT NULL,
  `like_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='用户实体表';

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `liked_user_id` bigint(20) NOT NULL COMMENT '被点赞人ID',
  `liked_post_id` bigint(20) NOT NULL COMMENT '点赞人ID',
  `status` tinyint(1) DEFAULT '1' COMMENT '点赞状态，0取消，1点赞',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` tinyint(1) DEFAULT NULL COMMENT '点赞类型，1问题，2评论',
  PRIMARY KEY (`id`),
  KEY `liked_user_id` (`liked_user_id`),
  KEY `liked_post_id` (`liked_post_id`)
) ENGINE=InnoDB COMMENT='用户点赞记录表';
