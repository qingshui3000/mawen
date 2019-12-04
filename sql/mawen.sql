/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : mawen

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-12-03 20:43:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES ('1', '1', 'Create user table', 'SQL', 'V1__Create_user_table.sql', '-1758537475', 'root', '2019-12-01 14:44:02', '86', '1');
INSERT INTO `flyway_schema_history` VALUES ('2', '2', 'Add bio col to user table', 'SQL', 'V2__Add_bio_col_to_user_table.sql', '1301280093', 'root', '2019-12-01 14:47:11', '67', '1');
INSERT INTO `flyway_schema_history` VALUES ('3', '3', 'Create question table', 'SQL', 'V3__Create_question_table.sql', '1110969280', 'root', '2019-12-01 17:19:00', '270', '1');
INSERT INTO `flyway_schema_history` VALUES ('4', '4', 'Add userface col to user table', 'SQL', 'V4__Add_userface_col_to_user_table.sql', '-1178826457', 'root', '2019-12-01 18:00:14', '57', '1');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `description` text COMMENT '问题描述',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间戳',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间戳',
  `creator` int(11) NOT NULL COMMENT '创建者',
  `comment_count` int(11) DEFAULT NULL COMMENT '评论数',
  `view_count` int(11) DEFAULT NULL COMMENT '浏览数',
  `like_count` int(11) DEFAULT NULL COMMENT '点赞数',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('32', '1', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('33', '2', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('34', '3', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('35', '4', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('36', '5', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('37', '6', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('38', '7', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('39', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('40', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('41', '10', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('42', '12', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('43', '13', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('44', '14', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('45', '15', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('46', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('47', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('48', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('49', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('50', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('51', '20', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('52', '21', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('53', '22', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('54', '23', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('55', '24', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('56', '25', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('57', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('58', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('59', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('60', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('61', '30', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('62', '31', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('63', '32', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('64', '33', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('65', '34', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('66', '35', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('67', '36', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('68', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('69', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('70', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('71', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');
INSERT INTO `question` VALUES ('72', '123', '123', '1575363800414', '1575363800414', '16', null, null, null, '123');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `account_id` varchar(50) NOT NULL COMMENT 'github id',
  `token` char(36) NOT NULL,
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间戳',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间戳',
  `bio` varchar(255) DEFAULT NULL,
  `userface_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'qssq', '37603377', 'd1b66c5f-2db8-4f59-8bd2-7eb1eed477a4', '1575363768549', '1575363768549', null, 'https://avatars2.githubusercontent.com/u/37603377?v=4');
