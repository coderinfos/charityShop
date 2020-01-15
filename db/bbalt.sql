/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : bbalt

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-15 10:59:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ba_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `ba_admin_user`;
CREATE TABLE `ba_admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '捐赠人ID',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '报名分店ID',
  `duty_date` datetime DEFAULT NULL COMMENT '值班日期',
  `duty_type` tinyint(4) DEFAULT NULL COMMENT '1早班2晚班',
  `duty_status` tinyint(4) DEFAULT NULL COMMENT '0为false,1为true审核通过(默认自动',
  `duty_submit_time` datetime DEFAULT NULL COMMENT '审核提交时间',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='店长排班表';

-- ----------------------------
-- Records of ba_boss
-- ----------------------------
INSERT INTO `ba_boss` VALUES ('1', '1', '1', '2020-01-13 14:07:54', '1', '1', '2020-01-13 14:08:01', '1', '2020-01-13 14:08:09', '192.168.1.1', '无备注,修改数据');

-- ----------------------------
-- Table structure for ba_donate
-- ----------------------------
DROP TABLE IF EXISTS `ba_donate`;
CREATE TABLE `ba_donate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', 'xxxz组织', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', 'xxx上海分部1', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', 'xxx上海分部2', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '商店1', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '商店2', '1', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存菜单', 'org.greencode.modules.sys.controller.SysMenuController.save()', '{\"menuId\":41,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"商店后台系统\",\"type\":0,\"icon\":\"fa fa-desktop\",\"orderNum\":0}', '68', '127.0.0.1', '2020-01-13 13:14:07');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存菜单', 'org.greencode.modules.sys.controller.SysMenuController.save()', '{\"menuId\":42,\"parentId\":0,\"parentName\":\"一级菜单\",\"name\":\"商店管理后台\",\"type\":0,\"icon\":\"fa fa-desktop\",\"orderNum\":0}', '46', '127.0.0.1', '2020-01-13 13:32:46');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改用户', 'org.greencode.modules.sys.controller.SysUserController.update()', '{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@163.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createTime\":\"Nov 11, 2016 11:11:11 AM\",\"deptId\":1,\"deptName\":\"xxxz组织\"}', '468', '127.0.0.1', '2020-01-15 10:51:51');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '1', '字典管理', 'modules/sys/dict.html', null, '1', 'fa fa-bookmark-o', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'sys:dict:list,sys:dict:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:dict:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:dict:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:dict:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('42', '0', '商店管理后台', null, null, '0', 'fa fa-desktop', '0');
INSERT INTO `sys_menu` VALUES ('43', '42', '分店管理', 'modules/app/shop.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('44', '43', '查看', null, 'sys:shop:list,sys:shop:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('45', '43', '新增', null, 'sys:shop:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('46', '43', '修改', null, 'sys:shop:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('47', '43', '删除', null, 'sys:shop:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('48', '42', '操作日志表', 'modules/app/operationllog.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('49', '48', '查看', null, 'sys:operationllog:list,sys:operationllog:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('50', '48', '新增', null, 'sys:operationllog:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('51', '48', '修改', null, 'sys:operationllog:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('52', '48', '删除', null, 'sys:operationllog:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('53', '42', '捐赠表', 'modules/app/donate.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('54', '53', '查看', null, 'sys:donate:list,sys:donate:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('55', '53', '新增', null, 'sys:donate:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('56', '53', '修改', null, 'sys:donate:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('57', '53', '删除', null, 'sys:donate:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('58', '42', '店长排班表', 'modules/app/boss.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('59', '58', '查看', null, 'sys:boss:list,sys:boss:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('60', '58', '新增', null, 'sys:boss:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('61', '58', '修改', null, 'sys:boss:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('62', '58', '删除', null, 'sys:boss:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('63', '42', '管理员账号表', 'modules/sys/adminuser.html', null, '1', 'fa fa-file-code-o', '6');
INSERT INTO `sys_menu` VALUES ('64', '63', '查看', null, 'sys:adminuser:list,sys:adminuser:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('65', '63', '新增', null, 'sys:adminuser:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('66', '63', '修改', null, 'sys:adminuser:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('67', '63', '删除', null, 'sys:adminuser:delete', '2', null, '6');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', null, null, '2020-01-13 13:23:07');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
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
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@163.com', '13612345678', '1', '1', '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '156123456789', '123456', '2020-01-13 13:34:42');
