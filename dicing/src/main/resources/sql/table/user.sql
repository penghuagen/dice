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

 Date: 15/09/2021 17:32:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint(20) NOT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `deskNo` int(11) NOT NULL,
  `seqNo` int(11) NOT NULL,
  `ready` bit(1) NULL DEFAULT NULL,
  `userName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avator` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orgName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `idx_deskNo_seqNo`(`deskNo`, `seqNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
