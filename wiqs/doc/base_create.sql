/*
 Navicat MySQL Data Transfer

 Source Server         : wiqsdb
 Source Server Type    : MySQL
 Source Server Version : 50524
 Source Host           : 57ea3524964c9.sh.cdb.myqcloud.com
 Source Database       : wiqsdb

 Target Server Type    : MySQL
 Target Server Version : 50524
 File Encoding         : utf-8

 Date: 05/10/2017 16:22:10 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `dict_id` varchar(32) NOT NULL,
  `dict_type` varchar(10) NOT NULL COMMENT '字典类型',
  `dict_dkey` varchar(10) NOT NULL COMMENT 'Key',
  `dict_value` varchar(32) NOT NULL COMMENT '字典值',
  `text` varchar(64) NOT NULL COMMENT '说明',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_index1` (`dict_type`,`dict_dkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表-dict';

-- ----------------------------
--  Table structure for `org`
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `org_id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `org_no` varchar(32) DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(128) NOT NULL COMMENT '机构名称',
  `org_pid` varchar(32) NOT NULL COMMENT '上级机构ID',
  `org_type` varchar(10) DEFAULT NULL COMMENT '机构类型',
  `contant` varchar(256) DEFAULT NULL COMMENT '备注/联系方式',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
--  Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `per_id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `per_name` varchar(128) NOT NULL COMMENT '资源名称',
  `per_type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `per_url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` varchar(32) DEFAULT NULL COMMENT '父结点id(UUID)',
  `parentids` varchar(132) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(64) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`per_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源权限表';

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `role_is_lock` char(1) DEFAULT NULL COMMENT '账号是否锁定，0：可用，1：锁定',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
--  Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `per_id` varchar(32) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(32) NOT NULL COMMENT '主键',
  `org_id` varchar(32) NOT NULL COMMENT '部门ID',
  `user_name` varchar(32) NOT NULL COMMENT '登陆名',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `password` varchar(32) NOT NULL COMMENT '密码(密文)',
  `address` varchar(128) NOT NULL COMMENT '联系方式',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `locked` char(1) DEFAULT NULL COMMENT '账号是否锁定，0：可用，1：锁定',
  `sex` char(1) DEFAULT NULL COMMENT '性别0未知；1：男；2:女。',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `is_state` char(1) DEFAULT NULL COMMENT '状态',
  `id_type` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_num` varchar(20) DEFAULT NULL COMMENT '证件号码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

SET FOREIGN_KEY_CHECKS = 1;
