/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.56.10
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.56.10:3306
 Source Schema         : chenyimall_wms

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 26/07/2022 20:38:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for wms_purchase
-- ----------------------------
DROP TABLE IF EXISTS `wms_purchase`;
CREATE TABLE `wms_purchase`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignee_id` bigint(20) NULL DEFAULT NULL,
  `assignee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `priority` int(4) NULL DEFAULT NULL,
  `status` int(4) NULL DEFAULT NULL,
  `ware_id` bigint(20) NULL DEFAULT NULL,
  `amount` decimal(18, 4) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_purchase
-- ----------------------------

-- ----------------------------
-- Table structure for wms_purchase_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_purchase_detail`;
CREATE TABLE `wms_purchase_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint(20) NULL DEFAULT NULL COMMENT '采购单id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '采购商品id',
  `sku_num` int(11) NULL DEFAULT NULL COMMENT '采购数量',
  `sku_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '采购金额',
  `ware_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_purchase_detail
-- ----------------------------

-- ----------------------------
-- Table structure for wms_ware_info
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_info`;
CREATE TABLE `wms_ware_info`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库地址',
  `areacode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '仓库信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_ware_info
-- ----------------------------
INSERT INTO `wms_ware_info` VALUES ('1524999847926099969', '长沙一号仓库', '长沙一号仓库地址', '43213');
INSERT INTO `wms_ware_info` VALUES ('1525000175354441729', '深圳一号仓库', '深圳一号仓库地址', '5461321');
INSERT INTO `wms_ware_info` VALUES ('1525000279725502465', '深圳二号仓库', '深圳二号仓库地址', '654653');
INSERT INTO `wms_ware_info` VALUES ('1525000341364994050', '广州一号仓库', '广州一号仓库地址', '3246354');
INSERT INTO `wms_ware_info` VALUES ('1525000413850955778', '广州二号仓库', '广州二号仓库地址', '3465431');
INSERT INTO `wms_ware_info` VALUES ('1525000557719777281', '北京一号仓库', '北京一号仓库地址', '6456135');
INSERT INTO `wms_ware_info` VALUES ('1525000610102439937', '北京二号仓库', '北京二号仓库地址', '456454');
INSERT INTO `wms_ware_info` VALUES ('1525000715245252610', '上海一号仓库', '上海一号仓库地址', '7964643');
INSERT INTO `wms_ware_info` VALUES ('1525000867729174530', '武汉一号仓库', '武汉一号仓库地址', '896564');

-- ----------------------------
-- Table structure for wms_ware_order_task
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_order_task`;
CREATE TABLE `wms_ware_order_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT 'order_id',
  `order_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'order_sn',
  `consignee` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `consignee_tel` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `delivery_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配送地址',
  `order_comment` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `payment_way` tinyint(1) NULL DEFAULT NULL COMMENT '付款方式【 1:在线付款 2:货到付款】',
  `task_status` tinyint(2) NULL DEFAULT NULL COMMENT '任务状态',
  `order_body` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单描述',
  `tracking_no` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `create_time` datetime NULL DEFAULT NULL COMMENT 'create_time',
  `ware_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `task_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作单备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存工作单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_ware_order_task
-- ----------------------------

-- ----------------------------
-- Table structure for wms_ware_order_task_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_order_task_detail`;
CREATE TABLE `wms_ware_order_task_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku_name',
  `sku_num` int(11) NULL DEFAULT NULL COMMENT '购买个数',
  `task_id` bigint(20) NULL DEFAULT NULL COMMENT '工作单id',
  `ware_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `lock_status` int(1) NULL DEFAULT NULL COMMENT '1-已锁定  2-已解锁  3-扣减',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存工作单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_ware_order_task_detail
-- ----------------------------

-- ----------------------------
-- Table structure for wms_ware_sku
-- ----------------------------
DROP TABLE IF EXISTS `wms_ware_sku`;
CREATE TABLE `wms_ware_sku`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `sku_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku_id',
  `ware_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库id',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存数',
  `sku_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku_name',
  `stock_locked` int(11) NULL DEFAULT 0 COMMENT '锁定库存',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sku_id`(`sku_id`) USING BTREE,
  INDEX `ware_id`(`ware_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品库存' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_ware_sku
-- ----------------------------
INSERT INTO `wms_ware_sku` VALUES ('1525025194415550465', '1524637734368014338', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 黑色 8GB+128GB5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525076292413272065', '1524637734678392833', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 黑色 8GB+256GB5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525076504422756353', '1524637734791639041', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 黑色 12GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525076564518744065', '1524637734896496641', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 蓝色 8GB+128GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525076742948630530', '1524637735001354241', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 蓝色 8GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525076993289859073', '1524637735102017537', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 蓝色 12GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077055738851329', '1524637735227846658', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 紫色 8GB+128GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077166208430082', '1524637735332704257', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 紫色 8GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077297611780097', '1524637735445950466', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 紫色 12GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077849913536513', '1524637735550808066', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 原野绿（素皮） 8GB+128GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077932054786049', '1524637735651471361', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 原野绿（素皮） 8GB+256GB 5G手机', 0);
INSERT INTO `wms_ware_sku` VALUES ('1525077979332980738', '1524637735756328962', '1524999847926099969', 10000, '小米12 骁龙8 Gen1 黄金手感 6.28英寸视感屏 120Hz高刷 5000万疾速影像 67W快充 原野绿（素皮） 12GB+256GB 5G手机', 0);

SET FOREIGN_KEY_CHECKS = 1;
