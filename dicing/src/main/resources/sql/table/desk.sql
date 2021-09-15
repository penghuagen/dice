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

 Date: 15/09/2021 17:32:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for desk
-- ----------------------------
DROP TABLE IF EXISTS `desk`;
CREATE TABLE `desk`  (
  `deskId` int(11) NOT NULL AUTO_INCREMENT,
  `deskNo` int(11) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `roundNo` int(11) NULL DEFAULT NULL,
  `curSeqNo` int(11) NULL DEFAULT NULL,
  `noRemainingRoundNo` int(11) NULL DEFAULT -1,
  `startTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`deskId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
