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

 Date: 15/09/2021 17:32:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dicinglog
-- ----------------------------
DROP TABLE IF EXISTS `dicinglog`;
CREATE TABLE `dicinglog`  (
  `dicingLogId` int(11) NOT NULL AUTO_INCREMENT,
  `awardId` int(11) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `roundNo` int(11) NULL DEFAULT NULL,
  `dicingTime` datetime NULL DEFAULT NULL,
  `valid` bit(1) NULL DEFAULT NULL,
  `dices` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deskNo` int(11) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT 0,
  `dicesOut` char(29) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `voiceIndex` int(11) NULL DEFAULT NULL,
  `systemDicing` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dicingLogId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6087 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
