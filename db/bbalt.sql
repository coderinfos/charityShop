/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : bbalt

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-12-30 10:12:15
*/


SET FOREIGN_KEY_CHECKS=0;

drop  DATABASE IF  EXISTS `bbalt` ;
CREATE DATABASE IF NOT EXISTS `bbalt` ;
USE `bbalt`;
-- ----------------------------
-- Table structure for ba_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `ba_admin_user`;
CREATE TABLE `ba_admin_user` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(300) DEFAULT NULL COMMENT '密码',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `wechat_id` varchar(50) DEFAULT NULL COMMENT '微信id',
  `user_id` bigint(20) DEFAULT '0' COMMENT '用户id 默认0',
  `boss_model` tinyint(4) DEFAULT NULL COMMENT '默认为0为false,1为true',
  `pre_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `pre_login_ip` varchar(50) DEFAULT NULL COMMENT '上一次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后一次登录IP',
  `verification_code` int(11) DEFAULT NULL COMMENT '验证码',
  `memo` varchar(400) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员账号表';

-- ----------------------------
-- Records of ba_admin_user
-- ----------------------------

-- ----------------------------
-- Table structure for ba_boss
-- ----------------------------
DROP TABLE IF EXISTS `ba_boss`;
CREATE TABLE `ba_boss` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL COMMENT '捐赠人id',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '报名分店id',
  `duty_date` datetime DEFAULT NULL COMMENT '值班日期',
  `duty_type` tinyint(4) DEFAULT NULL COMMENT '1早班2晚班',
  `duty_status` tinyint(4) DEFAULT NULL COMMENT '0为false,1为true审核通过(默认自动',
  `duty_submit_time` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operator_ip` varchar(50) DEFAULT NULL,
  `memo` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店长排班表';

-- ----------------------------
-- Records of ba_boss
-- ----------------------------

-- ----------------------------
-- Table structure for ba_donate
-- ----------------------------
DROP TABLE IF EXISTS `ba_donate`;
CREATE TABLE `ba_donate` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '捐赠人id',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '捐赠至分店id',
  `donate_name` varchar(50) DEFAULT NULL,
  `donate_type` tinyint(4) DEFAULT NULL COMMENT '1物品,2书籍',
  `donate_image` varchar(200) DEFAULT NULL,
  `donate_price` int(11) DEFAULT NULL,
  `donate_submit_time` datetime DEFAULT NULL,
  `donate_register_time` datetime DEFAULT NULL,
  `donate_sale_time` datetime DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operator_ip` varchar(50) DEFAULT NULL,
  `memo` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='捐赠表';

-- ----------------------------
-- Records of ba_donate
-- ----------------------------

-- ----------------------------
-- Table structure for ba_operationl_log
-- ----------------------------
DROP TABLE IF EXISTS `ba_operationl_log`;
CREATE TABLE `ba_operationl_log` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `operator` int(11) DEFAULT NULL,
  `operation_type` int(11) DEFAULT NULL,
  `content` text,
  `user_id` bigint(20) DEFAULT NULL COMMENT '捐赠人Id',
  `operation_time` datetime DEFAULT NULL,
  `operator_ip` varchar(50) DEFAULT NULL,
  `memo` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of ba_operationl_log
-- ----------------------------

-- ----------------------------
-- Table structure for ba_shop
-- ----------------------------
DROP TABLE IF EXISTS `ba_shop`;
CREATE TABLE `ba_shop` (
  `id` bigint(20) NOT NULL,
  `shop_name` varchar(50) DEFAULT '',
  `address` varchar(200) DEFAULT '',
  `operator` int(11) DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operator_ip` varchar(50) DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分店管理';

-- ----------------------------
-- Records of ba_shop
-- ----------------------------

-- ----------------------------
-- Table structure for ba_user
-- ----------------------------
DROP TABLE IF EXISTS `ba_user`;
CREATE TABLE `ba_user` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `face` varchar(400) DEFAULT NULL,
  `face_open` tinyint(4) DEFAULT NULL COMMENT 'face是否公开,1为true,0为false',
  `nick_name` varchar(50) DEFAULT '0' COMMENT '昵称',
  `nick_name_open` tinyint(4) DEFAULT NULL COMMENT '昵称是否公开,1为true,0为false',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `real_name_open` tinyint(4) DEFAULT NULL COMMENT '真实姓名是否公开,1为true,0为false',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `sex_open` tinyint(4) DEFAULT NULL COMMENT '性别是否公开 ,1为true,0为false',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  `age_open` tinyint(4) DEFAULT NULL COMMENT '年龄是否公开 ,1为true,0为false',
  `mobile_phone` bigint(20) DEFAULT NULL COMMENT '电话号',
  `mobile_phone_open` tinyint(4) DEFAULT NULL COMMENT '电话号是否公开,1为true,0为false',
  `signature_line` varchar(500) DEFAULT NULL COMMENT ' 签名,限制字数120字以内',
  `signature_line_open` tinyint(4) DEFAULT NULL COMMENT '签名是否公开,1为true,0为false',
  `wechat_id` varchar(50) DEFAULT NULL COMMENT '微信id',
  `verification_code` int(11) DEFAULT NULL COMMENT '验证码',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `pre_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `pre_login_ip` varchar(500) DEFAULT NULL COMMENT '上一次登录地址ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `last_login_ip` varchar(200) DEFAULT NULL COMMENT '最后一次登录地址ip',
  `memo` varchar(600) DEFAULT NULL COMMENT '备忘录',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基本用户信息表';

-- ----------------------------
-- Records of ba_user
-- ----------------------------
INSERT INTO `ba_user` VALUES ('1', 'admin', '123456', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root', '13612345678', '1', '1', '2016-11-11 11:11:11');
