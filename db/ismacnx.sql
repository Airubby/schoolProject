/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : ismacnx

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-02-22 16:29:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` varchar(11) NOT NULL,
  `Name` varchar(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '系统用户');
INSERT INTO `role` VALUES ('3', '非系统用户');

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` varchar(50) NOT NULL,
  `agentbm` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `port1` int(11) DEFAULT NULL,
  `protocol` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `socketconnecttimeout` int(11) DEFAULT NULL,
  `reconnecttime` int(11) DEFAULT NULL,
  `sotimeout` int(11) DEFAULT NULL,
  `xmlurl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('7056761910', '3', '192.168.16.136', '1061', '1062', 'V3', '2018-10-02T16:00:00.000Z', '1', '10000', '10000', '10000', '6792180781.xml', '1212333333333333333333');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Ope_ID` varchar(50) DEFAULT NULL,
  `UserID` varchar(50) NOT NULL,
  `Details` varchar(4000) DEFAULT NULL,
  `CreateTime` varchar(20) NOT NULL,
  `Interfaceurl` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=414345 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('414131', '00102', 'admin', '????:??????,', '2019-02-21 15:41:04', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414132', '00102', 'admin', '??????:,', '2019-02-21 15:44:07', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414133', '00102', 'admin', '??????:,', '2019-02-21 15:44:07', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414134', '00102', 'admin', '????:??????,', '2019-02-21 15:44:09', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414135', '00102', 'admin', '????:????,', '2019-02-21 15:53:53', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414136', '00102', 'admin', '??????:,', '2019-02-21 16:26:38', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414137', '00102', 'admin', '??????:,', '2019-02-21 16:26:38', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414138', '00102', 'admin', '??????:,', '2019-02-21 17:02:00', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414139', '00102', 'admin', '??????:,', '2019-02-21 17:02:00', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414140', '00102', 'admin', '??????:,', '2019-02-21 17:02:03', '/service/detail', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414141', '00102', 'admin', '??????:???????,', '2019-02-21 17:02:03', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414142', '00102', 'admin', '??????:,', '2019-02-21 17:03:23', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414143', '00102', 'admin', '??????:,', '2019-02-21 17:03:23', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414144', '00102', 'admin', '??????:,', '2019-02-21 17:29:04', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414145', '00102', 'admin', '??????:,', '2019-02-21 17:29:50', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414146', '00102', 'admin', '??????:,', '2019-02-21 17:31:25', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414147', '00102', 'admin', '??????:,', '2019-02-21 17:33:38', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414148', '00102', 'admin', '??????:,', '2019-02-21 17:43:56', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414149', '00102', 'admin', '??????:,', '2019-02-21 17:44:46', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414150', '00102', 'admin', '??????:,', '2019-02-21 17:45:36', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414151', '00102', 'admin', '??????:,', '2019-02-21 18:03:45', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414152', '00102', 'admin', '??????:,', '2019-02-21 18:04:11', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414153', '00102', 'admin', '??????:,', '2019-02-21 18:04:11', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414154', '00102', 'admin', '??????:,', '2019-02-21 18:04:13', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414155', '00102', 'admin', '??????:,', '2019-02-22 09:17:45', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414156', '00102', 'admin', '??????:,', '2019-02-22 09:18:06', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414157', '00102', 'admin', '??????:,', '2019-02-22 09:30:41', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414158', '00102', 'admin', '??????:,', '2019-02-22 09:30:41', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414159', '00102', 'admin', '??????:,', '2019-02-22 09:34:02', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414160', '00102', 'admin', '??????:,', '2019-02-22 09:34:49', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414161', '00102', 'admin', '??????:,', '2019-02-22 09:34:49', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414162', '00102', 'admin', '????:??????,', '2019-02-22 09:36:14', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414163', '00102', 'admin', '????:??????,', '2019-02-22 09:36:22', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414164', '00102', 'admin', '??????:???????,', '2019-02-22 09:36:33', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414165', '00102', 'admin', '????:??????,', '2019-02-22 09:36:35', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414166', '00102', 'admin', '????:??????,', '2019-02-22 09:37:20', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414167', '00103', 'admin', '????:??????????????????!null', '2019-02-22 09:38:40', '/user/update', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414168', '00102', 'admin', '????:??????,', '2019-02-22 09:39:21', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414169', '00102', 'admin', '????:??,', '2019-02-22 09:39:29', '/user/update', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414170', '00102', 'admin', '????:??????,', '2019-02-22 09:39:29', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414171', '00102', 'admin', '??????:,', '2019-02-22 09:40:06', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414172', '00102', 'admin', '??????:,', '2019-02-22 09:40:06', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414173', '00102', 'admin', '????:??????,', '2019-02-22 09:42:03', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414174', '00102', 'admin', '????:????,', '2019-02-22 09:42:25', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414175', '00102', 'admin', '??????:,', '2019-02-22 09:42:55', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414176', '00102', 'admin', '??????:,', '2019-02-22 09:42:56', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414177', '00102', 'admin', '??????:,', '2019-02-22 09:42:56', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414178', '00102', 'admin', '????:??????,', '2019-02-22 09:42:58', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414179', '00102', 'admin', '????:??,', '2019-02-22 09:43:19', '/user/add', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414180', '00102', 'admin', '????:??????,', '2019-02-22 09:43:19', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414181', '00102', 'admin', '????:????,', '2019-02-22 09:43:24', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414182', '00102', 'test', '??????:,', '2019-02-22 10:10:28', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414183', '00102', 'test', '??????:,', '2019-02-22 10:15:33', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414184', '00102', 'test', '??????:,', '2019-02-22 10:15:33', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414185', '00102', 'test', '????:??????,', '2019-02-22 10:18:34', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414186', '00102', 'test', '????:????,', '2019-02-22 10:19:24', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414187', '00102', 'admin', '??????:,', '2019-02-22 10:19:26', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414188', '00102', 'admin', '??????:,', '2019-02-22 10:19:27', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414189', '00102', 'admin', '??????:,', '2019-02-22 10:19:27', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414190', '00102', 'admin', '????:??????,', '2019-02-22 10:19:31', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414191', '00102', 'admin', '??????:???????,', '2019-02-22 10:22:55', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414192', '00102', 'admin', '????:??????,', '2019-02-22 10:22:57', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414193', '00102', 'admin', '????:??????,', '2019-02-22 10:23:52', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414194', '00102', 'admin', '????:??????,', '2019-02-22 11:52:19', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414195', '00102', 'admin', '??????:,', '2019-02-22 14:23:18', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414196', '00102', 'admin', '????:??????,', '2019-02-22 14:30:19', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414197', '00102', 'admin', '??????:,', '2019-02-22 14:30:20', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414198', '00102', 'admin', '??????:,', '2019-02-22 14:37:39', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414199', '00102', 'admin', '??????:,', '2019-02-22 14:38:25', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414200', '00102', 'admin', '??????:,', '2019-02-22 14:39:02', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414201', '00102', 'admin', '????:??????,', '2019-02-22 14:39:10', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414202', '00102', 'admin', '??????:,', '2019-02-22 14:39:13', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414203', '00102', 'admin', '??????:,', '2019-02-22 14:40:17', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414204', '00102', 'admin', '??????:,', '2019-02-22 14:45:01', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414205', '00102', 'admin', '??????:,', '2019-02-22 14:52:17', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414206', '00102', 'admin', '??????:,', '2019-02-22 14:53:49', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414207', '00102', 'admin', '??????:,', '2019-02-22 14:56:45', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414208', '00102', 'admin', '????:??????,', '2019-02-22 14:57:01', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414209', '00102', 'admin', '??????:,', '2019-02-22 14:57:02', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414210', '00102', 'admin', '????:??????,', '2019-02-22 14:59:04', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414211', '00102', 'admin', '??????:,', '2019-02-22 14:59:05', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414212', '00102', 'admin', '??????:,', '2019-02-22 15:01:19', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414213', '00102', 'admin', '??????:,', '2019-02-22 15:01:25', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414214', '00102', 'admin', '????:??????,', '2019-02-22 15:01:41', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414215', '00102', 'admin', '??????:,', '2019-02-22 15:01:43', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414216', '00102', 'admin', '??????:,', '2019-02-22 15:02:44', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414217', '00102', 'admin', '??????:,', '2019-02-22 15:02:53', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414218', '00102', 'admin', '??????:,', '2019-02-22 15:03:16', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414219', '00102', 'admin', '????:??????,', '2019-02-22 15:03:29', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414220', '00102', 'admin', '??????:,', '2019-02-22 15:03:30', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414221', '00102', 'admin', '??????:,', '2019-02-22 15:03:31', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414222', '00102', 'admin', '????:??????,', '2019-02-22 15:04:38', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414223', '00102', 'admin', '??????:,', '2019-02-22 15:04:39', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414224', '00102', 'admin', '??????:,', '2019-02-22 15:04:40', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414225', '00102', 'admin', '????:??????,', '2019-02-22 15:05:11', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414226', '00102', 'admin', '??????:,', '2019-02-22 15:05:12', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414227', '00102', 'admin', '??????:,', '2019-02-22 15:05:13', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414228', '00102', 'admin', '????:??????,', '2019-02-22 15:06:31', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414229', '00102', 'admin', '??????:,', '2019-02-22 15:06:32', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414230', '00102', 'admin', '??????:,', '2019-02-22 15:06:33', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414231', '00102', 'admin', '????:??????,', '2019-02-22 15:07:48', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414232', '00102', 'admin', '????:??????,', '2019-02-22 15:07:48', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414233', '00102', 'admin', '??????:,', '2019-02-22 15:07:50', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414234', '00102', 'admin', '??????:,', '2019-02-22 15:07:51', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414235', '00102', 'admin', '????:??????,', '2019-02-22 15:14:27', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414236', '00102', 'admin', '??????:,', '2019-02-22 15:14:28', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414237', '00102', 'admin', '??????:,', '2019-02-22 15:14:28', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414238', '00102', 'admin', '??????:,', '2019-02-22 15:15:18', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414239', '00102', 'admin', '????:??????,', '2019-02-22 15:15:30', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414240', '00102', 'admin', '??????:,', '2019-02-22 15:15:33', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414241', '00102', 'admin', '??????:,', '2019-02-22 15:15:34', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414242', '00102', 'admin', '????:??????,', '2019-02-22 15:17:44', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414243', '00102', 'admin', '??????:,', '2019-02-22 15:17:44', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414244', '00102', 'admin', '??????:,', '2019-02-22 15:17:46', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414245', '00102', 'admin', '????:??????,', '2019-02-22 15:19:57', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414246', '00102', 'admin', '??????:,', '2019-02-22 15:20:26', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414247', '00102', 'admin', '??????:,', '2019-02-22 15:20:27', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414248', '00102', 'admin', '??????:,', '2019-02-22 15:21:41', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414249', '00102', 'admin', '????:??????,', '2019-02-22 15:22:42', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414250', '00102', 'admin', '??????:,', '2019-02-22 15:22:46', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414251', '00102', 'admin', '??????:,', '2019-02-22 15:22:46', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414252', '00102', 'admin', '??????:,', '2019-02-22 15:24:09', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414253', '00102', 'admin', '??????:,', '2019-02-22 15:24:11', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414254', '00102', 'admin', '??????:,', '2019-02-22 15:24:11', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414255', '00102', 'admin', '????:??????,', '2019-02-22 15:25:09', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414256', '00102', 'admin', '??????:,', '2019-02-22 15:25:10', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414257', '00102', 'admin', '??????:,', '2019-02-22 15:25:12', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414258', '00102', 'admin', '??????:,', '2019-02-22 15:27:56', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414259', '00102', 'admin', '??????:,', '2019-02-22 15:28:01', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414260', '00102', 'admin', '??????:,', '2019-02-22 15:28:33', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414261', '00102', 'admin', '??????:,', '2019-02-22 15:28:56', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414262', '00102', 'admin', '??????:,', '2019-02-22 15:29:01', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414263', '00102', 'admin', '??????:,', '2019-02-22 15:30:40', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414264', '00102', 'admin', '??????:,', '2019-02-22 15:30:48', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414265', '00102', 'admin', '??????:,', '2019-02-22 15:31:14', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414266', '00102', 'admin', '??????:,', '2019-02-22 15:34:38', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414267', '00102', 'admin', '??????:,', '2019-02-22 15:34:43', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414268', '00102', 'admin', '??????:,', '2019-02-22 15:40:45', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414269', '00102', 'admin', '??????:,', '2019-02-22 15:40:49', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414270', '00102', 'admin', '????:??????,', '2019-02-22 15:47:21', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414271', '00102', 'admin', '??????:,', '2019-02-22 15:47:24', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414272', '00102', 'admin', '??????:,', '2019-02-22 15:47:25', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414273', '00102', 'admin', '??????:,', '2019-02-22 15:47:53', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414274', '00102', 'admin', '??????:,', '2019-02-22 15:47:56', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414275', '00102', 'admin', '????:??????,', '2019-02-22 15:48:04', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414276', '00102', 'admin', '??????:,', '2019-02-22 15:48:05', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414277', '00102', 'admin', '??????:,', '2019-02-22 15:48:06', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414278', '00102', 'admin', '??????:,', '2019-02-22 15:49:37', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414279', '00102', 'admin', '??????:,', '2019-02-22 15:50:31', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414280', '00102', 'admin', '??????:,', '2019-02-22 15:51:34', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414281', '00102', 'admin', '??????:,', '2019-02-22 15:54:23', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414282', '00102', 'admin', '??????:,', '2019-02-22 15:55:56', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414283', '00102', 'admin', '????:??????,', '2019-02-22 15:56:02', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414284', '00102', 'admin', '??????:,', '2019-02-22 15:56:03', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414285', '00102', 'admin', '??????:,', '2019-02-22 15:56:04', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414286', '00102', 'admin', '??????:,', '2019-02-22 15:57:15', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414287', '00102', 'admin', '??????:,', '2019-02-22 15:57:50', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414288', '00102', 'admin', '??????:,', '2019-02-22 15:58:31', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414289', '00102', 'admin', '??????:,', '2019-02-22 15:59:15', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414290', '00102', 'admin', '??????:,', '2019-02-22 15:59:48', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414291', '00102', 'admin', '??????:,', '2019-02-22 16:00:22', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414292', '00102', 'admin', '??????:,', '2019-02-22 16:01:13', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414293', '00102', 'admin', '??????:,', '2019-02-22 16:01:35', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414294', '00102', 'admin', '??????:,', '2019-02-22 16:01:51', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414295', '00102', 'admin', '??????:,', '2019-02-22 16:02:45', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414296', '00102', 'admin', '??????:,', '2019-02-22 16:03:46', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414297', '00102', 'admin', '??????:,', '2019-02-22 16:04:27', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414298', '00102', 'admin', '????:??????,', '2019-02-22 16:05:12', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414299', '00102', 'admin', '??????:,', '2019-02-22 16:05:14', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414300', '00102', 'admin', '??????:,', '2019-02-22 16:05:15', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414301', '00102', 'admin', '??????:,', '2019-02-22 16:05:21', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414302', '00102', 'admin', '??????:,', '2019-02-22 16:06:45', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414303', '00102', 'admin', '??????:,', '2019-02-22 16:06:54', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414304', '00102', 'admin', '??????:,', '2019-02-22 16:08:13', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414305', '00102', 'admin', '??????:,', '2019-02-22 16:08:16', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414306', '00102', 'admin', '??????:,', '2019-02-22 16:08:23', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414307', '00102', 'admin', '??????:,', '2019-02-22 16:09:42', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414308', '00102', 'admin', '??????:,', '2019-02-22 16:10:15', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414309', '00102', 'admin', '??????:,', '2019-02-22 16:10:33', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414310', '00102', 'admin', '??????:,', '2019-02-22 16:10:39', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414311', '00102', 'admin', '??????:,', '2019-02-22 16:11:43', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414312', '00102', 'admin', '??????:,', '2019-02-22 16:11:59', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414313', '00102', 'admin', '??????:,', '2019-02-22 16:14:23', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414314', '00102', 'admin', '??????:,', '2019-02-22 16:14:29', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414315', '00102', 'admin', '????:??????,', '2019-02-22 16:14:34', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414316', '00102', 'admin', '??????:,', '2019-02-22 16:14:37', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414317', '00102', 'admin', '??????:,', '2019-02-22 16:14:38', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414318', '00102', 'admin', '??????:,', '2019-02-22 16:15:05', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414319', '00102', 'admin', '??????:,', '2019-02-22 16:16:20', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414320', '00102', 'admin', '??????:,', '2019-02-22 16:16:24', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414321', '00102', 'admin', '????:??????,', '2019-02-22 16:16:31', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414322', '00102', 'admin', '??????:,', '2019-02-22 16:16:33', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414323', '00102', 'admin', '??????:,', '2019-02-22 16:16:34', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414324', '00102', 'admin', '??????:,', '2019-02-22 16:16:38', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414325', '00102', 'admin', '????:??,', '2019-02-22 16:17:08', '/user/add', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414326', '00102', 'admin', '????:??????,', '2019-02-22 16:17:08', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414327', '00102', 'admin', '????:??????,', '2019-02-22 16:17:22', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414328', '00102', 'admin', '????:??????,', '2019-02-22 16:18:37', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414329', '00102', 'admin', '????:??????,', '2019-02-22 16:18:43', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414330', '00102', 'admin', '????:??????,', '2019-02-22 16:19:35', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414331', '00102', 'admin', '????:??????,', '2019-02-22 16:19:38', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414332', '00102', 'admin', '????:??????,', '2019-02-22 16:20:12', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414333', '00102', 'admin', '??????:,', '2019-02-22 16:22:10', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414334', '00102', 'admin', '????:??????,', '2019-02-22 16:23:13', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414335', '00102', 'admin', '????:??????,', '2019-02-22 16:24:45', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414336', '00102', 'admin', '??????:,', '2019-02-22 16:25:10', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414337', '00102', 'admin', '??????:,', '2019-02-22 16:26:03', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414338', '00102', 'admin', '??????:,', '2019-02-22 16:26:20', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414339', '00102', 'admin', '????:??,', '2019-02-22 16:26:29', '/user/update', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414340', '00102', 'admin', '????:??????,', '2019-02-22 16:26:29', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414341', '00102', 'admin', '??????:,', '2019-02-22 16:26:31', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414342', '00102', 'admin', '??????:,', '2019-02-22 16:26:32', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414343', '00102', 'admin', '????:??????,', '2019-02-22 16:26:42', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414344', '00102', 'admin', '??????:???????,', '2019-02-22 16:26:43', '/time/query', '192.168.16.6');

-- ----------------------------
-- Table structure for timedetail
-- ----------------------------
DROP TABLE IF EXISTS `timedetail`;
CREATE TABLE `timedetail` (
  `id` varchar(255) NOT NULL,
  `timeweek` char(255) DEFAULT NULL,
  `begintime` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `timegroupid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timedetail
-- ----------------------------
INSERT INTO `timedetail` VALUES ('4995538094', '1', '11:53:30', '12:53:30', '4772920514');
INSERT INTO `timedetail` VALUES ('3185182041', '1', '10:43:08', '11:43:08', '4772920514');
INSERT INTO `timedetail` VALUES ('509228902', '2', '10:43:08', '11:43:08', '4772920514');
INSERT INTO `timedetail` VALUES ('5195049435', '2', '11:53:30', '12:53:30', '4772920514');
INSERT INTO `timedetail` VALUES ('5267252193', '3', '10:43:08', '11:43:08', '4772920514');
INSERT INTO `timedetail` VALUES ('5351503739', '3', '11:53:30', '12:53:30', '4772920514');
INSERT INTO `timedetail` VALUES ('690576998', '4', '13:04:58', '14:04:58', '5749805468');
INSERT INTO `timedetail` VALUES ('7051737775', '7', '13:04:58', '14:04:58', '5749805468');

-- ----------------------------
-- Table structure for timegroup
-- ----------------------------
DROP TABLE IF EXISTS `timegroup`;
CREATE TABLE `timegroup` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timegroup
-- ----------------------------
INSERT INTO `timegroup` VALUES ('5749805468', '2222', '2019-02-07 12:00:00', '2019-02-28 12:00:00');
INSERT INTO `timegroup` VALUES ('4772920514', '123', '2019-02-04 12:00:00', '2019-02-21 12:00:00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `OrderNo` int(4) NOT NULL AUTO_INCREMENT,
  `ID` varchar(50) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `UserID` varchar(50) NOT NULL,
  `RoleID` varchar(11) NOT NULL DEFAULT '3',
  `PsWord` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `time_start` varchar(32) DEFAULT '2015-01-01 00:00:00',
  `time_end` varchar(32) DEFAULT '2055-01-01 00:00:00',
  `state` int(2) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `addrorrole` varchar(5000) DEFAULT NULL,
  `addrname` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`OrderNo`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1801383644', '管理员', 'admin', '1', '21232F297A57A5A743894A0E4A801FC3', '15228585858', '356155@qq.com', '2015-01-01 00:00:00', '2055-01-01 00:00:00', '1', '', '', null);
INSERT INTO `user` VALUES ('75', '0562963191', 'youtao', 'youtao', '2', 'C8172401710F5C502DE49A006FC51CD1', null, null, '2019-01-01 00:00:00', '2019-01-23 00:00:00', '1', null, '6137245932', null);
INSERT INTO `user` VALUES ('76', '1560860264', '小呆', 'xiaodai', '2', '8C994FCED635EEFDEC3D27A5EBEEA26F', null, null, '2019-01-10 00:00:00', '2019-01-31 00:00:00', '0', null, '', null);
INSERT INTO `user` VALUES ('77', '956571353', 'test111', 'test', '2', '8C994FCED635EEFDEC3D27A5EBEEA26F', null, null, '2019-02-21 00:00:00', '2019-02-28 00:00:00', '1', null, null, null);
INSERT INTO `user` VALUES ('78', '9928860897', '??????', 'test111', '2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, '2019-02-07 00:00:00', '2019-02-28 00:00:00', '1', null, '211,212,221,222', '???_??1,???_??2,???_???1,???_???2');
