/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.56.10-chenyi
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.56.10:3306
 Source Schema         : gulimall_admin

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 08/06/2022 20:10:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', NULL, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000017D8D893FD07874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('RenrenScheduler', 'zixuan1652421463978', 1652455832835, 15000);

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
INSERT INTO `QRTZ_TRIGGERS` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1638788400000, -1, 5, 'WAITING', 'CRON', 1638787457000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000017D8D893FD07874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'testTask', 'renren', '0 0/30 * * * ?', 0, '参数测试', '2021-12-06 10:18:42');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 269 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES (1, 1, 'testTask', 'renren', 0, NULL, 1, '2021-12-06 19:00:00');
INSERT INTO `schedule_job_log` VALUES (2, 1, 'testTask', 'renren', 0, NULL, 2, '2021-12-07 15:30:00');
INSERT INTO `schedule_job_log` VALUES (3, 1, 'testTask', 'renren', 0, NULL, 3, '2021-12-07 16:00:00');
INSERT INTO `schedule_job_log` VALUES (4, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 01:30:00');
INSERT INTO `schedule_job_log` VALUES (5, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 16:00:00');
INSERT INTO `schedule_job_log` VALUES (6, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 16:30:00');
INSERT INTO `schedule_job_log` VALUES (7, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-08 17:00:00');
INSERT INTO `schedule_job_log` VALUES (8, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 17:30:00');
INSERT INTO `schedule_job_log` VALUES (9, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-08 18:00:00');
INSERT INTO `schedule_job_log` VALUES (10, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 18:30:00');
INSERT INTO `schedule_job_log` VALUES (11, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-08 19:00:00');
INSERT INTO `schedule_job_log` VALUES (12, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 19:30:00');
INSERT INTO `schedule_job_log` VALUES (13, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-08 20:00:00');
INSERT INTO `schedule_job_log` VALUES (14, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-08 20:30:00');
INSERT INTO `schedule_job_log` VALUES (15, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 21:00:00');
INSERT INTO `schedule_job_log` VALUES (16, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 21:30:00');
INSERT INTO `schedule_job_log` VALUES (17, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 22:00:00');
INSERT INTO `schedule_job_log` VALUES (18, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 22:30:00');
INSERT INTO `schedule_job_log` VALUES (19, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 23:00:00');
INSERT INTO `schedule_job_log` VALUES (20, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-08 23:30:00');
INSERT INTO `schedule_job_log` VALUES (21, 1, 'testTask', 'renren', 0, NULL, 4, '2022-02-09 00:00:00');
INSERT INTO `schedule_job_log` VALUES (22, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 00:30:00');
INSERT INTO `schedule_job_log` VALUES (23, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 01:00:00');
INSERT INTO `schedule_job_log` VALUES (24, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 01:30:00');
INSERT INTO `schedule_job_log` VALUES (25, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 02:00:00');
INSERT INTO `schedule_job_log` VALUES (26, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 17:00:00');
INSERT INTO `schedule_job_log` VALUES (27, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 17:30:00');
INSERT INTO `schedule_job_log` VALUES (28, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 18:00:00');
INSERT INTO `schedule_job_log` VALUES (29, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 18:30:00');
INSERT INTO `schedule_job_log` VALUES (30, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 19:00:00');
INSERT INTO `schedule_job_log` VALUES (31, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 19:30:00');
INSERT INTO `schedule_job_log` VALUES (32, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 20:00:00');
INSERT INTO `schedule_job_log` VALUES (33, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 20:30:00');
INSERT INTO `schedule_job_log` VALUES (34, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 21:00:00');
INSERT INTO `schedule_job_log` VALUES (35, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 21:30:00');
INSERT INTO `schedule_job_log` VALUES (36, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 22:00:00');
INSERT INTO `schedule_job_log` VALUES (37, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 22:30:00');
INSERT INTO `schedule_job_log` VALUES (38, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-09 23:00:00');
INSERT INTO `schedule_job_log` VALUES (39, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-09 23:30:00');
INSERT INTO `schedule_job_log` VALUES (40, 1, 'testTask', 'renren', 0, NULL, 5, '2022-02-10 00:00:00');
INSERT INTO `schedule_job_log` VALUES (41, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-10 00:30:00');
INSERT INTO `schedule_job_log` VALUES (42, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-10 01:00:00');
INSERT INTO `schedule_job_log` VALUES (43, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-11 23:30:00');
INSERT INTO `schedule_job_log` VALUES (44, 1, 'testTask', 'renren', 0, NULL, 3, '2022-02-12 00:00:00');
INSERT INTO `schedule_job_log` VALUES (45, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-12 00:30:00');
INSERT INTO `schedule_job_log` VALUES (46, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-12 01:00:00');
INSERT INTO `schedule_job_log` VALUES (47, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-12 18:30:00');
INSERT INTO `schedule_job_log` VALUES (48, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-12 19:00:00');
INSERT INTO `schedule_job_log` VALUES (49, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-13 15:30:00');
INSERT INTO `schedule_job_log` VALUES (50, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-13 16:00:00');
INSERT INTO `schedule_job_log` VALUES (51, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-13 16:30:00');
INSERT INTO `schedule_job_log` VALUES (52, 1, 'testTask', 'renren', 0, NULL, 3, '2022-02-13 22:00:00');
INSERT INTO `schedule_job_log` VALUES (53, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-13 22:30:00');
INSERT INTO `schedule_job_log` VALUES (54, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-13 23:00:00');
INSERT INTO `schedule_job_log` VALUES (55, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-13 23:30:00');
INSERT INTO `schedule_job_log` VALUES (56, 1, 'testTask', 'renren', 0, NULL, 57, '2022-02-14 00:00:00');
INSERT INTO `schedule_job_log` VALUES (57, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-14 00:30:00');
INSERT INTO `schedule_job_log` VALUES (58, 1, 'testTask', 'renren', 0, NULL, 3, '2022-02-14 01:00:00');
INSERT INTO `schedule_job_log` VALUES (59, 1, 'testTask', 'renren', 0, NULL, 6, '2022-02-14 01:30:00');
INSERT INTO `schedule_job_log` VALUES (60, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 16:30:00');
INSERT INTO `schedule_job_log` VALUES (61, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 17:00:00');
INSERT INTO `schedule_job_log` VALUES (62, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 17:30:00');
INSERT INTO `schedule_job_log` VALUES (63, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 18:00:00');
INSERT INTO `schedule_job_log` VALUES (64, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 18:30:00');
INSERT INTO `schedule_job_log` VALUES (65, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 19:00:00');
INSERT INTO `schedule_job_log` VALUES (66, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 19:30:00');
INSERT INTO `schedule_job_log` VALUES (67, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 20:00:00');
INSERT INTO `schedule_job_log` VALUES (68, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 20:30:00');
INSERT INTO `schedule_job_log` VALUES (69, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 21:00:00');
INSERT INTO `schedule_job_log` VALUES (70, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 21:30:00');
INSERT INTO `schedule_job_log` VALUES (71, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-14 22:00:00');
INSERT INTO `schedule_job_log` VALUES (72, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 22:30:00');
INSERT INTO `schedule_job_log` VALUES (73, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-14 23:00:00');
INSERT INTO `schedule_job_log` VALUES (74, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-14 23:30:00');
INSERT INTO `schedule_job_log` VALUES (75, 1, 'testTask', 'renren', 0, NULL, 7, '2022-02-15 00:00:00');
INSERT INTO `schedule_job_log` VALUES (76, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 00:30:00');
INSERT INTO `schedule_job_log` VALUES (77, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-15 01:00:00');
INSERT INTO `schedule_job_log` VALUES (78, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 01:30:00');
INSERT INTO `schedule_job_log` VALUES (79, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 02:00:08');
INSERT INTO `schedule_job_log` VALUES (80, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 16:00:00');
INSERT INTO `schedule_job_log` VALUES (81, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-15 16:30:00');
INSERT INTO `schedule_job_log` VALUES (82, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 17:00:00');
INSERT INTO `schedule_job_log` VALUES (83, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-15 19:30:00');
INSERT INTO `schedule_job_log` VALUES (84, 1, 'testTask', 'renren', 0, NULL, 0, '2022-02-15 20:00:00');
INSERT INTO `schedule_job_log` VALUES (85, 1, 'testTask', 'renren', 0, NULL, 1, '2022-02-15 20:30:00');
INSERT INTO `schedule_job_log` VALUES (86, 1, 'testTask', 'renren', 0, NULL, 2, '2022-02-15 21:00:00');
INSERT INTO `schedule_job_log` VALUES (87, 1, 'testTask', 'renren', 0, NULL, 0, '2022-04-18 18:00:00');
INSERT INTO `schedule_job_log` VALUES (88, 1, 'testTask', 'renren', 0, NULL, 0, '2022-04-18 18:30:00');
INSERT INTO `schedule_job_log` VALUES (89, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-01 19:00:00');
INSERT INTO `schedule_job_log` VALUES (90, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-01 19:30:00');
INSERT INTO `schedule_job_log` VALUES (91, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-01 20:00:00');
INSERT INTO `schedule_job_log` VALUES (92, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-01 20:30:00');
INSERT INTO `schedule_job_log` VALUES (93, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-01 21:00:00');
INSERT INTO `schedule_job_log` VALUES (94, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-01 21:30:00');
INSERT INTO `schedule_job_log` VALUES (95, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-01 22:00:00');
INSERT INTO `schedule_job_log` VALUES (96, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-01 22:30:00');
INSERT INTO `schedule_job_log` VALUES (97, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-01 23:00:00');
INSERT INTO `schedule_job_log` VALUES (98, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 12:00:00');
INSERT INTO `schedule_job_log` VALUES (99, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 12:30:00');
INSERT INTO `schedule_job_log` VALUES (100, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 13:00:00');
INSERT INTO `schedule_job_log` VALUES (101, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 13:30:00');
INSERT INTO `schedule_job_log` VALUES (102, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 14:00:00');
INSERT INTO `schedule_job_log` VALUES (103, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 14:30:00');
INSERT INTO `schedule_job_log` VALUES (104, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 15:00:00');
INSERT INTO `schedule_job_log` VALUES (105, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 15:30:00');
INSERT INTO `schedule_job_log` VALUES (106, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 16:00:00');
INSERT INTO `schedule_job_log` VALUES (107, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 16:30:00');
INSERT INTO `schedule_job_log` VALUES (108, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 17:00:00');
INSERT INTO `schedule_job_log` VALUES (109, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 17:30:00');
INSERT INTO `schedule_job_log` VALUES (110, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 18:00:00');
INSERT INTO `schedule_job_log` VALUES (111, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 18:30:00');
INSERT INTO `schedule_job_log` VALUES (112, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 19:00:00');
INSERT INTO `schedule_job_log` VALUES (113, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 19:30:00');
INSERT INTO `schedule_job_log` VALUES (114, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 20:00:00');
INSERT INTO `schedule_job_log` VALUES (115, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 20:30:00');
INSERT INTO `schedule_job_log` VALUES (116, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 21:00:00');
INSERT INTO `schedule_job_log` VALUES (117, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 21:30:00');
INSERT INTO `schedule_job_log` VALUES (118, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 22:00:00');
INSERT INTO `schedule_job_log` VALUES (119, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 22:30:00');
INSERT INTO `schedule_job_log` VALUES (120, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-04 23:00:00');
INSERT INTO `schedule_job_log` VALUES (121, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-04 23:30:00');
INSERT INTO `schedule_job_log` VALUES (122, 1, 'testTask', 'renren', 0, NULL, 15, '2022-05-05 00:00:00');
INSERT INTO `schedule_job_log` VALUES (123, 1, 'testTask', 'renren', 0, NULL, 3, '2022-05-05 00:30:00');
INSERT INTO `schedule_job_log` VALUES (124, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-05 12:00:00');
INSERT INTO `schedule_job_log` VALUES (125, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-05 12:30:00');
INSERT INTO `schedule_job_log` VALUES (126, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-05 13:00:00');
INSERT INTO `schedule_job_log` VALUES (127, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-05 13:30:00');
INSERT INTO `schedule_job_log` VALUES (128, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-05 14:00:00');
INSERT INTO `schedule_job_log` VALUES (129, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-05 14:30:00');
INSERT INTO `schedule_job_log` VALUES (130, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-05 15:00:00');
INSERT INTO `schedule_job_log` VALUES (131, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-05 15:30:00');
INSERT INTO `schedule_job_log` VALUES (132, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-05 16:00:00');
INSERT INTO `schedule_job_log` VALUES (133, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-05 16:30:00');
INSERT INTO `schedule_job_log` VALUES (134, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 09:30:00');
INSERT INTO `schedule_job_log` VALUES (135, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 10:00:00');
INSERT INTO `schedule_job_log` VALUES (136, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 10:30:00');
INSERT INTO `schedule_job_log` VALUES (137, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 11:00:00');
INSERT INTO `schedule_job_log` VALUES (138, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 11:30:00');
INSERT INTO `schedule_job_log` VALUES (139, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 12:00:00');
INSERT INTO `schedule_job_log` VALUES (140, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 12:30:00');
INSERT INTO `schedule_job_log` VALUES (141, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 13:00:00');
INSERT INTO `schedule_job_log` VALUES (142, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 13:30:00');
INSERT INTO `schedule_job_log` VALUES (143, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 14:00:00');
INSERT INTO `schedule_job_log` VALUES (144, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 14:30:00');
INSERT INTO `schedule_job_log` VALUES (145, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 15:00:01');
INSERT INTO `schedule_job_log` VALUES (146, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 15:30:00');
INSERT INTO `schedule_job_log` VALUES (147, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 16:00:00');
INSERT INTO `schedule_job_log` VALUES (148, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 16:30:00');
INSERT INTO `schedule_job_log` VALUES (149, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 17:00:00');
INSERT INTO `schedule_job_log` VALUES (150, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 17:30:00');
INSERT INTO `schedule_job_log` VALUES (151, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 18:00:00');
INSERT INTO `schedule_job_log` VALUES (152, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 18:30:00');
INSERT INTO `schedule_job_log` VALUES (153, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 19:00:01');
INSERT INTO `schedule_job_log` VALUES (154, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 19:30:00');
INSERT INTO `schedule_job_log` VALUES (155, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 20:00:00');
INSERT INTO `schedule_job_log` VALUES (156, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 20:30:00');
INSERT INTO `schedule_job_log` VALUES (157, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 21:00:00');
INSERT INTO `schedule_job_log` VALUES (158, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 21:30:00');
INSERT INTO `schedule_job_log` VALUES (159, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 22:00:00');
INSERT INTO `schedule_job_log` VALUES (160, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 22:30:00');
INSERT INTO `schedule_job_log` VALUES (161, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-06 23:00:00');
INSERT INTO `schedule_job_log` VALUES (162, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-06 23:30:00');
INSERT INTO `schedule_job_log` VALUES (163, 1, 'testTask', 'renren', 0, NULL, 7, '2022-05-07 00:00:00');
INSERT INTO `schedule_job_log` VALUES (164, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 12:30:00');
INSERT INTO `schedule_job_log` VALUES (165, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 14:30:00');
INSERT INTO `schedule_job_log` VALUES (166, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 15:00:00');
INSERT INTO `schedule_job_log` VALUES (167, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 15:30:00');
INSERT INTO `schedule_job_log` VALUES (168, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 16:00:00');
INSERT INTO `schedule_job_log` VALUES (169, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 16:30:00');
INSERT INTO `schedule_job_log` VALUES (170, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 17:00:00');
INSERT INTO `schedule_job_log` VALUES (171, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 17:30:00');
INSERT INTO `schedule_job_log` VALUES (172, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 18:00:00');
INSERT INTO `schedule_job_log` VALUES (173, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 18:30:00');
INSERT INTO `schedule_job_log` VALUES (174, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 19:00:00');
INSERT INTO `schedule_job_log` VALUES (175, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 19:30:00');
INSERT INTO `schedule_job_log` VALUES (176, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 20:00:00');
INSERT INTO `schedule_job_log` VALUES (177, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 20:30:00');
INSERT INTO `schedule_job_log` VALUES (178, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 21:00:00');
INSERT INTO `schedule_job_log` VALUES (179, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 21:30:00');
INSERT INTO `schedule_job_log` VALUES (180, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-07 22:00:00');
INSERT INTO `schedule_job_log` VALUES (181, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-07 22:30:00');
INSERT INTO `schedule_job_log` VALUES (182, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 23:00:00');
INSERT INTO `schedule_job_log` VALUES (183, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-07 23:30:00');
INSERT INTO `schedule_job_log` VALUES (184, 1, 'testTask', 'renren', 0, NULL, 13, '2022-05-08 00:00:00');
INSERT INTO `schedule_job_log` VALUES (185, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 00:30:00');
INSERT INTO `schedule_job_log` VALUES (186, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-08 01:00:00');
INSERT INTO `schedule_job_log` VALUES (187, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 01:30:00');
INSERT INTO `schedule_job_log` VALUES (188, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-08 14:30:00');
INSERT INTO `schedule_job_log` VALUES (189, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-08 15:00:00');
INSERT INTO `schedule_job_log` VALUES (190, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 15:30:00');
INSERT INTO `schedule_job_log` VALUES (191, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-08 16:00:00');
INSERT INTO `schedule_job_log` VALUES (192, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-08 16:30:00');
INSERT INTO `schedule_job_log` VALUES (193, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 19:00:00');
INSERT INTO `schedule_job_log` VALUES (194, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 19:30:00');
INSERT INTO `schedule_job_log` VALUES (195, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 20:00:00');
INSERT INTO `schedule_job_log` VALUES (196, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-08 20:30:00');
INSERT INTO `schedule_job_log` VALUES (197, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-08 21:00:00');
INSERT INTO `schedule_job_log` VALUES (198, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 11:00:00');
INSERT INTO `schedule_job_log` VALUES (199, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 11:30:00');
INSERT INTO `schedule_job_log` VALUES (200, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 12:00:00');
INSERT INTO `schedule_job_log` VALUES (201, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 12:30:00');
INSERT INTO `schedule_job_log` VALUES (202, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 13:00:00');
INSERT INTO `schedule_job_log` VALUES (203, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 13:30:00');
INSERT INTO `schedule_job_log` VALUES (204, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 14:00:00');
INSERT INTO `schedule_job_log` VALUES (205, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 14:30:00');
INSERT INTO `schedule_job_log` VALUES (206, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 15:00:00');
INSERT INTO `schedule_job_log` VALUES (207, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 15:30:00');
INSERT INTO `schedule_job_log` VALUES (208, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 16:00:00');
INSERT INTO `schedule_job_log` VALUES (209, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 16:30:00');
INSERT INTO `schedule_job_log` VALUES (210, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 17:00:00');
INSERT INTO `schedule_job_log` VALUES (211, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 17:30:00');
INSERT INTO `schedule_job_log` VALUES (212, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 18:00:00');
INSERT INTO `schedule_job_log` VALUES (213, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 18:30:00');
INSERT INTO `schedule_job_log` VALUES (214, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 19:00:00');
INSERT INTO `schedule_job_log` VALUES (215, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 19:30:00');
INSERT INTO `schedule_job_log` VALUES (216, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 20:00:00');
INSERT INTO `schedule_job_log` VALUES (217, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 20:30:00');
INSERT INTO `schedule_job_log` VALUES (218, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 21:00:00');
INSERT INTO `schedule_job_log` VALUES (219, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 21:30:00');
INSERT INTO `schedule_job_log` VALUES (220, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 22:00:00');
INSERT INTO `schedule_job_log` VALUES (221, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 22:30:00');
INSERT INTO `schedule_job_log` VALUES (222, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-09 23:00:00');
INSERT INTO `schedule_job_log` VALUES (223, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-09 23:30:00');
INSERT INTO `schedule_job_log` VALUES (224, 1, 'testTask', 'renren', 0, NULL, 12, '2022-05-10 00:00:00');
INSERT INTO `schedule_job_log` VALUES (225, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-11 18:30:00');
INSERT INTO `schedule_job_log` VALUES (226, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-11 19:00:00');
INSERT INTO `schedule_job_log` VALUES (227, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-11 19:30:00');
INSERT INTO `schedule_job_log` VALUES (228, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-11 20:00:00');
INSERT INTO `schedule_job_log` VALUES (229, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-11 20:30:00');
INSERT INTO `schedule_job_log` VALUES (230, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-11 21:00:00');
INSERT INTO `schedule_job_log` VALUES (231, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-11 21:30:00');
INSERT INTO `schedule_job_log` VALUES (232, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-11 22:00:00');
INSERT INTO `schedule_job_log` VALUES (233, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-11 22:30:00');
INSERT INTO `schedule_job_log` VALUES (234, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-11 23:00:00');
INSERT INTO `schedule_job_log` VALUES (235, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-11 23:30:00');
INSERT INTO `schedule_job_log` VALUES (236, 1, 'testTask', 'renren', 0, NULL, 8, '2022-05-12 00:00:00');
INSERT INTO `schedule_job_log` VALUES (237, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 00:30:00');
INSERT INTO `schedule_job_log` VALUES (238, 1, 'testTask', 'renren', 0, NULL, 2, '2022-05-12 01:00:00');
INSERT INTO `schedule_job_log` VALUES (239, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-12 11:30:00');
INSERT INTO `schedule_job_log` VALUES (240, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-12 12:00:00');
INSERT INTO `schedule_job_log` VALUES (241, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 12:30:00');
INSERT INTO `schedule_job_log` VALUES (242, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 13:00:00');
INSERT INTO `schedule_job_log` VALUES (243, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-12 13:30:00');
INSERT INTO `schedule_job_log` VALUES (244, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-12 14:00:00');
INSERT INTO `schedule_job_log` VALUES (245, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 14:30:00');
INSERT INTO `schedule_job_log` VALUES (246, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 15:00:00');
INSERT INTO `schedule_job_log` VALUES (247, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 15:30:00');
INSERT INTO `schedule_job_log` VALUES (248, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-12 16:00:00');
INSERT INTO `schedule_job_log` VALUES (249, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 14:00:00');
INSERT INTO `schedule_job_log` VALUES (250, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 14:30:00');
INSERT INTO `schedule_job_log` VALUES (251, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 15:00:00');
INSERT INTO `schedule_job_log` VALUES (252, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 15:30:00');
INSERT INTO `schedule_job_log` VALUES (253, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 16:00:00');
INSERT INTO `schedule_job_log` VALUES (254, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 16:30:00');
INSERT INTO `schedule_job_log` VALUES (255, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 17:00:00');
INSERT INTO `schedule_job_log` VALUES (256, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 17:30:00');
INSERT INTO `schedule_job_log` VALUES (257, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 18:00:00');
INSERT INTO `schedule_job_log` VALUES (258, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 18:30:00');
INSERT INTO `schedule_job_log` VALUES (259, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 19:00:00');
INSERT INTO `schedule_job_log` VALUES (260, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 19:30:00');
INSERT INTO `schedule_job_log` VALUES (261, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 20:00:00');
INSERT INTO `schedule_job_log` VALUES (262, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 20:30:00');
INSERT INTO `schedule_job_log` VALUES (263, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 21:00:00');
INSERT INTO `schedule_job_log` VALUES (264, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 21:30:00');
INSERT INTO `schedule_job_log` VALUES (265, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 22:00:00');
INSERT INTO `schedule_job_log` VALUES (266, 1, 'testTask', 'renren', 0, NULL, 1, '2022-05-13 22:30:00');
INSERT INTO `schedule_job_log` VALUES (267, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 23:00:00');
INSERT INTO `schedule_job_log` VALUES (268, 1, 'testTask', 'renren', 0, NULL, 0, '2022-05-13 23:30:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('03de2eae-1537-4aca-8d71-d2af72ac23bf', 'epdng', '2022-02-13 22:07:50');
INSERT INTO `sys_captcha` VALUES ('0786b4ff-09d9-4f30-8a96-a4302d59c4ee', '76mya', '2022-02-08 17:57:37');
INSERT INTO `sys_captcha` VALUES ('1a7c3a66-b996-4cb2-85f4-6c7a852de6f1', 'n8mbp', '2022-02-08 18:00:03');
INSERT INTO `sys_captcha` VALUES ('1bafb93d-b8a6-4474-8cea-8d1928daaa3b', 'c2dxx', '2022-02-08 17:41:00');
INSERT INTO `sys_captcha` VALUES ('25de8af5-5b9a-4225-8af8-8be5043f35a1', 'my34a', '2022-02-08 17:40:51');
INSERT INTO `sys_captcha` VALUES ('2f312796-fdf5-493b-8d0d-8257abb18011', '77m53', '2022-02-08 15:57:02');
INSERT INTO `sys_captcha` VALUES ('36e66cd7-e5c8-469e-8c4b-0f40bb4efeaf', '88ydn', '2022-02-08 15:56:51');
INSERT INTO `sys_captcha` VALUES ('3f1532c6-db3a-4eca-8e12-5da91832cd8f', 'nnbpc', '2022-05-13 14:31:28');
INSERT INTO `sys_captcha` VALUES ('3fed3bec-70b0-425b-8f75-f2aa7ecff70b', 'bfm35', '2022-02-11 23:22:38');
INSERT INTO `sys_captcha` VALUES ('45668482-d5ff-4f47-87c7-97d470426dbc', 'wdmny', '2022-02-08 16:52:08');
INSERT INTO `sys_captcha` VALUES ('607132d7-3347-46fc-8c09-94f02018b980', '2nfxn', '2022-02-09 17:49:21');
INSERT INTO `sys_captcha` VALUES ('64ffd734-0578-4b41-82db-ac880166f3bc', '6yddg', '2022-02-14 16:22:43');
INSERT INTO `sys_captcha` VALUES ('78c106b5-b0a2-4dfa-8f96-5de59c3432cf', '3g6gn', '2022-02-08 16:28:01');
INSERT INTO `sys_captcha` VALUES ('7b437032-a504-498d-8f5f-58d069679b04', 'b84g5', '2022-02-08 17:59:32');
INSERT INTO `sys_captcha` VALUES ('806fec00-8076-4657-87cf-bc009234f4d4', 'bnx7c', '2022-02-08 17:57:30');
INSERT INTO `sys_captcha` VALUES ('8899f426-085d-4219-8d09-8b5e38d9ca80', '24w3m', '2022-02-08 17:24:45');
INSERT INTO `sys_captcha` VALUES ('8e86eedf-3740-4cc5-8373-11268554a9ca', '7fncm', '2022-02-08 17:15:38');
INSERT INTO `sys_captcha` VALUES ('9780e609-98f0-4f48-85d1-f946fcf53b91', '2nedp', '2022-02-08 16:51:33');
INSERT INTO `sys_captcha` VALUES ('9a0e4e03-540a-488d-84ea-dc7ca7372556', 'en2y3', '2022-04-18 18:08:50');
INSERT INTO `sys_captcha` VALUES ('ace8d68a-3951-44c7-8ee5-50954ccfd181', 'cc7cp', '2022-02-08 16:52:11');
INSERT INTO `sys_captcha` VALUES ('bbbb32c5-004d-44fe-80f4-5338c56026f4', 'wymg7', '2022-05-12 12:29:01');
INSERT INTO `sys_captcha` VALUES ('c188d45c-8caa-4f3a-8277-b5aa43493a4a', 'wa5an', '2022-05-08 14:37:24');
INSERT INTO `sys_captcha` VALUES ('c2ff130c-ffa1-4b66-827b-2a2dd775395e', 'p5dy8', '2022-02-08 16:53:03');
INSERT INTO `sys_captcha` VALUES ('d0ee1b77-618d-47dd-8f98-43cd5cf442b3', 'b74w4', '2022-05-07 12:16:59');
INSERT INTO `sys_captcha` VALUES ('d29dc936-2339-43d3-8198-d532b03ce46b', 'f8xf6', '2022-02-09 17:06:49');
INSERT INTO `sys_captcha` VALUES ('d9a7b120-68a3-4956-8236-14dd354eb698', 'dawfx', '2022-02-08 17:01:41');
INSERT INTO `sys_captcha` VALUES ('e12ce62e-d0df-48ea-84e7-f94cc43b96ca', 'g5c5b', '2022-02-08 17:16:19');
INSERT INTO `sys_captcha` VALUES ('e24cdfb5-3d56-461c-8d80-32b0ee9ee193', 'mpg54', '2022-02-08 16:37:36');
INSERT INTO `sys_captcha` VALUES ('e826d692-34b2-4ca7-8e80-a2f92e291f61', 'ag7ad', '2022-04-18 18:08:38');
INSERT INTO `sys_captcha` VALUES ('e96a9157-3bf2-40da-8e01-1b8e0a6ddce1', 'pe2p3', '2022-02-13 15:39:49');
INSERT INTO `sys_captcha` VALUES ('ffcffb71-6de2-4887-8cb5-a952b64fd537', '4gn4w', '2022-02-08 17:34:47');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":0,\"roleIdList\":[],\"createUserId\":1}]', 328, '127.0.0.1', '2022-02-08 18:00:02');
INSERT INTO `sys_log` VALUES (2, 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"editor\",\"orderNum\":0,\"list\":[]}]', 71, '127.0.0.1', '2022-02-08 19:28:06');
INSERT INTO `sys_log` VALUES (3, 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"分类维护\",\"url\":\"product/category\",\"perms\":\"\",\"type\":1,\"icon\":\"menu\",\"orderNum\":0,\"list\":[]}]', 99, '127.0.0.1', '2022-02-08 19:29:27');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);
INSERT INTO `sys_menu` VALUES (31, 0, '商品系统', '', '', 0, 'editor', 0);
INSERT INTO `sys_menu` VALUES (32, 31, '分类维护', 'product/category', '', 1, 'menu', 0);
INSERT INTO `sys_menu` VALUES (34, 31, '品牌管理', 'product/brand', '', 1, 'editor', 0);
INSERT INTO `sys_menu` VALUES (37, 31, '平台属性', '', '', 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (38, 37, '属性分组', 'product/attrgroup', '', 1, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (39, 37, '规格参数', 'product/baseattr', '', 1, 'log', 0);
INSERT INTO `sys_menu` VALUES (40, 37, '销售属性', 'product/saleattr', '', 1, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (41, 31, '商品维护', 'product/spu', '', 0, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (42, 0, '优惠营销', '', '', 0, 'mudedi', 0);
INSERT INTO `sys_menu` VALUES (43, 0, '库存系统', '', '', 0, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (44, 0, '订单系统', '', '', 0, 'config', 0);
INSERT INTO `sys_menu` VALUES (45, 0, '用户系统', '', '', 0, 'admin', 0);
INSERT INTO `sys_menu` VALUES (46, 0, '内容管理', '', '', 0, 'sousuo', 0);
INSERT INTO `sys_menu` VALUES (47, 42, '优惠券管理', 'coupon/coupon', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (48, 42, '发放记录', 'coupon/history', '', 1, 'sql', 0);
INSERT INTO `sys_menu` VALUES (49, 42, '专题活动', 'coupon/subject', '', 1, 'tixing', 0);
INSERT INTO `sys_menu` VALUES (50, 42, '秒杀活动', 'coupon/seckill', '', 1, 'daohang', 0);
INSERT INTO `sys_menu` VALUES (51, 42, '积分维护', 'coupon/bounds', '', 1, 'geren', 0);
INSERT INTO `sys_menu` VALUES (52, 42, '满减折扣', 'coupon/full', '', 1, 'shoucang', 0);
INSERT INTO `sys_menu` VALUES (53, 43, '仓库维护', 'ware/wareinfo', '', 1, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (54, 43, '库存工作单', 'ware/task', '', 1, 'log', 0);
INSERT INTO `sys_menu` VALUES (55, 43, '商品库存', 'ware/sku', '', 1, 'jiesuo', 0);
INSERT INTO `sys_menu` VALUES (56, 44, '订单查询', 'order/order', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (57, 44, '退货单处理', 'order/return', '', 1, 'shanchu', 0);
INSERT INTO `sys_menu` VALUES (58, 44, '等级规则', 'order/settings', '', 1, 'system', 0);
INSERT INTO `sys_menu` VALUES (59, 44, '支付流水查询', 'order/payment', '', 1, 'job', 0);
INSERT INTO `sys_menu` VALUES (60, 44, '退款流水查询', 'order/refund', '', 1, 'mudedi', 0);
INSERT INTO `sys_menu` VALUES (61, 45, '会员列表', 'member/member', '', 1, 'geren', 0);
INSERT INTO `sys_menu` VALUES (62, 45, '会员等级', 'member/level', '', 1, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (63, 45, '积分变化', 'member/growth', '', 1, 'bianji', 0);
INSERT INTO `sys_menu` VALUES (64, 45, '统计信息', 'member/statistics', '', 1, 'sql', 0);
INSERT INTO `sys_menu` VALUES (65, 46, '首页推荐', 'content/index', '', 1, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (66, 46, '分类热门', 'content/category', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (67, 46, '评论管理', 'content/comments', '', 1, 'pinglun', 0);
INSERT INTO `sys_menu` VALUES (68, 41, 'spu管理', 'product/spu', '', 1, 'config', 0);
INSERT INTO `sys_menu` VALUES (69, 41, '发布商品', 'product/spuadd', '', 1, 'bianji', 0);
INSERT INTO `sys_menu` VALUES (70, 43, '采购单维护', '', '', 0, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (71, 70, '采购需求', 'ware/purchaseitem', '', 1, 'editor', 0);
INSERT INTO `sys_menu` VALUES (72, 70, '采购单', 'ware/purchase', '', 1, 'menu', 0);
INSERT INTO `sys_menu` VALUES (73, 41, '商品管理', 'product/manager', '', 1, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (74, 42, '会员价格', 'coupon/memberprice', '', 1, 'admin', 0);
INSERT INTO `sys_menu` VALUES (75, 42, '每日秒杀', 'coupon/seckillsession', '', 1, 'job', 0);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '2cb31d296b0a9cd7b73f112018a35719', '2022-05-14 02:10:12', '2022-05-13 14:10:12');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

SET FOREIGN_KEY_CHECKS = 1;
