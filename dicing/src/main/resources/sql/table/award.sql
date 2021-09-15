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

 Date: 15/09/2021 17:31:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `awardId` int(11) NOT NULL,
  `awardName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `subName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`awardId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
