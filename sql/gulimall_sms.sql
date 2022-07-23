/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.56.10-chenyi
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.56.10:3306
 Source Schema         : gulimall_sms

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 08/06/2022 20:11:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon`;
CREATE TABLE `sms_coupon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_type` tinyint(1) NULL DEFAULT NULL COMMENT '优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]',
  `coupon_img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券图片',
  `coupon_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠卷名字',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `amount` decimal(18, 4) NULL DEFAULT NULL COMMENT '金额',
  `per_limit` int(11) NULL DEFAULT NULL COMMENT '每人限领张数',
  `min_point` decimal(18, 4) NULL DEFAULT NULL COMMENT '使用门槛',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `use_type` tinyint(1) NULL DEFAULT NULL COMMENT '使用类型[0->全场通用；1->指定分类；2->指定商品]',
  `note` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `publish_count` int(11) NULL DEFAULT NULL COMMENT '发行数量',
  `use_count` int(11) NULL DEFAULT NULL COMMENT '已使用数量',
  `receive_count` int(11) NULL DEFAULT NULL COMMENT '领取数量',
  `enable_start_time` datetime NULL DEFAULT NULL COMMENT '可以领取的开始日期',
  `enable_end_time` datetime NULL DEFAULT NULL COMMENT '可以领取的结束日期',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠码',
  `member_level` tinyint(1) NULL DEFAULT NULL COMMENT '可以领取的会员等级[0->不限等级，其他-对应等级]',
  `publish` tinyint(1) NULL DEFAULT NULL COMMENT '发布状态[0-未发布，1-已发布]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for sms_coupon_history
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_history`;
CREATE TABLE `sms_coupon_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `member_nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员名字',
  `get_type` tinyint(1) NULL DEFAULT NULL COMMENT '获取方式[0->后台赠送；1->主动领取]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `use_type` tinyint(1) NULL DEFAULT NULL COMMENT '使用状态[0->未使用；1->已使用；2->已过期]',
  `use_time` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `order_sn` bigint(20) NULL DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券领取历史记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_coupon_history
-- ----------------------------

-- ----------------------------
-- Table structure for sms_coupon_spu_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_category_relation`;
CREATE TABLE `sms_coupon_spu_category_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券id',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '产品分类id',
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券分类关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_coupon_spu_category_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sms_coupon_spu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon_spu_relation`;
CREATE TABLE `sms_coupon_spu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spu_id',
  `spu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spu_name',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券与产品关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_coupon_spu_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sms_home_adv
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_adv`;
CREATE TABLE `sms_home_adv`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `click_count` int(11) NULL DEFAULT NULL COMMENT '点击数',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '广告详情连接地址',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `publisher_id` bigint(20) NULL DEFAULT NULL COMMENT '发布者',
  `auth_id` bigint(20) NULL DEFAULT NULL COMMENT '审核者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页轮播广告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_home_adv
-- ----------------------------

-- ----------------------------
-- Table structure for sms_home_subject
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject`;
CREATE TABLE `sms_home_subject`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题名字',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题标题',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题副标题',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '显示状态',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情连接',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_home_subject
-- ----------------------------

-- ----------------------------
-- Table structure for sms_home_subject_spu
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_subject_spu`;
CREATE TABLE `sms_home_subject_spu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题名字',
  `subject_id` bigint(20) NULL DEFAULT NULL COMMENT '专题id',
  `spu_id` bigint(20) NULL DEFAULT NULL COMMENT 'spu_id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '专题商品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_home_subject_spu
-- ----------------------------

-- ----------------------------
-- Table structure for sms_member_price
-- ----------------------------
DROP TABLE IF EXISTS `sms_member_price`;
CREATE TABLE `sms_member_price`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `sku_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku_id',
  `member_level_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员等级id',
  `member_level_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员等级名',
  `member_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '会员对应价格',
  `add_other` tinyint(1) NULL DEFAULT NULL COMMENT '可否叠加其他优惠[0-不可叠加优惠，1-可叠加]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品会员价格' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_member_price
-- ----------------------------
INSERT INTO `sms_member_price` VALUES ('1524637734632243202', '1524637734368014338', '1524342749693304833', '铜牌会员', 3680.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734640631810', '1524637734368014338', '1524343023623299073', '银牌会员', 3620.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734766460930', '1524637734678392833', '1524342749693304833', '铜牌会员', 3980.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734766460931', '1524637734678392833', '1524343023623299073', '银牌会员', 3920.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734867124226', '1524637734791639041', '1524342749693304833', '铜牌会员', 4349.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734875512834', '1524637734791639041', '1524343023623299073', '银牌会员', 4299.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734971981825', '1524637734896496641', '1524342749693304833', '铜牌会员', 3680.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637734971981826', '1524637734896496641', '1524343023623299073', '银牌会员', 3620.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735076839426', '1524637735001354241', '1524342749693304833', '铜牌会员', 3980.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735076839427', '1524637735001354241', '1524343023623299073', '银牌会员', 3920.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735194279938', '1524637735102017537', '1524342749693304833', '铜牌会员', 4349.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735202668545', '1524637735102017537', '1524343023623299073', '银牌会员', 4299.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735303331841', '1524637735227846658', '1524342749693304833', '铜牌会员', 3680.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735307526145', '1524637735227846658', '1524343023623299073', '银牌会员', 3620.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735416578050', '1524637735332704257', '1524342749693304833', '铜牌会员', 3980.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735420772353', '1524637735332704257', '1524343023623299073', '银牌会员', 3920.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735525629954', '1524637735445950466', '1524342749693304833', '铜牌会员', 4349.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735525629955', '1524637735445950466', '1524343023623299073', '银牌会员', 4299.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735617904641', '1524637735550808066', '1524342749693304833', '铜牌会员', 3680.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735626293249', '1524637735550808066', '1524343023623299073', '银牌会员', 3620.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735722762242', '1524637735651471361', '1524342749693304833', '铜牌会员', 3980.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735731150849', '1524637735651471361', '1524343023623299073', '银牌会员', 3920.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735827619841', '1524637735756328962', '1524342749693304833', '铜牌会员', 4349.0000, NULL);
INSERT INTO `sms_member_price` VALUES ('1524637735836008449', '1524637735756328962', '1524343023623299073', '银牌会员', 4299.0000, NULL);

-- ----------------------------
-- Table structure for sms_seckill_promotion
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_promotion`;
CREATE TABLE `sms_seckill_promotion`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '上下线状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀活动' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_seckill_promotion
-- ----------------------------

-- ----------------------------
-- Table structure for sms_seckill_session
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_session`;
CREATE TABLE `sms_seckill_session`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '场次名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '每日开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '每日结束时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '启用状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀活动场次' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_seckill_session
-- ----------------------------

-- ----------------------------
-- Table structure for sms_seckill_sku_notice
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_sku_notice`;
CREATE TABLE `sms_seckill_sku_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT 'member_id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku_id',
  `session_id` bigint(20) NULL DEFAULT NULL COMMENT '活动场次id',
  `subcribe_time` datetime NULL DEFAULT NULL COMMENT '订阅时间',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `notice_type` tinyint(1) NULL DEFAULT NULL COMMENT '通知方式[0-短信，1-邮件]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀商品通知订阅' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_seckill_sku_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sms_seckill_sku_relation
-- ----------------------------
DROP TABLE IF EXISTS `sms_seckill_sku_relation`;
CREATE TABLE `sms_seckill_sku_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `promotion_id` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  `promotion_session_id` bigint(20) NULL DEFAULT NULL COMMENT '活动场次id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `seckill_price` decimal(10, 0) NULL DEFAULT NULL COMMENT '秒杀价格',
  `seckill_count` decimal(10, 0) NULL DEFAULT NULL COMMENT '秒杀总量',
  `seckill_limit` decimal(10, 0) NULL DEFAULT NULL COMMENT '每人限购数量',
  `seckill_sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '秒杀活动商品关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_seckill_sku_relation
-- ----------------------------

-- ----------------------------
-- Table structure for sms_sku_full_reduction
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_full_reduction`;
CREATE TABLE `sms_sku_full_reduction`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `sku_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spu_id',
  `full_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '满多少',
  `reduce_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '减多少',
  `add_other` tinyint(1) NULL DEFAULT NULL COMMENT '是否参与其他优惠',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品满减信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_sku_full_reduction
-- ----------------------------
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637734628048897', '1524637734368014338', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637734753878017', '1524637734678392833', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637734854541314', '1524637734791639041', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637734963593217', '1524637734896496641', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735064256513', '1524637735001354241', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735181697026', '1524637735102017537', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735290748930', '1524637735227846658', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735408189442', '1524637735332704257', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735517241346', '1524637735445950466', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735613710337', '1524637735550808066', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735718567937', '1524637735651471361', 3000.0000, 200.0000, NULL);
INSERT INTO `sms_sku_full_reduction` VALUES ('1524637735823425537', '1524637735756328962', 3000.0000, 200.0000, NULL);

-- ----------------------------
-- Table structure for sms_sku_ladder
-- ----------------------------
DROP TABLE IF EXISTS `sms_sku_ladder`;
CREATE TABLE `sms_sku_ladder`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `sku_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spu_id',
  `full_count` int(11) NULL DEFAULT NULL COMMENT '满几件',
  `discount` decimal(4, 2) NULL DEFAULT NULL COMMENT '打几折',
  `price` decimal(18, 4) NULL DEFAULT NULL COMMENT '折后价',
  `add_other` tinyint(1) NULL DEFAULT NULL COMMENT '是否叠加其他优惠[0-不可叠加，1-可叠加]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品阶梯价格' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_sku_ladder
-- ----------------------------
INSERT INTO `sms_sku_ladder` VALUES ('1524637734607077377', '1524637734368014338', 1, 0.99, 200.0000, 0);

-- ----------------------------
-- Table structure for sms_spu_bounds
-- ----------------------------
DROP TABLE IF EXISTS `sms_spu_bounds`;
CREATE TABLE `sms_spu_bounds`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `spu_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grow_bounds` decimal(18, 4) NULL DEFAULT NULL COMMENT '成长积分',
  `buy_bounds` decimal(18, 4) NULL DEFAULT NULL COMMENT '购物积分',
  `work` tinyint(1) NULL DEFAULT NULL COMMENT '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品spu积分设置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_spu_bounds
-- ----------------------------
INSERT INTO `sms_spu_bounds` VALUES ('1524637735890534402', '1524637731717214209', 100.0000, 100.0000, NULL);

SET FOREIGN_KEY_CHECKS = 1;
