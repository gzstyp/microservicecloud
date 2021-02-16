/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.166_MySQL5.7.25
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.3.166:3307
 Source Schema         : microcloud01

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 02/05/2019 02:24:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
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

SET FOREIGN_KEY_CHECKS = 1;
