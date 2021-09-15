/*
 Navicat Premium Data Transfer

 Source Server         : 47.114.141.192
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : 47.114.141.192:3306
 Source Schema         : dicing

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 15/09/2021 17:32:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for deskaward
-- ----------------------------
DROP TABLE IF EXISTS `deskaward`;
CREATE TABLE `deskaward`  (
  `deskAwardId` int(11) NOT NULL AUTO_INCREMENT,
  `awardId` int(20) NOT NULL,
  `counter` int(11) NULL DEFAULT NULL,
  `remaining` int(11) NULL DEFAULT NULL,
  `deskNo` int(11) NOT NULL,
  PRIMARY KEY (`deskAwardId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1160 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
