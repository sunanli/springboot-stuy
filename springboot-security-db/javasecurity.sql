/*
Navicat MySQL Data Transfer
Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : security
Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001
Date: 2018-07-28 15:26:51
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javasecurity` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javasecurity`;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) DEFAULT NULL,
  `nameZh` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'dba', '数据库管理员');
INSERT INTO `role` VALUES ('2', 'admin', '系统管理员');
INSERT INTO `role` VALUES ('3', 'user', '用户');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `enabled` TINYINT(1) DEFAULT NULL,
  `locked` TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '1', '0');
INSERT INTO `user` VALUES ('2', 'admin', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '1', '0');
INSERT INTO `user` VALUES ('3', 'sang', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '1', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `uid` INT(11) DEFAULT NULL,
  `rid` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('3', '2', '2');
INSERT INTO `user_role` VALUES ('4', '3', '3');
SET FOREIGN_KEY_CHECKS=1;