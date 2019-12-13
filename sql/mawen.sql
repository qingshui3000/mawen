/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : mawen

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-12-13 20:13:42
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
  `commentor` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `like_count` int(11) DEFAULT '0',
  `content` text NOT NULL,
  `comment_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '73', '1', '16', '1575685508768', '1575685508768', null, '回复test', '0');
INSERT INTO `comment` VALUES ('2', '73', '1', '16', '1575685536501', '1575685536501', null, '回复test', '0');
INSERT INTO `comment` VALUES ('3', '42', '1', '16', '1575879136659', '1575879136659', null, '回复test', '0');
INSERT INTO `comment` VALUES ('4', '42', '1', '16', '1575879144674', '1575879144674', null, '回复test', '0');
INSERT INTO `comment` VALUES ('5', '37', '1', '16', '1575879256577', '1575879256577', null, '回复test', '0');
INSERT INTO `comment` VALUES ('6', '37', '1', '16', '1575879470192', '1575879470192', null, '回复test', '0');
INSERT INTO `comment` VALUES ('7', '37', '1', '16', '1575879481264', '1575879481264', null, '回复test', '0');
INSERT INTO `comment` VALUES ('8', '37', '1', '16', '1575879481425', '1575879481425', null, '回复test', '0');
INSERT INTO `comment` VALUES ('9', '37', '1', '16', '1575879481603', '1575879481603', null, '回复test', '0');
INSERT INTO `comment` VALUES ('10', '37', '1', '16', '1575879482150', '1575879482150', null, '回复test', '0');
INSERT INTO `comment` VALUES ('11', '3', '2', '16', '1575879593219', '1575879593219', null, '回复test', null);
INSERT INTO `comment` VALUES ('13', '37', '1', '16', '1575884402740', '1575884402740', null, '123', '0');
INSERT INTO `comment` VALUES ('14', '37', '1', '16', '1575884873510', '1575884873510', null, '123', '0');
INSERT INTO `comment` VALUES ('15', '37', '1', '16', '1575884900320', '1575884900320', null, '123', '0');
INSERT INTO `comment` VALUES ('16', '37', '1', '16', '1575940570188', '1575940570188', null, '132', '0');
INSERT INTO `comment` VALUES ('17', '37', '1', '16', '1575940926682', '1576052304477', null, '123', '0');
INSERT INTO `comment` VALUES ('18', '37', '1', '16', '1575940968735', '1575940968735', null, '123', '0');
INSERT INTO `comment` VALUES ('19', '74', '1', '16', '1575946442959', '1575946442959', null, '测试', '0');
INSERT INTO `comment` VALUES ('20', '74', '1', '16', '1575964771462', '1575964771462', null, '', '0');
INSERT INTO `comment` VALUES ('21', '74', '1', '16', '1575965325701', '1575965325701', null, '123', '0');
INSERT INTO `comment` VALUES ('22', '37', '1', '16', '1575965708153', '1575965708153', null, '12/10新回复\n', '0');
INSERT INTO `comment` VALUES ('23', '37', '1', '16', '1576035975267', '1576052292476', null, '12/11 二级回复test\n', '1');
INSERT INTO `comment` VALUES ('24', '23', '2', '16', '1576035975267', '1576035975267', '0', 'subcomment test', null);
INSERT INTO `comment` VALUES ('25', '23', '2', '16', '1576039444849', '1576039444849', null, 'test2', null);
INSERT INTO `comment` VALUES ('26', '23', '2', '16', '1576051930839', '1576051930839', null, 'test2', null);
INSERT INTO `comment` VALUES ('27', '76', '1', '16', '1576052199955', '1576052212387', null, '123', '3');
INSERT INTO `comment` VALUES ('28', '27', '2', '16', '1576052203884', '1576052203884', null, '123', null);
INSERT INTO `comment` VALUES ('29', '27', '2', '16', '1576052208130', '1576052208130', null, '1233', null);
INSERT INTO `comment` VALUES ('30', '27', '2', '16', '1576052212376', '1576052212376', null, '12333', null);
INSERT INTO `comment` VALUES ('31', '23', '2', '16', '1576052245885', '1576052245885', null, '1', null);
INSERT INTO `comment` VALUES ('32', '23', '2', '16', '1576052292469', '1576052292469', null, '1', null);
INSERT INTO `comment` VALUES ('33', '17', '2', '16', '1576052304471', '1576052304471', null, '!', null);

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
INSERT INTO `flyway_schema_history` VALUES ('5', '5', 'Update all count default to question table', 'SQL', 'V5__Update_all_count_default_to_question_table.sql', '-1745576404', 'root', '2019-12-07 09:56:18', '61', '1');
INSERT INTO `flyway_schema_history` VALUES ('6', '6', 'Create comment table', 'SQL', 'V6__Create_comment_table.sql', '1657519270', 'root', '2019-12-07 10:12:03', '108', '1');
INSERT INTO `flyway_schema_history` VALUES ('7', '7', 'Add content col to comment table', 'SQL', 'V7__Add_content_col_to_comment_table.sql', '2067200693', 'root', '2019-12-07 10:12:03', '51', '1');
INSERT INTO `flyway_schema_history` VALUES ('8', '8', 'Update all pk col', 'SQL', 'V8__Update_all_pk_col.sql', '-323595933', 'root', '2019-12-09 15:47:57', '152', '1');
INSERT INTO `flyway_schema_history` VALUES ('9', '9', 'Update content col to comment table', 'SQL', 'V9__Update_content_col_to_comment_table.sql', '-1631282934', 'root', '2019-12-10 11:00:13', '130', '1');
INSERT INTO `flyway_schema_history` VALUES ('10', '10', 'Add comment count col to comment table', 'SQL', 'V10__Add_comment_count_col_to_comment_table.sql', '1121378015', 'root', '2019-12-11 15:54:19', '81', '1');

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
  `creator` int(11) NOT NULL COMMENT '创建者',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `like_count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('32', '1', '123', '1575363800414', '1575363800414', '16', '0', '1', '0', '123');
INSERT INTO `question` VALUES ('33', '2', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('34', '3', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('35', '4', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('36', '5', '123', '1575363800414', '1575363800414', '16', '0', '2', '0', '123');
INSERT INTO `question` VALUES ('37', '6vc111', '12321', '1575363800414', '1576035975282', '16', '13', '149', '0', '123');
INSERT INTO `question` VALUES ('38', '7', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('39', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('40', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('41', '10', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('42', '12', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('43', '13', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('44', '14', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('45', '15', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('46', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('47', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('48', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('49', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('50', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('51', '20', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('52', '21', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('53', '22', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('54', '23', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('55', '24', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('56', '25', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('57', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('58', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('59', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('60', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('61', '30', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('62', '31', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('63', '32', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('64', '33', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('65', '34', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('66', '35', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('67', '36', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', 'Spring');
INSERT INTO `question` VALUES ('68', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('69', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('70', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('71', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('72', '123', '123', '1575363800414', '1575363800414', '16', '0', '0', '0', '123');
INSERT INTO `question` VALUES ('73', 'Spring Boot v2.0 Test', '如题，Spring Boot v2.0 Test', '1575450917696', '1575450917696', '16', '0', '17', '0', 'Spring');
INSERT INTO `question` VALUES ('74', 'Haha1', 'test1', '1575536343197', '1575965325712', '19', '3', '27', '0', 'Spring');
INSERT INTO `question` VALUES ('75', '问题发布测试', '问题描述', '1575964346346', '1575964346346', '16', '0', '1', '0', 'Spring');
INSERT INTO `question` VALUES ('76', '发布问题测试', '问题描述。。。。', '1575964374062', '1576052199972', '16', '1', '20', '0', 'Spring');
INSERT INTO `question` VALUES ('77', 'tags 测试', '蛤蛤蛤', '1576053647211', '1576053647211', '16', '0', '41', '0', 'Spring,Css,Java,Mybatis');
INSERT INTO `question` VALUES ('78', '12322', '123', '1576143732255', '1576143732255', '16', '0', '4', '0', 'javascript,php,css');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'qssq', '37603377', 'c7dc5477-b149-446b-9bb4-00654466f35f', '1575363768549', '1576230076409', '', 'https://avatars2.githubusercontent.com/u/37603377?v=4');
INSERT INTO `user` VALUES ('19', 'test', '1111', '1111', '1111', '1111', '', '');
