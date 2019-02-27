/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : ismacnx

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-02-27 16:56:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hisdev20190225
-- ----------------------------
DROP TABLE IF EXISTS `hisdev20190225`;
CREATE TABLE `hisdev20190225` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mgrobjid` varchar(50) DEFAULT NULL,
  `pointid` varchar(50) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hisdev20190225
-- ----------------------------
INSERT INTO `hisdev20190225` VALUES ('1', '10001', '000001', '10', '2019-02-25 17:00:29');
INSERT INTO `hisdev20190225` VALUES ('2', '10002', '000001', '15', '2019-02-25 17:00:53');
INSERT INTO `hisdev20190225` VALUES ('3', '10003', '000001', '12', '2019-02-25 17:01:18');
INSERT INTO `hisdev20190225` VALUES ('4', '10004', '000001', '10', '2019-02-25 17:31:45');
INSERT INTO `hisdev20190225` VALUES ('5', '10011', '000001', '10', '2019-02-25 17:32:24');
INSERT INTO `hisdev20190225` VALUES ('6', '10012', '000001', '15', '2019-02-25 17:32:41');
INSERT INTO `hisdev20190225` VALUES ('7', '10013', '000001', '16', '2019-02-25 17:32:56');
INSERT INTO `hisdev20190225` VALUES ('8', '10014', '000001', '20', '2019-02-25 17:33:10');
INSERT INTO `hisdev20190225` VALUES ('9', '10021', '000001', '18', '2019-02-25 17:33:25');
INSERT INTO `hisdev20190225` VALUES ('10', '10022', '000001', '24', '2019-02-25 17:33:38');
INSERT INTO `hisdev20190225` VALUES ('11', '10023', '000001', '23', '2019-02-25 17:33:52');
INSERT INTO `hisdev20190225` VALUES ('12', '10024', '000001', '36', '2019-02-25 17:34:04');
INSERT INTO `hisdev20190225` VALUES ('13', '10025', '000001', '15', '2019-02-25 17:34:16');
INSERT INTO `hisdev20190225` VALUES ('14', '10054', '000001', '19.65', '2019-02-26 14:53:32');

-- ----------------------------
-- Table structure for hisdev20190226
-- ----------------------------
DROP TABLE IF EXISTS `hisdev20190226`;
CREATE TABLE `hisdev20190226` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mgrobjid` varchar(50) DEFAULT NULL,
  `pointid` varchar(50) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hisdev20190226
-- ----------------------------
INSERT INTO `hisdev20190226` VALUES ('1', '10001', '000001', '10', '2019-02-26 17:00:29');
INSERT INTO `hisdev20190226` VALUES ('2', '10002', '000001', '15', '2019-02-26 17:00:53');
INSERT INTO `hisdev20190226` VALUES ('3', '10003', '000001', '12', '2019-02-26 17:01:18');
INSERT INTO `hisdev20190226` VALUES ('4', '10004', '000001', '10', '2019-02-26 17:31:45');
INSERT INTO `hisdev20190226` VALUES ('5', '10011', '000001', '10', '2019-02-26 17:32:24');
INSERT INTO `hisdev20190226` VALUES ('6', '10012', '000001', '15', '2019-02-26 17:32:41');
INSERT INTO `hisdev20190226` VALUES ('7', '10013', '000001', '16', '2019-02-26 17:32:56');
INSERT INTO `hisdev20190226` VALUES ('8', '10014', '000001', '20', '2019-02-26 17:33:10');
INSERT INTO `hisdev20190226` VALUES ('9', '10021', '000001', '18', '2019-02-26 17:33:25');
INSERT INTO `hisdev20190226` VALUES ('10', '10022', '000001', '24', '2019-02-26 17:33:38');
INSERT INTO `hisdev20190226` VALUES ('11', '10023', '000001', '23', '2019-02-26 17:33:52');
INSERT INTO `hisdev20190226` VALUES ('12', '10024', '000001', '36', '2019-02-26 17:34:04');
INSERT INTO `hisdev20190226` VALUES ('13', '10025', '000001', '15', '2019-02-26 17:34:16');
INSERT INTO `hisdev20190226` VALUES ('14', '10054', '000001', '19.65', '2019-02-26 14:53:32');

-- ----------------------------
-- Table structure for hisdev20190227
-- ----------------------------
DROP TABLE IF EXISTS `hisdev20190227`;
CREATE TABLE `hisdev20190227` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mgrobjid` varchar(50) DEFAULT NULL,
  `pointid` varchar(50) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hisdev20190227
-- ----------------------------
INSERT INTO `hisdev20190227` VALUES ('1', '10004', '000001', '10.02', '2019-02-27 09:08:21');
INSERT INTO `hisdev20190227` VALUES ('2', '10014', '000001', '15.5', '2019-02-27 09:08:38');
INSERT INTO `hisdev20190227` VALUES ('3', '10024', '000001', '18.4', '2019-02-27 09:08:58');
INSERT INTO `hisdev20190227` VALUES ('4', '10044', '000001', '16.69', '2019-02-27 14:33:18');

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
) ENGINE=MyISAM AUTO_INCREMENT=415144 DEFAULT CHARSET=utf8;

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
INSERT INTO `syslog` VALUES ('414345', '00102', 'admin', '????:??????,', '2019-02-22 16:30:37', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414346', '00102', 'admin', '??????:,', '2019-02-22 16:30:38', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414347', '00102', 'admin', '??????:,', '2019-02-22 16:30:51', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414348', '00102', 'admin', '??????:,', '2019-02-22 16:30:54', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414349', '00102', 'admin', '????:??,', '2019-02-22 16:31:03', '/user/update', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414350', '00102', 'admin', '????:??????,', '2019-02-22 16:31:03', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414351', '00102', 'admin', '????:????,', '2019-02-22 16:31:13', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414352', '00102', 'admin', '??????:,', '2019-02-22 16:32:19', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414353', '00102', 'admin', '??????:,', '2019-02-22 16:32:20', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414354', '00102', 'admin', '??????:,', '2019-02-22 16:32:20', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414355', '00102', 'admin', '????:??????,', '2019-02-22 16:32:22', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414356', '00102', 'admin', '????:??,', '2019-02-22 16:32:25', '/user/delete', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414357', '00102', 'admin', '????:??????,', '2019-02-22 16:32:25', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414358', '00102', 'admin', '????:??,', '2019-02-22 16:32:26', '/user/delete', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414359', '00102', 'admin', '????:??????,', '2019-02-22 16:32:26', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414360', '00102', 'admin', '????:??,', '2019-02-22 16:32:29', '/user/delete', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414361', '00102', 'admin', '????:??????,', '2019-02-22 16:32:29', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414362', '00102', 'admin', '??????:,', '2019-02-22 16:32:32', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414363', '00102', 'admin', '??????:,', '2019-02-22 16:32:51', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414364', '00102', 'admin', '????:??,', '2019-02-22 16:32:57', '/user/add', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414365', '00102', 'admin', '????:??????,', '2019-02-22 16:32:57', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414366', '00102', 'admin', '????:????,', '2019-02-22 16:33:00', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414367', '00102', 'xiaodai', '??????:,', '2019-02-22 16:33:07', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414368', '00102', 'xiaodai', '??????:,', '2019-02-22 16:33:14', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414369', '00102', 'xiaodai', '??????:,', '2019-02-22 16:33:14', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414370', '00102', 'xiaodai', '????:??????,', '2019-02-22 16:35:00', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414371', '00102', 'xiaodai', '??????:,', '2019-02-22 16:35:02', '/service/tree', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414372', '00102', 'xiaodai', '????:??????,', '2019-02-22 16:41:24', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414373', '00102', 'xiaodai', '??????:,', '2019-02-22 16:47:12', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414374', '00102', 'xiaodai', '??????:,', '2019-02-22 16:47:12', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414375', '00102', 'xiaodai', '??????:,', '2019-02-22 17:48:52', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414376', '00102', 'xiaodai', '??????:,', '2019-02-22 17:48:52', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414377', '00102', 'xiaodai', '??????:,', '2019-02-22 17:49:12', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414378', '00102', 'xiaodai', '??????:,', '2019-02-22 17:53:58', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414379', '00102', 'xiaodai', '??????:,', '2019-02-22 18:01:51', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414380', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:19', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414381', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:19', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414382', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:27', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414383', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:29', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414384', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:33', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414385', '00102', 'xiaodai', '??????:,', '2019-02-22 18:02:36', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414386', '00102', 'xiaodai', '??????:,', '2019-02-25 09:09:55', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414387', '00102', 'xiaodai', '??????:,', '2019-02-25 09:10:15', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414388', '00102', 'xiaodai', '??????:,', '2019-02-25 09:10:15', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414389', '00102', 'xiaodai', '??????:???????,', '2019-02-25 09:10:33', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414390', '00102', 'xiaodai', '??????:???????,', '2019-02-25 09:10:35', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414391', '00102', 'xiaodai', '????:??????,', '2019-02-25 09:10:42', '/user/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414392', '00102', 'xiaodai', '??????:,', '2019-02-25 09:11:17', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414393', '00102', 'xiaodai', '??????:,', '2019-02-25 09:11:17', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414394', '00102', 'xiaodai', '??????:,', '2019-02-25 09:27:50', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414395', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:01', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414396', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:01', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414397', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:23', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414398', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:35', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414399', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:35', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414400', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:37', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414401', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:40', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414402', '00102', 'xiaodai', '??????:,', '2019-02-25 09:28:40', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414403', '00102', 'xiaodai', '??????:,', '2019-02-25 09:43:35', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414404', '00102', 'xiaodai', '??????:,', '2019-02-25 09:43:44', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414405', '00102', 'xiaodai', '??????:,', '2019-02-25 09:43:44', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414406', '00102', 'xiaodai', '??????:,', '2019-02-25 09:46:17', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414407', '00102', 'xiaodai', '??????:,', '2019-02-25 09:46:22', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414408', '00102', 'xiaodai', '??????:,', '2019-02-25 09:46:22', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414409', '00102', 'xiaodai', '??????:,', '2019-02-25 09:52:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414410', '00102', 'xiaodai', '??????:,', '2019-02-25 09:52:32', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414411', '00102', 'xiaodai', '??????:,', '2019-02-25 09:53:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414412', '00102', 'xiaodai', '??????:,', '2019-02-25 09:54:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414413', '00102', 'xiaodai', '??????:,', '2019-02-25 09:55:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414414', '00102', 'xiaodai', '??????:,', '2019-02-25 09:55:38', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414415', '00102', 'xiaodai', '??????:,', '2019-02-25 09:57:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414416', '00102', 'xiaodai', '??????:,', '2019-02-25 09:58:42', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414417', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:20', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414418', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:20', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414419', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:22', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414420', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:28', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414421', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:28', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414422', '00102', 'xiaodai', '??????:,', '2019-02-25 09:59:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414423', '00102', 'xiaodai', '??????:,', '2019-02-25 10:00:25', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414424', '00102', 'xiaodai', '??????:,', '2019-02-25 10:01:13', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414425', '00102', 'xiaodai', '??????:,', '2019-02-25 10:02:01', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414426', '00102', 'xiaodai', '??????:,', '2019-02-25 10:03:03', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414427', '00102', 'xiaodai', '??????:,', '2019-02-25 10:04:17', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414428', '00102', 'xiaodai', '??????:,', '2019-02-25 10:04:19', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414429', '00102', 'xiaodai', '??????:,', '2019-02-25 10:04:19', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414430', '00102', 'xiaodai', '??????:,', '2019-02-25 10:04:20', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414431', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:04:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414432', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:04:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414433', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:05:09', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414434', '00102', 'xiaodai', '??????:,', '2019-02-25 10:05:29', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414435', '00102', 'xiaodai', '??????:,', '2019-02-25 10:05:36', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414436', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:06:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414437', '00102', 'xiaodai', '??????:,', '2019-02-25 10:06:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414438', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:12:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414439', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:12:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414440', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:12:52', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414441', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:12:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414442', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:13:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414443', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:14:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414444', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:16:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414445', '00103', 'xiaodai', '??????:????????????????!null', '2019-02-25 10:18:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414446', '00102', 'xiaodai', '??????:,', '2019-02-25 10:19:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414447', '00102', 'xiaodai', '??????:,', '2019-02-25 10:19:34', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414448', '00102', 'xiaodai', '??????:,', '2019-02-25 10:27:38', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414449', '00102', 'xiaodai', '??????:,', '2019-02-25 10:27:46', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414450', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:23', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414451', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:23', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414452', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414453', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:31', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414454', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:31', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414455', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:31', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414456', '00102', 'xiaodai', '??????:,', '2019-02-25 10:29:31', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414457', '00102', 'xiaodai', '??????:,', '2019-02-25 10:30:22', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414458', '00102', 'xiaodai', '??????:,', '2019-02-25 10:31:18', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414459', '00102', 'xiaodai', '??????:,', '2019-02-25 10:31:18', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414460', '00102', 'xiaodai', '??????:,', '2019-02-25 10:31:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414461', '00102', 'xiaodai', '??????:,', '2019-02-25 10:35:02', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414462', '00102', 'xiaodai', '??????:,', '2019-02-25 10:35:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414463', '00102', 'xiaodai', '??????:,', '2019-02-25 10:38:48', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414464', '00102', 'xiaodai', '??????:,', '2019-02-25 10:39:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414465', '00102', 'xiaodai', '??????:,', '2019-02-25 10:39:34', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414466', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:05', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414467', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:09', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414468', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:09', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414469', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:39', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414470', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:39', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414471', '00102', 'xiaodai', '??????:,', '2019-02-25 10:45:55', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414472', '00102', 'xiaodai', '??????:,', '2019-02-25 10:48:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414473', '00102', 'xiaodai', '??????:,', '2019-02-25 10:49:08', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414474', '00102', 'xiaodai', '??????:,', '2019-02-25 10:49:30', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414475', '00102', 'xiaodai', '??????:,', '2019-02-25 10:50:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414476', '00102', 'xiaodai', '??????:,', '2019-02-25 10:50:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414477', '00102', 'xiaodai', '??????:,', '2019-02-25 10:53:37', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414478', '00102', 'xiaodai', '??????:,', '2019-02-25 10:53:52', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414479', '00102', 'xiaodai', '??????:,', '2019-02-25 10:54:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414480', '00102', 'xiaodai', '??????:,', '2019-02-25 10:54:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414481', '00102', 'xiaodai', '??????:,', '2019-02-25 10:54:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414482', '00102', 'xiaodai', '??????:,', '2019-02-25 10:54:47', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414483', '00102', 'xiaodai', '??????:,', '2019-02-25 10:55:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414484', '00102', 'xiaodai', '??????:,', '2019-02-25 10:55:11', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414485', '00102', 'xiaodai', '??????:,', '2019-02-25 10:59:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414486', '00102', 'xiaodai', '??????:,', '2019-02-25 10:59:50', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414487', '00102', 'xiaodai', '??????:,', '2019-02-25 11:03:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414488', '00102', 'xiaodai', '??????:,', '2019-02-25 11:03:18', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414489', '00102', 'xiaodai', '??????:,', '2019-02-25 11:04:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414490', '00102', 'xiaodai', '??????:,', '2019-02-25 11:04:19', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414491', '00102', 'xiaodai', '??????:,', '2019-02-25 11:04:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414492', '00102', 'xiaodai', '??????:,', '2019-02-25 11:04:22', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414493', '00102', 'xiaodai', '??????:,', '2019-02-25 11:05:37', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414494', '00102', 'xiaodai', '??????:,', '2019-02-25 11:05:37', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414495', '00102', 'xiaodai', '??????:,', '2019-02-25 11:10:43', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414496', '00102', 'xiaodai', '??????:,', '2019-02-25 11:12:10', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414497', '00102', 'xiaodai', '??????:,', '2019-02-25 11:12:43', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414498', '00102', 'xiaodai', '??????:,', '2019-02-25 11:12:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414499', '00102', 'xiaodai', '??????:,', '2019-02-25 11:13:23', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414500', '00102', 'xiaodai', '??????:,', '2019-02-25 11:13:23', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414501', '00102', 'xiaodai', '????:????,', '2019-02-25 11:14:41', '/user/out', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414502', '00102', 'admin', '??????:,', '2019-02-25 11:14:42', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414503', '00102', 'admin', '??????:,', '2019-02-25 11:14:42', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414504', '00102', 'admin', '??????:,', '2019-02-25 11:14:43', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414505', '00102', 'admin', '??????:,', '2019-02-25 11:14:43', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414506', '00102', 'admin', '??????:,', '2019-02-25 11:14:56', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414507', '00102', 'admin', '??????:,', '2019-02-25 11:14:56', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414508', '00102', 'admin', '??????:,', '2019-02-25 11:21:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414509', '00102', 'admin', '??????:,', '2019-02-25 11:21:27', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414510', '00102', 'admin', '??????:,', '2019-02-25 11:22:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414511', '00102', 'admin', '??????:,', '2019-02-25 11:22:18', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414512', '00102', 'admin', '??????:,', '2019-02-25 11:22:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414513', '00102', 'admin', '??????:,', '2019-02-25 11:22:43', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414514', '00102', 'admin', '??????:,', '2019-02-25 11:23:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414515', '00102', 'admin', '??????:,', '2019-02-25 11:23:41', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414516', '00102', 'admin', '??????:,', '2019-02-25 11:23:46', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414517', '00102', 'admin', '??????:,', '2019-02-25 11:23:46', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414518', '00102', 'admin', '??????:,', '2019-02-25 11:23:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414519', '00102', 'admin', '??????:,', '2019-02-25 11:23:58', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414520', '00102', 'admin', '??????:,', '2019-02-25 11:23:58', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414521', '00102', 'admin', '??????:,', '2019-02-25 11:25:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414522', '00102', 'admin', '??????:,', '2019-02-25 11:25:27', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414523', '00102', 'admin', '??????:,', '2019-02-25 11:25:27', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414524', '00102', 'admin', '??????:,', '2019-02-25 11:26:53', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414525', '00102', 'admin', '??????:,', '2019-02-25 11:26:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414526', '00102', 'admin', '??????:,', '2019-02-25 11:28:46', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414527', '00102', 'admin', '??????:,', '2019-02-25 11:28:46', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414528', '00102', 'admin', '??????:,', '2019-02-25 11:38:25', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414529', '00102', 'admin', '??????:,', '2019-02-25 11:38:25', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414530', '00102', 'admin', '??????:,', '2019-02-25 11:38:25', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414531', '00102', 'admin', '??????:,', '2019-02-25 11:38:30', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414532', '00102', 'admin', '??????:,', '2019-02-25 11:38:30', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414533', '00102', 'admin', '??????:,', '2019-02-25 11:38:30', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414534', '00102', 'admin', '??????:,', '2019-02-25 16:44:03', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414535', '00102', 'admin', '??????:,', '2019-02-25 16:44:03', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414536', '00102', 'admin', '??????:,', '2019-02-25 16:44:03', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414537', '00102', 'admin', '??????:,', '2019-02-25 16:44:03', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414538', '00102', 'admin', '??????:,', '2019-02-25 16:44:03', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414539', '00102', 'admin', '??????:,', '2019-02-25 16:44:11', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414540', '00102', 'admin', '??????:,', '2019-02-25 16:44:11', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414541', '00102', 'admin', '??????:,', '2019-02-25 16:44:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414542', '00102', 'admin', '??????:,', '2019-02-25 16:48:34', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414543', '00102', 'admin', '??????:,', '2019-02-25 16:48:34', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414544', '00102', 'admin', '??????:,', '2019-02-25 16:48:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414545', '00102', 'admin', '??????:,', '2019-02-25 16:48:35', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414546', '00102', 'admin', '??????:,', '2019-02-25 16:48:35', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414547', '00102', 'admin', '??????:,', '2019-02-25 16:54:29', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414548', '00102', 'admin', '??????:,', '2019-02-25 16:54:29', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414549', '00102', 'admin', '??????:,', '2019-02-25 16:54:29', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414550', '00102', 'admin', '??????:,', '2019-02-25 16:56:21', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414551', '00102', 'admin', '??????:,', '2019-02-25 16:56:21', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414552', '00102', 'admin', '??????:,', '2019-02-25 16:57:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414553', '00102', 'admin', '??????:,', '2019-02-25 16:57:18', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414554', '00102', 'admin', '??????:,', '2019-02-25 16:57:18', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414555', '00102', 'admin', '??????:,', '2019-02-25 17:36:50', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414556', '00102', 'admin', '??????:,', '2019-02-25 17:36:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414557', '00102', 'admin', '??????:,', '2019-02-25 17:36:50', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414558', '00102', 'admin', '??????:,', '2019-02-25 17:37:23', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414559', '00102', 'admin', '??????:,', '2019-02-25 17:37:23', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414560', '00102', 'admin', '??????:,', '2019-02-25 17:37:24', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414561', '00102', 'admin', '??????:,', '2019-02-25 17:37:24', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414562', '00102', 'admin', '??????:,', '2019-02-25 17:37:24', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414563', '00102', 'admin', '??????:,', '2019-02-25 17:37:54', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414564', '00102', 'admin', '??????:,', '2019-02-25 17:37:54', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414565', '00102', 'admin', '??????:,', '2019-02-25 17:37:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414566', '00102', 'admin', '??????:,', '2019-02-25 17:38:04', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414567', '00102', 'admin', '??????:,', '2019-02-25 17:38:04', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414568', '00102', 'admin', '??????:,', '2019-02-25 17:38:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414569', '00102', 'admin', '??????:,', '2019-02-25 17:38:06', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414570', '00102', 'admin', '??????:,', '2019-02-25 17:38:06', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414571', '00102', 'admin', '??????:,', '2019-02-25 17:43:39', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414572', '00102', 'admin', '??????:,', '2019-02-25 17:43:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414573', '00102', 'admin', '??????:,', '2019-02-25 17:43:39', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414574', '00102', 'admin', '??????:,', '2019-02-25 18:03:23', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414575', '00102', 'admin', '??????:,', '2019-02-25 18:03:23', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414576', '00102', 'admin', '??????:,', '2019-02-25 18:03:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414577', '00102', 'admin', '??????:,', '2019-02-25 18:03:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414578', '00102', 'admin', '??????:,', '2019-02-25 18:03:35', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414579', '00102', 'admin', '??????:,', '2019-02-25 18:03:35', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414580', '00102', 'admin', '??????:,', '2019-02-25 18:03:39', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414581', '00102', 'admin', '??????:,', '2019-02-25 18:03:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414582', '00102', 'admin', '??????:,', '2019-02-25 18:03:39', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414583', '00102', 'admin', '??????:,', '2019-02-25 18:04:43', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414584', '00102', 'admin', '??????:,', '2019-02-25 18:04:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414585', '00102', 'admin', '??????:,', '2019-02-25 18:04:43', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414586', '00102', 'admin', '??????:,', '2019-02-26 09:43:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414587', '00102', 'admin', '??????:,', '2019-02-26 09:43:35', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414588', '00103', 'admin', '??????:????????????????!null', '2019-02-26 09:44:00', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414589', '00102', 'admin', '??????:,', '2019-02-26 09:44:00', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414590', '00102', 'admin', '??????:,', '2019-02-26 09:44:00', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414591', '00102', 'admin', '??????:,', '2019-02-26 09:45:34', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414592', '00102', 'admin', '??????:,', '2019-02-26 09:45:34', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414593', '00103', 'admin', '??????:????????????????!null', '2019-02-26 09:45:34', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414594', '00102', 'admin', '??????:,', '2019-02-26 09:46:35', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414595', '00102', 'admin', '??????:,', '2019-02-26 09:46:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414596', '00103', 'admin', '??????:????????????????!null', '2019-02-26 09:47:10', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414597', '00102', 'admin', '??????:,', '2019-02-26 09:54:53', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414598', '00102', 'admin', '??????:,', '2019-02-26 09:54:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414599', '00103', 'admin', '??????:????????????????!null', '2019-02-26 09:55:54', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414600', '00102', 'admin', '??????:,', '2019-02-26 09:57:11', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414601', '00102', 'admin', '??????:,', '2019-02-26 09:57:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414602', '00102', 'admin', '??????:,', '2019-02-26 09:57:43', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414603', '00102', 'admin', '??????:,', '2019-02-26 09:57:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414604', '00102', 'admin', '??????:,', '2019-02-26 10:41:39', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414605', '00102', 'admin', '??????:,', '2019-02-26 10:41:40', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414606', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:41:47', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414607', '00102', 'admin', '??????:,', '2019-02-26 10:41:47', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414608', '00102', 'admin', '??????:,', '2019-02-26 10:41:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414609', '00102', 'admin', '??????:,', '2019-02-26 10:41:47', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414610', '00102', 'admin', '??????:,', '2019-02-26 10:41:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414611', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:41:47', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414612', '00102', 'admin', '??????:,', '2019-02-26 10:41:51', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414613', '00102', 'admin', '??????:,', '2019-02-26 10:41:51', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414614', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:41:51', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414615', '00102', 'admin', '??????:,', '2019-02-26 10:45:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414616', '00102', 'admin', '??????:,', '2019-02-26 10:45:19', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414617', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:46:41', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414618', '00102', 'admin', '??????:,', '2019-02-26 10:47:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414619', '00102', 'admin', '??????:,', '2019-02-26 10:47:12', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414620', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:48:47', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414621', '00102', 'admin', '??????:,', '2019-02-26 10:48:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414622', '00102', 'admin', '??????:,', '2019-02-26 10:48:53', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414623', '00102', 'admin', '??????:,', '2019-02-26 10:48:54', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414624', '00102', 'admin', '??????:,', '2019-02-26 10:48:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414625', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:53:00', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414626', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:53:07', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414627', '00102', 'admin', '??????:,', '2019-02-26 10:53:07', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414628', '00102', 'admin', '??????:,', '2019-02-26 10:53:07', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414629', '00102', 'admin', '??????:,', '2019-02-26 10:53:54', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414630', '00102', 'admin', '??????:,', '2019-02-26 10:53:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414631', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:54:05', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414632', '00103', 'admin', '??????:????????????????!null', '2019-02-26 10:54:16', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414633', '00103', 'admin', '??????:????????????????!null', '2019-02-26 11:00:35', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414634', '00103', 'admin', '??????:????????????????!null', '2019-02-26 11:03:57', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414635', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414636', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414637', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414638', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414639', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414640', '00102', 'admin', '??????:,', '2019-02-26 11:04:02', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414641', '00102', 'admin', '??????:,', '2019-02-26 11:11:38', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414642', '00102', 'admin', '??????:,', '2019-02-26 11:11:38', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414643', '00102', 'admin', '??????:,', '2019-02-26 11:11:38', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414644', '00102', 'admin', '??????:,', '2019-02-26 11:12:59', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414645', '00102', 'admin', '??????:,', '2019-02-26 11:12:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414646', '00102', 'admin', '??????:,', '2019-02-26 11:12:59', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414647', '00102', 'admin', '??????:,', '2019-02-26 11:15:26', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414648', '00102', 'admin', '??????:,', '2019-02-26 11:15:26', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414649', '00102', 'admin', '??????:,', '2019-02-26 11:15:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414650', '00102', 'admin', '??????:,', '2019-02-26 11:15:28', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414651', '00102', 'admin', '??????:,', '2019-02-26 11:15:28', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414652', '00102', 'admin', '??????:,', '2019-02-26 11:58:29', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414653', '00102', 'admin', '??????:,', '2019-02-26 11:58:29', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414654', '00102', 'admin', '??????:,', '2019-02-26 11:58:30', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414655', '00102', 'admin', '??????:,', '2019-02-26 11:58:30', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414656', '00102', 'admin', '??????:,', '2019-02-26 13:08:19', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414657', '00102', 'admin', '??????:,', '2019-02-26 13:10:49', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414658', '00102', 'admin', '??????:,', '2019-02-26 13:10:49', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414659', '00102', 'admin', '??????:,', '2019-02-26 13:10:51', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414660', '00102', 'admin', '??????:,', '2019-02-26 13:10:51', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414661', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:13:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414662', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:13:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414663', '00102', 'admin', '??????:,', '2019-02-26 13:13:39', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414664', '00102', 'admin', '??????:,', '2019-02-26 13:13:39', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414665', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:13:46', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414666', '00102', 'admin', '??????:,', '2019-02-26 13:13:46', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414667', '00102', 'admin', '??????:,', '2019-02-26 13:13:46', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414668', '00102', 'admin', '??????:,', '2019-02-26 13:14:05', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414669', '00102', 'admin', '??????:,', '2019-02-26 13:14:05', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414670', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:20:30', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414671', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:20:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414672', '00102', 'admin', '??????:,', '2019-02-26 13:20:43', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414673', '00102', 'admin', '??????:,', '2019-02-26 13:20:43', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414674', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:21:32', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414675', '00102', 'admin', '??????:,', '2019-02-26 13:21:57', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414676', '00102', 'admin', '??????:,', '2019-02-26 13:21:57', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414677', '00102', 'admin', '??????:,', '2019-02-26 13:21:57', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414678', '00102', 'admin', '??????:,', '2019-02-26 13:21:57', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414679', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:31:01', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414680', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:31:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414681', '00103', 'admin', '??????:????????????????!null', '2019-02-26 13:31:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414682', '00102', 'admin', '??????:,', '2019-02-26 13:31:41', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414683', '00102', 'admin', '??????:,', '2019-02-26 13:31:41', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414684', '00102', 'admin', '??????:,', '2019-02-26 13:33:10', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414685', '00102', 'admin', '??????:,', '2019-02-26 13:33:56', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414686', '00102', 'admin', '??????:,', '2019-02-26 13:33:56', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414687', '00102', 'admin', '??????:,', '2019-02-26 13:58:58', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414688', '00102', 'admin', '??????:,', '2019-02-26 13:58:58', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414689', '00102', 'admin', '??????:,', '2019-02-26 13:59:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414690', '00102', 'admin', '??????:,', '2019-02-26 13:59:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414691', '00102', 'admin', '??????:,', '2019-02-26 14:06:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414692', '00102', 'admin', '??????:,', '2019-02-26 14:07:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414693', '00102', 'admin', '??????:,', '2019-02-26 14:08:11', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414694', '00102', 'admin', '??????:,', '2019-02-26 14:08:11', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414695', '00102', 'admin', '??????:,', '2019-02-26 14:08:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414696', '00102', 'admin', '??????:,', '2019-02-26 14:19:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414697', '00102', 'admin', '??????:,', '2019-02-26 14:20:50', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414698', '00102', 'admin', '??????:,', '2019-02-26 14:20:50', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414699', '00102', 'admin', '??????:,', '2019-02-26 14:20:55', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414700', '00102', 'admin', '??????:,', '2019-02-26 14:22:10', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414701', '00102', 'admin', '??????:,', '2019-02-26 14:22:10', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414702', '00102', 'admin', '??????:,', '2019-02-26 14:22:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414703', '00102', 'admin', '??????:,', '2019-02-26 14:25:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414704', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:31:15', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414705', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:31:15', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414706', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:32:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414707', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:32:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414708', '00102', 'admin', '??????:,', '2019-02-26 14:33:44', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414709', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:44:02', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414710', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:46:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414711', '00103', 'admin', '??????:????????????????!null', '2019-02-26 14:51:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414712', '00102', 'admin', '??????:,', '2019-02-26 14:55:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414713', '00102', 'admin', '??????:,', '2019-02-26 14:57:48', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414714', '00102', 'admin', '??????:,', '2019-02-26 15:47:34', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414715', '00102', 'admin', '??????:,', '2019-02-26 15:47:34', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414716', '00103', 'admin', '??????:????????????????!null', '2019-02-26 15:49:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414717', '00103', 'admin', '??????:????????????????!null', '2019-02-26 15:49:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414718', '00103', 'admin', '??????:????????????????!null', '2019-02-26 15:49:55', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414719', '00103', 'admin', '??????:????????????????!null', '2019-02-26 15:50:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414720', '00103', 'admin', '??????:????????????????!null', '2019-02-26 15:52:00', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414721', '00102', 'admin', '??????:,', '2019-02-26 15:56:44', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414722', '00102', 'admin', '??????:,', '2019-02-26 15:57:03', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414723', '00102', 'admin', '??????:,', '2019-02-26 15:57:03', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414724', '00102', 'admin', '??????:,', '2019-02-26 15:58:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414725', '00102', 'admin', '??????:,', '2019-02-26 15:58:17', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414726', '00102', 'admin', '??????:,', '2019-02-26 15:58:17', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414727', '00102', 'admin', '??????:,', '2019-02-26 15:59:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414728', '00102', 'admin', '??????:,', '2019-02-26 15:59:23', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414729', '00102', 'admin', '??????:,', '2019-02-26 15:59:23', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414730', '00102', 'admin', '??????:,', '2019-02-26 16:00:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414731', '00102', 'admin', '??????:,', '2019-02-26 16:00:39', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414732', '00102', 'admin', '??????:,', '2019-02-26 16:00:39', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414733', '00102', 'admin', '??????:,', '2019-02-26 16:22:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414734', '00102', 'admin', '??????:,', '2019-02-26 16:22:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414735', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:22:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414736', '00102', 'admin', '??????:,', '2019-02-26 16:22:49', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414737', '00102', 'admin', '??????:,', '2019-02-26 16:22:49', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414738', '00102', 'admin', '??????:,', '2019-02-26 16:22:49', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414739', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:22:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414740', '00102', 'admin', '??????:,', '2019-02-26 16:22:49', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414741', '00102', 'admin', '??????:,', '2019-02-26 16:23:58', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414742', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:23:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414743', '00102', 'admin', '??????:,', '2019-02-26 16:23:58', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414744', '00102', 'admin', '??????:,', '2019-02-26 16:24:07', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414745', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:24:07', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414746', '00102', 'admin', '??????:,', '2019-02-26 16:24:07', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414747', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:25:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414748', '00102', 'admin', '??????:,', '2019-02-26 16:25:39', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414749', '00102', 'admin', '??????:,', '2019-02-26 16:28:31', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414750', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:28:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414751', '00102', 'admin', '??????:,', '2019-02-26 16:28:40', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414752', '00102', 'admin', '??????:,', '2019-02-26 16:28:40', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414753', '00102', 'admin', '??????:,', '2019-02-26 16:28:40', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414754', '00103', 'admin', '??????:????????????????!null', '2019-02-26 16:28:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414755', '00102', 'admin', '??????:,', '2019-02-26 16:28:40', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414756', '00102', 'admin', '??????:,', '2019-02-26 16:28:40', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414757', '00102', 'admin', '??????:,', '2019-02-26 16:46:31', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414758', '00102', 'admin', '??????:,', '2019-02-26 16:46:31', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414759', '00102', 'admin', '??????:???????,', '2019-02-26 16:46:32', '/time/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414760', '00102', 'admin', '??????:,', '2019-02-26 16:46:32', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414761', '00102', 'admin', '??????:,', '2019-02-26 16:46:32', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414762', '00102', 'admin', '??????:,', '2019-02-26 16:46:32', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414763', '00102', 'admin', '??????:,', '2019-02-26 16:55:41', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414764', '00102', 'admin', '??????:,', '2019-02-26 16:55:41', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414765', '00102', 'admin', '??????:,', '2019-02-26 16:55:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414766', '00102', 'admin', '??????:,', '2019-02-26 17:40:22', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414767', '00102', 'admin', '??????:,', '2019-02-26 17:40:22', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414768', '00102', 'admin', '??????:,', '2019-02-26 17:41:07', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414769', '00102', 'admin', '??????:,', '2019-02-26 18:04:38', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414770', '00102', 'admin', '??????:,', '2019-02-26 18:04:38', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414771', '00102', 'admin', '??????:,', '2019-02-26 18:04:38', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414772', '00102', 'admin', '??????:,', '2019-02-26 18:04:49', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414773', '00102', 'admin', '??????:,', '2019-02-26 18:04:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414774', '00102', 'admin', '??????:,', '2019-02-26 18:04:49', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414775', '00102', 'admin', '??????:,', '2019-02-27 09:03:54', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414776', '00102', 'admin', '??????:,', '2019-02-27 09:03:54', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414777', '00102', 'admin', '??????:,', '2019-02-27 09:04:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414778', '00102', 'admin', '??????:,', '2019-02-27 09:09:11', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414779', '00102', 'admin', '??????:,', '2019-02-27 09:09:11', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414780', '00102', 'admin', '??????:,', '2019-02-27 09:11:20', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414781', '00102', 'admin', '??????:,', '2019-02-27 09:12:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414782', '00102', 'admin', '??????:,', '2019-02-27 09:12:38', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414783', '00102', 'admin', '??????:,', '2019-02-27 09:12:38', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414784', '00102', 'admin', '??????:,', '2019-02-27 09:12:38', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414785', '00102', 'admin', '??????:,', '2019-02-27 09:15:47', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414786', '00102', 'admin', '??????:,', '2019-02-27 09:15:47', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414787', '00102', 'admin', '??????:,', '2019-02-27 09:18:36', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414788', '00102', 'admin', '??????:,', '2019-02-27 09:18:51', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414789', '00102', 'admin', '??????:,', '2019-02-27 09:29:17', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414790', '00102', 'admin', '??????:,', '2019-02-27 09:29:17', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414791', '00102', 'admin', '??????:,', '2019-02-27 09:29:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414792', '00102', 'admin', '??????:,', '2019-02-27 09:29:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414793', '00102', 'admin', '??????:,', '2019-02-27 09:29:47', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414794', '00103', 'admin', '??????:????????????????!null', '2019-02-27 09:29:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414795', '00102', 'admin', '??????:,', '2019-02-27 09:29:47', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414796', '00102', 'admin', '??????:,', '2019-02-27 09:32:23', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414797', '00102', 'admin', '??????:,', '2019-02-27 09:32:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414798', '00102', 'admin', '??????:,', '2019-02-27 09:32:23', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414799', '00102', 'admin', '??????:,', '2019-02-27 09:32:38', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414800', '00102', 'admin', '??????:,', '2019-02-27 09:32:38', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414801', '00102', 'admin', '??????:,', '2019-02-27 09:32:38', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414802', '00102', 'admin', '??????:,', '2019-02-27 09:32:47', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414803', '00102', 'admin', '??????:,', '2019-02-27 09:32:47', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414804', '00102', 'admin', '??????:,', '2019-02-27 09:32:48', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414805', '00102', 'admin', '??????:,', '2019-02-27 09:33:33', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414806', '00102', 'admin', '??????:,', '2019-02-27 09:33:33', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414807', '00102', 'admin', '??????:,', '2019-02-27 09:33:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414808', '00102', 'admin', '??????:,', '2019-02-27 09:34:26', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414809', '00102', 'admin', '??????:,', '2019-02-27 09:34:26', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414810', '00102', 'admin', '??????:,', '2019-02-27 09:34:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414811', '00102', 'admin', '??????:,', '2019-02-27 09:34:54', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414812', '00102', 'admin', '??????:,', '2019-02-27 09:46:24', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414813', '00102', 'admin', '??????:,', '2019-02-27 09:46:33', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414814', '00102', 'admin', '??????:,', '2019-02-27 09:46:34', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414815', '00102', 'admin', '??????:,', '2019-02-27 09:46:35', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414816', '00102', 'admin', '??????:,', '2019-02-27 09:46:40', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414817', '00102', 'admin', '??????:,', '2019-02-27 09:46:40', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414818', '00102', 'admin', '??????:,', '2019-02-27 09:46:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414819', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414820', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414821', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414822', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414823', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414824', '00102', 'admin', '??????:,', '2019-02-27 09:46:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414825', '00102', 'admin', '??????:,', '2019-02-27 09:47:41', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414826', '00102', 'admin', '??????:,', '2019-02-27 09:51:11', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414827', '00102', 'admin', '??????:,', '2019-02-27 09:51:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414828', '00102', 'admin', '??????:,', '2019-02-27 09:51:49', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414829', '00102', 'admin', '??????:,', '2019-02-27 09:51:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414830', '00102', 'admin', '??????:,', '2019-02-27 09:51:54', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414831', '00102', 'admin', '??????:,', '2019-02-27 09:51:54', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414832', '00102', 'admin', '??????:,', '2019-02-27 09:51:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414833', '00102', 'admin', '??????:,', '2019-02-27 09:52:50', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414834', '00102', 'admin', '??????:,', '2019-02-27 09:52:50', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414835', '00102', 'admin', '??????:,', '2019-02-27 09:52:51', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414836', '00102', 'admin', '??????:,', '2019-02-27 09:52:51', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414837', '00102', 'admin', '??????:,', '2019-02-27 09:52:51', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414838', '00102', 'admin', '??????:,', '2019-02-27 09:52:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414839', '00102', 'admin', '??????:,', '2019-02-27 09:53:00', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414840', '00102', 'admin', '??????:,', '2019-02-27 10:07:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414841', '00102', 'admin', '??????:,', '2019-02-27 10:07:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414842', '00102', 'admin', '??????:,', '2019-02-27 10:07:42', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414843', '00102', 'admin', '??????:,', '2019-02-27 10:07:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414844', '00102', 'admin', '??????:,', '2019-02-27 10:07:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414845', '00102', 'admin', '??????:,', '2019-02-27 10:07:46', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414846', '00102', 'admin', '??????:,', '2019-02-27 10:07:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414847', '00102', 'admin', '??????:,', '2019-02-27 10:09:32', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414848', '00102', 'admin', '??????:,', '2019-02-27 10:09:36', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414849', '00102', 'admin', '??????:,', '2019-02-27 10:09:43', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414850', '00102', 'admin', '??????:,', '2019-02-27 10:09:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414851', '00102', 'admin', '??????:,', '2019-02-27 10:09:43', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414852', '00102', 'admin', '??????:,', '2019-02-27 10:10:12', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414853', '00102', 'admin', '??????:,', '2019-02-27 10:10:12', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414854', '00102', 'admin', '??????:,', '2019-02-27 10:10:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414855', '00102', 'admin', '??????:,', '2019-02-27 10:10:14', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414856', '00102', 'admin', '??????:,', '2019-02-27 10:10:14', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414857', '00102', 'admin', '??????:,', '2019-02-27 10:19:21', '/service/report', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414858', '00102', 'admin', '??????:,', '2019-02-27 10:19:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414859', '00102', 'admin', '??????:,', '2019-02-27 10:19:21', '/service/power', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414860', '00102', 'admin', '??????:,', '2019-02-27 10:19:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414861', '00102', 'admin', '??????:,', '2019-02-27 10:27:04', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414862', '00102', 'admin', '??????:,', '2019-02-27 10:27:05', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414863', '00102', 'admin', '??????:,', '2019-02-27 10:27:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414864', '00102', 'admin', '??????:,', '2019-02-27 10:27:07', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414865', '00102', 'admin', '??????:,', '2019-02-27 10:27:08', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414866', '00102', 'admin', '??????:,', '2019-02-27 10:27:08', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414867', '00102', 'admin', '??????:,', '2019-02-27 10:28:13', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414868', '00102', 'admin', '??????:,', '2019-02-27 10:28:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414869', '00102', 'admin', '??????:,', '2019-02-27 10:28:13', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414870', '00102', 'admin', '??????:,', '2019-02-27 10:28:17', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414871', '00102', 'admin', '??????:,', '2019-02-27 10:28:17', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414872', '00102', 'admin', '??????:,', '2019-02-27 10:28:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414873', '00102', 'admin', '??????:,', '2019-02-27 10:28:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414874', '00102', 'admin', '??????:,', '2019-02-27 10:34:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414875', '00102', 'admin', '??????:,', '2019-02-27 10:35:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414876', '00102', 'admin', '??????:,', '2019-02-27 10:39:16', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414877', '00102', 'admin', '??????:,', '2019-02-27 10:40:29', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414878', '00102', 'admin', '??????:,', '2019-02-27 10:41:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414879', '00102', 'admin', '??????:,', '2019-02-27 10:41:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414880', '00102', 'admin', '??????:,', '2019-02-27 10:41:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414881', '00102', 'admin', '??????:,', '2019-02-27 10:41:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414882', '00102', 'admin', '??????:,', '2019-02-27 10:41:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414883', '00102', 'admin', '??????:,', '2019-02-27 10:41:15', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414884', '00102', 'admin', '??????:,', '2019-02-27 10:41:15', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414885', '00102', 'admin', '??????:,', '2019-02-27 10:41:16', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414886', '00102', 'admin', '??????:,', '2019-02-27 10:41:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414887', '00102', 'admin', '??????:,', '2019-02-27 10:41:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414888', '00102', 'admin', '??????:,', '2019-02-27 10:41:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414889', '00102', 'admin', '??????:,', '2019-02-27 10:41:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414890', '00102', 'admin', '??????:,', '2019-02-27 10:41:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414891', '00102', 'admin', '??????:,', '2019-02-27 10:41:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414892', '00102', 'admin', '??????:,', '2019-02-27 10:41:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414893', '00102', 'admin', '??????:,', '2019-02-27 10:41:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414894', '00102', 'admin', '??????:,', '2019-02-27 10:41:32', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414895', '00102', 'admin', '??????:,', '2019-02-27 10:41:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414896', '00102', 'admin', '??????:,', '2019-02-27 10:49:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414897', '00103', 'admin', '??????:????????????????!null', '2019-02-27 10:51:08', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414898', '00103', 'admin', '??????:????????????????!null', '2019-02-27 10:54:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414899', '00102', 'admin', '??????:,', '2019-02-27 10:55:01', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414900', '00102', 'admin', '??????:,', '2019-02-27 11:16:03', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414901', '00102', 'admin', '??????:,', '2019-02-27 11:16:15', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414902', '00102', 'admin', '??????:,', '2019-02-27 11:16:15', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414903', '00102', 'admin', '??????:,', '2019-02-27 11:16:16', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414904', '00102', 'admin', '??????:,', '2019-02-27 11:16:16', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414905', '00102', 'admin', '??????:,', '2019-02-27 14:13:56', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414906', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:14:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414907', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:14:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414908', '00102', 'admin', '??????:,', '2019-02-27 14:14:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414909', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:14:22', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414910', '00102', 'admin', '??????:,', '2019-02-27 14:16:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414911', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:18:14', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414912', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:26:37', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414913', '00102', 'admin', '??????:,', '2019-02-27 14:26:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414914', '00102', 'admin', '??????:,', '2019-02-27 14:26:45', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414915', '00102', 'admin', '??????:,', '2019-02-27 14:27:35', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414916', '00102', 'admin', '??????:,', '2019-02-27 14:27:35', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414917', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:28:27', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414918', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:28:27', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414919', '00102', 'admin', '??????:,', '2019-02-27 14:28:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414920', '00102', 'admin', '??????:,', '2019-02-27 14:28:33', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414921', '00102', 'admin', '??????:,', '2019-02-27 14:30:00', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414922', '00102', 'admin', '??????:,', '2019-02-27 14:30:00', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414923', '00102', 'admin', '??????:,', '2019-02-27 14:31:09', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414924', '00102', 'admin', '??????:,', '2019-02-27 14:31:09', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414925', '00102', 'admin', '??????:,', '2019-02-27 14:31:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414926', '00102', 'admin', '??????:,', '2019-02-27 14:31:47', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414927', '00102', 'admin', '??????:,', '2019-02-27 14:32:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414928', '00102', 'admin', '??????:,', '2019-02-27 14:32:06', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414929', '00102', 'admin', '??????:,', '2019-02-27 14:33:25', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414930', '00102', 'admin', '??????:,', '2019-02-27 14:33:25', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414931', '00102', 'admin', '??????:,', '2019-02-27 14:42:53', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414932', '00102', 'admin', '??????:,', '2019-02-27 14:42:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414933', '00102', 'admin', '??????:,', '2019-02-27 14:44:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414934', '00102', 'admin', '??????:,', '2019-02-27 14:44:26', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414935', '00102', 'admin', '??????:,', '2019-02-27 14:44:55', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414936', '00102', 'admin', '??????:,', '2019-02-27 14:44:55', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414937', '00102', 'admin', '??????:,', '2019-02-27 14:46:46', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414938', '00102', 'admin', '??????:,', '2019-02-27 14:46:46', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414939', '00102', 'admin', '??????:,', '2019-02-27 14:46:52', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414940', '00102', 'admin', '??????:,', '2019-02-27 14:46:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414941', '00102', 'admin', '??????:,', '2019-02-27 14:47:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414942', '00102', 'admin', '??????:,', '2019-02-27 14:48:37', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414943', '00102', 'admin', '??????:,', '2019-02-27 14:48:37', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414944', '00102', 'admin', '??????:,', '2019-02-27 14:49:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414945', '00102', 'admin', '??????:,', '2019-02-27 14:49:21', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414946', '00102', 'admin', '??????:,', '2019-02-27 14:49:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414947', '00102', 'admin', '??????:,', '2019-02-27 14:49:26', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414948', '00102', 'admin', '??????:,', '2019-02-27 14:49:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414949', '00102', 'admin', '??????:,', '2019-02-27 14:49:50', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414950', '00102', 'admin', '??????:,', '2019-02-27 14:49:55', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414951', '00102', 'admin', '??????:,', '2019-02-27 14:49:55', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414952', '00102', 'admin', '??????:,', '2019-02-27 14:50:48', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414953', '00102', 'admin', '??????:,', '2019-02-27 14:50:48', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414954', '00102', 'admin', '??????:,', '2019-02-27 14:51:10', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414955', '00102', 'admin', '??????:,', '2019-02-27 14:51:10', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414956', '00102', 'admin', '??????:,', '2019-02-27 14:51:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414957', '00102', 'admin', '??????:,', '2019-02-27 14:51:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414958', '00102', 'admin', '??????:,', '2019-02-27 14:53:40', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414959', '00102', 'admin', '??????:,', '2019-02-27 14:53:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414960', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:53:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414961', '00102', 'admin', '??????:,', '2019-02-27 14:53:45', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414962', '00102', 'admin', '??????:,', '2019-02-27 14:55:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414963', '00102', 'admin', '??????:,', '2019-02-27 14:55:26', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414964', '00103', 'admin', '??????:????????????????!null', '2019-02-27 14:55:29', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414965', '00102', 'admin', '??????:,', '2019-02-27 14:55:29', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414966', '00102', 'admin', '??????:,', '2019-02-27 15:07:09', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414967', '00102', 'admin', '??????:,', '2019-02-27 15:07:10', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414968', '00102', 'admin', '??????:,', '2019-02-27 15:07:10', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414969', '00102', 'admin', '??????:,', '2019-02-27 15:08:41', '/service/queryOverview', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414970', '00102', 'admin', '??????:,', '2019-02-27 15:08:41', '/service/rate', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414971', '00102', 'admin', '??????:,', '2019-02-27 15:08:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414972', '00102', 'admin', '??????:,', '2019-02-27 15:14:17', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414973', '00102', 'admin', '??????:,', '2019-02-27 15:14:17', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414974', '00102', 'admin', '??????:,', '2019-02-27 15:14:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414975', '00102', 'admin', '??????:,', '2019-02-27 15:14:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414976', '00103', 'admin', '??????:????????????????!null', '2019-02-27 15:14:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414977', '00102', 'admin', '??????:,', '2019-02-27 15:14:22', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414978', '00102', 'admin', '??????:,', '2019-02-27 15:14:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414979', '00102', 'admin', '??????:,', '2019-02-27 15:14:54', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414980', '00103', 'admin', '??????:????????????????!null', '2019-02-27 15:14:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414981', '00102', 'admin', '??????:,', '2019-02-27 15:14:58', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414982', '00102', 'admin', '??????:,', '2019-02-27 15:27:09', '/service/queryFloor', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414983', '00102', 'admin', '??????:,', '2019-02-27 15:27:09', '/service/query', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414984', '00102', 'admin', '??????:,', '2019-02-27 15:27:10', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414985', '00102', 'admin', '??????:,', '2019-02-27 15:27:10', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414986', '00102', 'admin', '??????:,', '2019-02-27 15:28:10', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414987', '00102', 'admin', '??????:,', '2019-02-27 15:28:10', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414988', '00102', 'admin', '??????:,', '2019-02-27 15:30:11', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414989', '00102', 'admin', '??????:,', '2019-02-27 15:30:11', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414990', '00103', 'admin', '??????:????????????????!null', '2019-02-27 15:30:16', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414991', '00102', 'admin', '??????:,', '2019-02-27 15:30:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414992', '00102', 'admin', '??????:,', '2019-02-27 15:30:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414993', '00102', 'admin', '??????:,', '2019-02-27 15:30:23', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414994', '00103', 'admin', '??????:????????????????!null', '2019-02-27 15:30:25', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414995', '00102', 'admin', '??????:,', '2019-02-27 15:30:25', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414996', '00102', 'admin', '??????:,', '2019-02-27 15:32:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414997', '00102', 'admin', '??????:,', '2019-02-27 15:32:53', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414998', '00102', 'admin', '??????:,', '2019-02-27 15:32:57', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('414999', '00102', 'admin', '??????:,', '2019-02-27 15:32:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415000', '00102', 'admin', '??????:,', '2019-02-27 15:40:50', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415001', '00102', 'admin', '??????:,', '2019-02-27 15:40:50', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415002', '00102', 'admin', '??????:,', '2019-02-27 15:40:51', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415003', '00102', 'admin', '??????:,', '2019-02-27 15:40:51', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415004', '00102', 'admin', '??????:,', '2019-02-27 15:42:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415005', '00102', 'admin', '??????:,', '2019-02-27 15:42:47', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415006', '00102', 'admin', '??????:,', '2019-02-27 15:48:22', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415007', '00102', 'admin', '??????:,', '2019-02-27 15:48:22', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415008', '00102', 'admin', '??????:,', '2019-02-27 15:48:29', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415009', '00102', 'admin', '??????:,', '2019-02-27 15:48:29', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415010', '00102', 'admin', '??????:,', '2019-02-27 15:58:43', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415011', '00102', 'admin', '??????:,', '2019-02-27 15:58:43', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415012', '00102', 'admin', '??????:,', '2019-02-27 15:58:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415013', '00102', 'admin', '??????:,', '2019-02-27 15:58:57', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415014', '00102', 'admin', '??????:,', '2019-02-27 15:59:05', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415015', '00102', 'admin', '??????:,', '2019-02-27 15:59:05', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415016', '00102', 'admin', '??????:,', '2019-02-27 15:59:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415017', '00102', 'admin', '??????:,', '2019-02-27 15:59:57', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415018', '00102', 'admin', '??????:,', '2019-02-27 16:03:03', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415019', '00102', 'admin', '??????:,', '2019-02-27 16:03:03', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415020', '00102', 'admin', '??????:,', '2019-02-27 16:05:49', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415021', '00102', 'admin', '??????:,', '2019-02-27 16:05:49', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415022', '00102', 'admin', '??????:,', '2019-02-27 16:06:53', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415023', '00102', 'admin', '??????:,', '2019-02-27 16:06:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415024', '00102', 'admin', '??????:,', '2019-02-27 16:07:00', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415025', '00102', 'admin', '??????:,', '2019-02-27 16:07:00', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415026', '00102', 'admin', '??????:,', '2019-02-27 16:07:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415027', '00102', 'admin', '??????:,', '2019-02-27 16:07:18', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415028', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:13:47', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415029', '00102', 'admin', '??????:,', '2019-02-27 16:13:47', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415030', '00102', 'admin', '??????:,', '2019-02-27 16:13:56', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415031', '00102', 'admin', '??????:,', '2019-02-27 16:13:56', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415032', '00102', 'admin', '??????:,', '2019-02-27 16:14:02', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415033', '00102', 'admin', '??????:,', '2019-02-27 16:14:02', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415034', '00102', 'admin', '??????:,', '2019-02-27 16:15:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415035', '00102', 'admin', '??????:,', '2019-02-27 16:15:46', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415036', '00102', 'admin', '??????:,', '2019-02-27 16:18:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415037', '00102', 'admin', '??????:,', '2019-02-27 16:18:38', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415038', '00102', 'admin', '??????:,', '2019-02-27 16:18:40', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415039', '00102', 'admin', '??????:,', '2019-02-27 16:19:45', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415040', '00102', 'admin', '??????:,', '2019-02-27 16:22:10', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415041', '00102', 'admin', '??????:,', '2019-02-27 16:22:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415042', '00102', 'admin', '??????:,', '2019-02-27 16:22:21', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415043', '00102', 'admin', '??????:,', '2019-02-27 16:22:24', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415044', '00102', 'admin', '??????:,', '2019-02-27 16:24:37', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415045', '00102', 'admin', '??????:,', '2019-02-27 16:24:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415046', '00102', 'admin', '??????:,', '2019-02-27 16:25:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415047', '00102', 'admin', '??????:,', '2019-02-27 16:25:24', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415048', '00102', 'admin', '??????:,', '2019-02-27 16:25:36', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415049', '00102', 'admin', '??????:,', '2019-02-27 16:26:33', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415050', '00102', 'admin', '??????:,', '2019-02-27 16:26:37', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415051', '00102', 'admin', '??????:,', '2019-02-27 16:26:40', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415052', '00102', 'admin', '??????:,', '2019-02-27 16:26:51', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415053', '00102', 'admin', '??????:,', '2019-02-27 16:26:53', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415054', '00102', 'admin', '??????:,', '2019-02-27 16:26:57', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415055', '00102', 'admin', '??????:,', '2019-02-27 16:26:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415056', '00102', 'admin', '??????:,', '2019-02-27 16:27:05', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415057', '00102', 'admin', '??????:,', '2019-02-27 16:27:06', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415058', '00102', 'admin', '??????:,', '2019-02-27 16:27:38', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415059', '00102', 'admin', '??????:,', '2019-02-27 16:27:39', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415060', '00102', 'admin', '??????:,', '2019-02-27 16:29:04', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415061', '00102', 'admin', '??????:,', '2019-02-27 16:29:07', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415062', '00102', 'admin', '??????:,', '2019-02-27 16:29:14', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415063', '00102', 'admin', '??????:,', '2019-02-27 16:29:15', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415064', '00102', 'admin', '??????:,', '2019-02-27 16:29:49', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415065', '00102', 'admin', '??????:,', '2019-02-27 16:29:51', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415066', '00102', 'admin', '??????:,', '2019-02-27 16:29:55', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415067', '00102', 'admin', '??????:,', '2019-02-27 16:29:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415068', '00102', 'admin', '??????:,', '2019-02-27 16:30:11', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415069', '00102', 'admin', '??????:,', '2019-02-27 16:30:16', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415070', '00102', 'admin', '??????:,', '2019-02-27 16:30:32', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415071', '00102', 'admin', '??????:,', '2019-02-27 16:30:33', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415072', '00102', 'admin', '??????:,', '2019-02-27 16:30:59', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415073', '00102', 'admin', '??????:,', '2019-02-27 16:31:01', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415074', '00102', 'admin', '??????:,', '2019-02-27 16:32:08', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415075', '00102', 'admin', '??????:,', '2019-02-27 16:32:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415076', '00102', 'admin', '??????:,', '2019-02-27 16:32:40', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415077', '00102', 'admin', '??????:,', '2019-02-27 16:33:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415078', '00102', 'admin', '??????:,', '2019-02-27 16:33:23', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415079', '00102', 'admin', '??????:,', '2019-02-27 16:34:44', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415080', '00102', 'admin', '??????:,', '2019-02-27 16:34:54', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415081', '00102', 'admin', '??????:,', '2019-02-27 16:34:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415082', '00102', 'admin', '??????:,', '2019-02-27 16:34:58', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415083', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:35:20', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415084', '00102', 'admin', '??????:,', '2019-02-27 16:35:20', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415085', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:36:01', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415086', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:36:01', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415087', '00102', 'admin', '??????:,', '2019-02-27 16:36:05', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415088', '00102', 'admin', '??????:,', '2019-02-27 16:36:05', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415089', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:36:13', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415090', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:36:13', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415091', '00102', 'admin', '??????:,', '2019-02-27 16:36:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415092', '00102', 'admin', '??????:,', '2019-02-27 16:36:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415093', '00102', 'admin', '??????:,', '2019-02-27 16:36:21', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415094', '00102', 'admin', '??????:,', '2019-02-27 16:36:21', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415095', '00102', 'admin', '??????:,', '2019-02-27 16:38:08', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415096', '00102', 'admin', '??????:,', '2019-02-27 16:38:09', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415097', '00102', 'admin', '??????:,', '2019-02-27 16:38:44', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415098', '00102', 'admin', '??????:,', '2019-02-27 16:38:44', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415099', '00102', 'admin', '??????:,', '2019-02-27 16:39:48', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415100', '00102', 'admin', '??????:,', '2019-02-27 16:39:48', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415101', '00102', 'admin', '??????:,', '2019-02-27 16:39:59', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415102', '00102', 'admin', '??????:,', '2019-02-27 16:39:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415103', '00102', 'admin', '??????:,', '2019-02-27 16:39:59', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415104', '00102', 'admin', '??????:,', '2019-02-27 16:39:59', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415105', '00102', 'admin', '??????:,', '2019-02-27 16:43:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415106', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:44:41', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415107', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:44:41', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415108', '00102', 'admin', '??????:,', '2019-02-27 16:44:44', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415109', '00102', 'admin', '??????:,', '2019-02-27 16:44:44', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415110', '00102', 'admin', '??????:,', '2019-02-27 16:47:31', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415111', '00102', 'admin', '??????:,', '2019-02-27 16:47:31', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415112', '00102', 'admin', '??????:,', '2019-02-27 16:48:03', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415113', '00102', 'admin', '??????:,', '2019-02-27 16:48:03', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415114', '00102', 'admin', '??????:,', '2019-02-27 16:49:25', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415115', '00102', 'admin', '??????:,', '2019-02-27 16:49:25', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415116', '00102', 'admin', '??????:,', '2019-02-27 16:49:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415117', '00102', 'admin', '??????:,', '2019-02-27 16:49:28', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415118', '00102', 'admin', '??????:,', '2019-02-27 16:50:23', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415119', '00102', 'admin', '??????:,', '2019-02-27 16:50:23', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415120', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:50:26', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415121', '00102', 'admin', '??????:,', '2019-02-27 16:50:26', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415122', '00102', 'admin', '??????:,', '2019-02-27 16:50:40', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415123', '00102', 'admin', '??????:,', '2019-02-27 16:50:40', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415124', '00102', 'admin', '??????:,', '2019-02-27 16:50:52', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415125', '00102', 'admin', '??????:,', '2019-02-27 16:50:52', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415126', '00102', 'admin', '??????:,', '2019-02-27 16:50:58', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415127', '00102', 'admin', '??????:,', '2019-02-27 16:50:58', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415128', '00102', 'admin', '??????:,', '2019-02-27 16:53:57', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415129', '00102', 'admin', '??????:,', '2019-02-27 16:53:57', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415130', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:54:03', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415131', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:54:03', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415132', '00102', 'admin', '??????:,', '2019-02-27 16:54:12', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415133', '00102', 'admin', '??????:,', '2019-02-27 16:54:12', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415134', '00102', 'admin', '??????:,', '2019-02-27 16:54:14', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415135', '00102', 'admin', '??????:,', '2019-02-27 16:54:14', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415136', '00102', 'admin', '??????:,', '2019-02-27 16:54:17', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415137', '00102', 'admin', '??????:,', '2019-02-27 16:54:17', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415138', '00102', 'admin', '??????:,', '2019-02-27 16:54:18', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415139', '00102', 'admin', '??????:,', '2019-02-27 16:54:18', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415140', '00102', 'admin', '??????:,', '2019-02-27 16:54:19', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415141', '00102', 'admin', '??????:,', '2019-02-27 16:54:19', '/service/allInfo', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415142', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:56:28', '/service/top', '192.168.16.6');
INSERT INTO `syslog` VALUES ('415143', '00103', 'admin', '??????:????????????????!null', '2019-02-27 16:56:28', '/service/allInfo', '192.168.16.6');

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
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1801383644', '管理员', 'admin', '1', '21232F297A57A5A743894A0E4A801FC3', '15228585858', '356155@qq.com', '2015-01-01 00:00:00', '2055-01-01 00:00:00', '1', '', '', null);
INSERT INTO `user` VALUES ('75', '0562963191', 'youtao', 'youtao', '2', 'C8172401710F5C502DE49A006FC51CD1', null, null, '2019-01-01 00:00:00', '2019-01-23 00:00:00', '1', null, '6137245932', null);
INSERT INTO `user` VALUES ('79', '687273855', 'xiaodai', 'xiaodai', '2', '8C994FCED635EEFDEC3D27A5EBEEA26F', null, null, '2019-02-20 00:00:00', '2019-02-28 00:00:00', '1', null, '111,112', '???_??1,???_??2');
