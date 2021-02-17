/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.66_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 192.168.3.66:3306
 Source Schema         : microcloud01

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 17/02/2021 10:28:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dname` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `db_source` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '开发部', 'microcloud01');
INSERT INTO `dept` VALUES (2, '人事部', 'microcloud01');
INSERT INTO `dept` VALUES (3, '财务部', 'microcloud01');
INSERT INTO `dept` VALUES (4, '市场部', 'microcloud01');
INSERT INTO `dept` VALUES (5, '运维部', 'microcloud01');
INSERT INTO `dept` VALUES (6, '监管部', 'microcloud01');
INSERT INTO `dept` VALUES (7, '售后部', 'microcloud01');
INSERT INTO `dept` VALUES (8, '营业部', 'microcloud01');

SET FOREIGN_KEY_CHECKS = 1;
