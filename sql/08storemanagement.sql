/*
 Navicat Premium Data Transfer

 Source Server         : testmmysql
 Source Server Type    : MySQL
 Source Server Version : 100605
 Source Host           : localhost:3306
 Source Schema         : 08storemanagement

 Target Server Type    : MySQL
 Target Server Version : 100605
 File Encoding         : 65001

 Date: 12/06/2023 22:44:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `VIPNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (3, '333', '3334', '3335');
INSERT INTO `customer` VALUES (4, '4445', '4446', '456456465');
INSERT INTO `customer` VALUES (5, 'VIP', '张三', '517517517517');
INSERT INTO `customer` VALUES (6, 'qw', 'wertwe', '124567890');
INSERT INTO `customer` VALUES (7, 'vip', 'ls', '123234545');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `storeNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商店号',
  `storeName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商店名',
  `model` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规格',
  `price` float(10, 2) NOT NULL COMMENT '单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'qwe', 'wert', 'rtyrty', 4564.00);
INSERT INTO `goods` VALUES (2, 'qwert', '苹果', '菠萝牌', 5.00);

-- ----------------------------
-- Table structure for salesperson
-- ----------------------------
DROP TABLE IF EXISTS `salesperson`;
CREATE TABLE `salesperson`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识符',
  `workNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salesperson
-- ----------------------------
INSERT INTO `salesperson` VALUES (1, 'admin', 'admin');
INSERT INTO `salesperson` VALUES (2, 'hhhh', 'hhhh');

SET FOREIGN_KEY_CHECKS = 1;
