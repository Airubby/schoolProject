/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : ismac

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-07-31 16:19:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for londebugger
-- ----------------------------
DROP TABLE IF EXISTS `londebugger`;
CREATE TABLE `londebugger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loggerinfo` varchar(255) DEFAULT NULL,
  `TM` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of londebugger
-- ----------------------------

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
  `sysxml` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `devxml` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('7056761912', '3', '172.18.34.103', '2061', '2062', 'V3', '2018-10-02T16:00:00.000Z2018-10-02T16:00:00.000', '1', '10000', '10000', '10000', 'sysconfig1.xml', 'one', 'devconfig1.xml');
INSERT INTO `service` VALUES ('7056761911', '3', '172.18.34.103', '2061', '2062', 'V3', '2018-10-02T16:00:00.000Z2018-10-02T16:00:00.000', '1', '10000', '10000', '10000', 'sysconfig0.xml', 'one', 'devconfig0.xml');
INSERT INTO `service` VALUES ('7056761913', '3', '172.18.34.103', '2061', '2062', 'V3', '2018-10-02T16:00:00.000Z2018-10-02T16:00:00.000', '1', '10000', '10000', '10000', 'sysconfig3.xml', 'one', 'devconfig3.xml');

-- ----------------------------
-- Table structure for slide
-- ----------------------------
DROP TABLE IF EXISTS `slide`;
CREATE TABLE `slide` (
  `id` varchar(50) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of slide
-- ----------------------------

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
) ENGINE=MyISAM AUTO_INCREMENT=434319 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('433500', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:58:49', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433501', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:58:49', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433502', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:58:49', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433503', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:58:59', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433504', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:58:59', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433505', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:58:59', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433506', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:14', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433507', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:59:14', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433508', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:14', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433509', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:29', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433510', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:59:29', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433511', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:29', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433512', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:44', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433513', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:59:44', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433514', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:44', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433515', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:59', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433516', '00102', 'admin', '数据服务模块:,', '2019-07-05 14:59:59', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433517', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 14:59:59', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433518', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:14', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433519', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:00:14', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433520', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:14', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433521', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:29', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433522', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:00:29', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433523', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:29', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433524', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:44', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433525', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:00:44', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433526', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:44', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433527', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:58', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433528', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:00:58', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433529', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:00:58', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433530', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:01:03', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433531', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:01:03', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433532', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 15:01:14', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433533', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:01:14', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433534', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:45', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433535', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:45', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433536', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:45', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433537', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:46', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433538', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:46', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433539', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:49', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433540', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:49', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433541', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:52', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433542', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:52', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433543', '00102', 'admin', '数据服务模块:,', '2019-07-05 15:04:57', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433544', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:31', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433545', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:31', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433546', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:31', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433547', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:46', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433548', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:46', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433549', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:01:46', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433550', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:01', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433551', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:01', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433552', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:01', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433553', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:16', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433554', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:16', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433555', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:16', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433556', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:31', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433557', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:31', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433558', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:31', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433559', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:38', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433560', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:38', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433561', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-05 16:02:40', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433562', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:40', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433563', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:40', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433564', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:42', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433565', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:46', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433566', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433567', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:02:49', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433568', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:04:18', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433569', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:44', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433570', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:44', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433571', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:44', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433572', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:45', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433573', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:45', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433574', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:47', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433575', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433576', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:48', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433577', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:49', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433578', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:43:53', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433579', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:53:20', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433580', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:39', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433581', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:39', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433582', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:39', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433583', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:40', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433584', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:40', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433585', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:41', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433586', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:41', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433587', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:56:43', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433588', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:57:20', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433589', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:57:20', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433590', '00102', 'admin', '数据服务模块:,', '2019-07-05 16:57:22', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433591', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:18', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433592', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:18', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433593', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:18', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433594', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:19', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433595', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:19', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433596', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:20', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433597', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:20', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433598', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:21', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433599', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:33:21', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433600', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:33:27', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433601', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:44', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433602', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:44', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433603', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:44', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433604', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:45', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433605', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:45', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433606', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:46', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433607', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:46', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433608', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:47', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433609', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433610', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:34:49', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433611', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:30', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433612', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:30', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433613', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:30', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433614', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:33', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433615', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:33', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433616', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:35', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433617', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:37', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433618', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:41', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433619', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:40:42', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433620', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:40:46', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433621', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:41:05', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433622', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:53', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433623', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:53', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433624', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:53', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433625', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:54', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433626', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:54', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433627', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:55', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433628', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:55', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433629', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:57', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433630', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:42:57', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433631', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:42:59', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433632', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:43:21', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433633', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:29', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433634', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:29', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433635', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:29', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433636', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:30', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433637', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:30', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433638', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:31', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433639', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:31', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433640', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:34', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433641', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:44:34', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433642', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:44:36', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433643', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:19', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433644', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:19', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433645', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:19', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433646', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:19', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433647', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:19', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433648', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:20', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433649', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:21', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433650', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:21', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433651', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:45:21', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433652', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:45:23', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433653', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:27', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433654', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:27', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433655', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:27', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433656', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:28', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433657', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:28', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433658', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:29', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433659', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:29', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433660', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:30', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433661', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:46:30', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433662', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 17:46:34', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433663', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:55', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433664', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:55', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433665', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:55', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433666', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:56', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433667', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:56', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433668', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:57', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433669', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:57', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433670', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:58', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433671', '00102', 'admin', '数据服务模块:,', '2019-07-05 17:59:58', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433672', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 18:00:05', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433673', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:42', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433674', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:42', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433675', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:42', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433676', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:43', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433677', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:43', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433678', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:44', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433679', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:44', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433680', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:45', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433681', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:01:45', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433682', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 18:01:47', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433683', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:42', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433684', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:42', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433685', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:42', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433686', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:43', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433687', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:43', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433688', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:44', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433689', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:44', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433690', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:46', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433691', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:08:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433692', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 18:08:49', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433693', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:40', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433694', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:40', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433695', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:40', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433696', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:41', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433697', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:41', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433698', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:42', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433699', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:42', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433700', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:43', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433701', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:09:43', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433702', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:21', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433703', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:21', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433704', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:21', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433705', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:22', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433706', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:22', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433707', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:23', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433708', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:23', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433709', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:24', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433710', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:13:24', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433711', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:09', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433712', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-05 18:16:13', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433713', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:25', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433714', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:25', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433715', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:25', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433716', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:25', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433717', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:25', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433718', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:26', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433719', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:26', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433720', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:27', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433721', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:27', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433722', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:16:50', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433723', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:17:56', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433724', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:17:56', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433725', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:17:58', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433726', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:17:59', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433727', '00102', 'admin', '数据服务模块:,', '2019-07-05 18:18:02', '/service/export', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433728', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:01:30', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433729', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:01:30', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433730', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:01:30', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433731', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:01:31', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433732', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:01:31', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433733', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:02:38', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433734', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:02:38', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433735', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:03:20', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433736', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:03:20', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433737', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:09', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433738', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:08:09', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433739', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:09', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433740', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:12', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433741', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:08:12', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433742', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:12', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433743', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:27', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433744', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:08:27', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433745', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-08 09:08:27', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433746', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:09:36', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433747', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:09:36', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433748', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:09:36', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433749', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:09:37', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433750', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:09:37', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433751', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:10:55', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433752', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:10:55', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433753', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:11:13', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433754', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:11:13', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433755', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:30', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433756', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:30', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433757', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:30', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433758', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:31', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433759', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:31', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433760', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:36:38', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433761', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:03', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433762', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:18', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433763', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:18', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433764', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:50', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433765', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:50', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433766', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:50', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433767', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:51', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433768', '00102', 'admin', '数据服务模块:,', '2019-07-08 09:37:51', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433769', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:17:49', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433770', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:38', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433771', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:38', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433772', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:38', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433773', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:39', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433774', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:39', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433775', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:42', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433776', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:24:42', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433777', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:19', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433778', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:19', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433779', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:23', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433780', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:23', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433781', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:43', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433782', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:43', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433783', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:46', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433784', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:25:46', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433785', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:26:51', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433786', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:26:51', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433787', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:37:15', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433788', '00102', 'admin', '数据服务模块:,', '2019-07-08 10:37:15', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433789', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:17', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433790', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:17', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433791', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:17', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433792', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:18', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433793', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:18', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433794', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:22', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433795', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:22', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433796', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:56', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433797', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:43:57', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433798', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:46:44', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433799', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:46:44', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433800', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:46:44', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433801', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:46:45', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433802', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:46:45', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433803', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:49:32', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433804', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:50:22', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433805', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:50:22', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433806', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:50:22', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433807', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:50:29', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433808', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:50:58', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433809', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:51:06', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433810', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:51:06', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433811', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:51:11', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433812', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:51:11', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433813', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:51:34', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433814', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:21', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433815', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:34', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433816', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:34', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433817', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:34', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433818', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:34', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433819', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:52:35', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433820', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:55:08', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433821', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:55:08', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433822', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:55:08', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433823', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:55:09', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433824', '00102', 'admin', '数据服务模块:,', '2019-07-08 11:55:09', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433825', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:06:17', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433826', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:06:17', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433827', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:06:17', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433828', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:06:18', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433829', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:06:18', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433830', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:16:46', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433831', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:16:46', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433832', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:16:46', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433833', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:16:48', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433834', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:16:48', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433835', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:21:40', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433836', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:21:40', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433837', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:01', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433838', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:35', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433839', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:35', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433840', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:35', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433841', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:35', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433842', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:36', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433843', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:22:47', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433844', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:04', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433845', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:04', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433846', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:04', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433847', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:05', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433848', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:05', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433849', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:11', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433850', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:11', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433851', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:14', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433852', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:26:14', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433853', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:27:37', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433854', '00102', 'admin', '数据服务模块:,', '2019-07-08 13:27:37', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433855', '00102', 'admin', '数据服务模块:,', '2019-07-08 14:24:14', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433856', '00102', 'admin', '数据服务模块:,', '2019-07-08 14:24:14', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433857', '00102', 'admin', '数据服务模块:,', '2019-07-08 14:24:14', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433858', '00102', 'admin', '数据服务模块:,', '2019-07-08 14:24:15', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433859', '00102', 'admin', '数据服务模块:,', '2019-07-08 14:24:15', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433860', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:51:55', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433861', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:51:55', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433862', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:51:55', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433863', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:52:03', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433864', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:52:03', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433865', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:53:48', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433866', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:53:48', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433867', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:53:48', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433868', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:53:49', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433869', '00102', 'admin', '数据服务模块:,', '2019-07-08 15:53:49', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433870', '00102', 'admin', '数据服务模块:,', '2019-07-08 16:39:10', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433871', '00102', 'admin', '数据服务模块:,', '2019-07-08 16:39:10', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433872', '00102', 'admin', '数据服务模块:,', '2019-07-08 16:39:10', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433873', '00102', 'admin', '数据服务模块:,', '2019-07-08 16:39:11', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433874', '00102', 'admin', '数据服务模块:,', '2019-07-08 16:39:11', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433875', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:36:04', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433876', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:36:04', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433877', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:36:04', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433878', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:36:05', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433879', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:36:05', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433880', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:42:28', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433881', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:42:28', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433882', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:43:41', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433883', '00102', 'admin', '数据服务模块:,', '2019-07-09 14:43:41', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433884', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:06', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433885', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:06', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433886', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:06', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433887', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:21', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433888', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:21', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433889', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:21', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433890', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:36', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433891', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:36', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433892', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:36', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433893', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:51', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433894', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:51', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433895', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:51', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433896', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:53', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433897', '00102', 'admin', '数据服务模块:,', '2019-07-09 17:45:53', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433898', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-15 14:26:19', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433899', '00102', 'admin', '数据服务模块:,', '2019-07-15 14:26:19', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433900', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-15 14:26:19', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433901', '00102', 'admin', '数据服务模块:,', '2019-07-15 14:26:20', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433902', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-15 14:26:20', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433903', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:18:26', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433904', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:18:35', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433905', '00102', 'admin', '数据服务模块:,', '2019-07-16 14:21:11', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433906', '00102', 'admin', '数据服务模块:,', '2019-07-16 14:21:11', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433907', '00102', 'admin', '数据服务模块:,', '2019-07-16 14:21:11', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433908', '00102', 'admin', '数据服务模块:,', '2019-07-16 14:21:13', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433909', '00102', 'admin', '数据服务模块:,', '2019-07-16 14:21:13', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433910', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:21:58', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433911', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:22:36', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433912', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:23:48', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433913', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:26:53', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433914', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:27:29', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433915', '00103', 'admin', ':系统日志查询访问接口方法不存在或者类内部错误!null', '2019-07-16 14:27:36', '/syslog/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433916', '00102', 'admin', '数据服务模块:,', '2019-07-17 17:44:52', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433917', '00102', 'admin', '数据服务模块:,', '2019-07-17 17:44:52', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433918', '00102', 'admin', '数据服务模块:,', '2019-07-17 17:44:52', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433919', '00102', 'admin', '数据服务模块:,', '2019-07-17 17:44:58', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433920', '00102', 'admin', '数据服务模块:,', '2019-07-17 17:44:58', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433921', '00102', 'admin', '数据服务模块:,', '2019-07-17 18:01:38', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433922', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-17 18:01:38', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433923', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:55:54', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433924', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:55:54', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433925', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:55:54', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433926', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:55:55', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433927', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:55:55', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433928', '00102', 'admin', '数据服务模块:,', '2019-07-18 09:56:09', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433929', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 09:56:09', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433930', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 09:56:44', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433931', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:15:02', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433932', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:15:02', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433933', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:15:06', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433934', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:15:06', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433935', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:15:18', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433936', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:16:11', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433937', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:16:11', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433938', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:16:14', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433939', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:16:14', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433940', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:16:25', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433941', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:17:32', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433942', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:17:32', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433943', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:17:36', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433944', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:17:36', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433945', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:17:51', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433946', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:01', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433947', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:01', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433948', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:01', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433949', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:02', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433950', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:02', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433951', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:05', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433952', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:20:05', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433953', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:20:08', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433954', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:20', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433955', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:20', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433956', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:20:22', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433957', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:20:22', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433958', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:20:25', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433959', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:31:04', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433960', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:31:04', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433961', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:31:06', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433962', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:31:06', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433963', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 10:31:14', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433964', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:08', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433965', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:08', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433966', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:08', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433967', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:09', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433968', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:09', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433969', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:18', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433970', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 10:32:18', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433971', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:20', '/service/update', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433972', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:32:20', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('433973', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:38:41', '/service/queryOverview', '172.18.34.103');
INSERT INTO `syslog` VALUES ('433974', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:38:41', '/service/indextop', '172.18.34.103');
INSERT INTO `syslog` VALUES ('433975', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:38:41', '/service/rate', '172.18.34.103');
INSERT INTO `syslog` VALUES ('433976', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:38:51', '/service/queryFloor', '172.18.34.103');
INSERT INTO `syslog` VALUES ('433977', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:38:51', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('433978', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:48:22', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433979', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:48:22', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433980', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:48:22', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433981', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:48:23', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433982', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:48:23', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433983', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:49:17', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433984', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:49:17', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433985', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:49:17', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433986', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:49:18', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433987', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:49:18', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433988', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:52:38', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433989', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:52:38', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433990', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:52:38', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433991', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:52:39', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433992', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:52:39', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433993', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:53:09', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433994', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:53:09', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433995', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:53:15', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433996', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:53:15', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433997', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:25', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433998', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:25', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('433999', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:25', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434000', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:26', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434001', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:26', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434002', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:44', '/service/queryOverview', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434003', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:44', '/service/indextop', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434004', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:44', '/service/rate', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434005', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:45', '/service/queryFloor', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434006', '00102', 'admin', '数据服务模块:,', '2019-07-18 10:54:45', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434007', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:43', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434008', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:43', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434009', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:44', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434010', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:44', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434011', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:44', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434012', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:46', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434013', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:01:46', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434014', '00102', 'admin', '用户模块:分页查询用户,', '2019-07-18 11:02:00', '/user/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434015', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:02:01', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434016', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:02:02', '/service/tableTitle', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434017', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:02:02', '/service/tableInfo', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434018', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:02:04', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434019', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:02:04', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434020', '00102', 'admin', '用户模块:分页查询用户,', '2019-07-18 11:05:02', '/user/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434021', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:05:04', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434022', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:05:05', '/service/tableTitle', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434023', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:05:05', '/service/tableInfo', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434024', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:21:31', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434025', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:21:31', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434026', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 11:21:53', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434027', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:28:28', '/service/queryFloor', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434028', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:28:28', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434029', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:28:45', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434030', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:28:45', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434031', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 11:29:00', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434032', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:35:02', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434033', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:35:02', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434034', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 11:35:13', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434035', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:36:15', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434036', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:36:15', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434037', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:36:17', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434038', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:36:17', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434039', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:37:45', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434040', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:37:45', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434041', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:37:47', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434042', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:37:47', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434043', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:41:59', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434044', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:41:59', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434045', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:42:01', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434046', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:42:01', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434047', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:48:53', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434048', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:48:53', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434049', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:48:56', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434050', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:48:56', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434051', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 11:52:26', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434052', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:52:26', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434053', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:52:27', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434054', '00102', 'admin', '数据服务模块:,', '2019-07-18 11:52:27', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434055', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:21', '/service/queryOverview', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434056', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:21', '/service/indextop', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434057', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:21', '/service/rate', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434058', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:23', '/service/queryFloor', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434059', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:23', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434060', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 13:17:26', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434061', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:26', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434062', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:28', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434063', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:17:28', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434064', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:21:15', '/service/detail', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434065', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 13:21:15', '/time/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434066', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:21:17', '/service/update', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434067', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:21:18', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434068', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:39', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434069', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:39', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434070', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:39', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434071', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:40', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434072', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:40', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434073', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:50', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434074', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:44:50', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434075', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 13:45:00', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434076', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:45:00', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434077', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:45:07', '/service/update', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434078', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:45:07', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434079', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:46:00', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434080', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:46:00', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434081', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:46:03', '/service/queryFloor', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434082', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:46:03', '/service/query', '172.18.34.103');
INSERT INTO `syslog` VALUES ('434083', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:13', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434084', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:13', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434085', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:13', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434086', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:14', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434087', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:14', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434088', '00102', 'admin', '数据服务模块:,', '2019-07-18 13:52:17', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434089', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 13:52:17', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434090', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:20', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434091', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:20', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434092', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:20', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434093', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:21', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434094', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:21', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434095', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:00:25', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434096', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 14:00:25', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434097', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:03:03', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434098', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:03:03', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434099', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:03:06', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434100', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 14:03:06', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434101', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:03:14', '/service/update', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434102', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:11:15', '/service/update', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434103', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:33', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434104', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:33', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434105', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:34', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434106', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:35', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434107', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:35', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434108', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:13:41', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434109', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 14:13:41', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434110', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 14:14:47', '/service/update', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434111', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:04', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434112', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:04', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434113', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:23', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434114', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:23', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434115', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:23', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434116', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:25', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434117', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:25', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434118', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:15:28', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434119', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 14:15:28', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434120', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-18 14:15:37', '/service/update', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434121', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:00', '/service/queryOverview', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434122', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:04', '/service/indextop', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434123', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:04', '/service/rate', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434124', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:11', '/service/queryFloor', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434125', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:11', '/service/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434126', '00102', 'admin', '数据服务模块:,', '2019-07-18 14:18:13', '/service/detail', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434127', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-18 14:18:13', '/time/query', '172.18.34.107');
INSERT INTO `syslog` VALUES ('434128', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:49:51', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434129', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:50:06', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434130', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:50:06', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434131', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:50:06', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434132', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:50:07', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434133', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:50:07', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434134', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-19 09:50:14', '/service/switchOrder', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434135', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:51:51', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434136', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:51:51', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434137', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:55:09', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434138', '00102', 'admin', '数据服务模块:,', '2019-07-19 09:55:09', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434139', '00103', 'admin', '数据服务模块:访问接口方法不存在或者类内部错误!null', '2019-07-19 09:55:48', '/service/switchOrder', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434140', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:14', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434141', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:14', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434142', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:14', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434143', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:15', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434144', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:15', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434145', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:21', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434146', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:55', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434147', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:27:55', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434148', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:28:00', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434149', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:29:05', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434150', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:29:05', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434151', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:29:05', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434152', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:29:06', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434153', '00102', 'admin', '数据服务模块:,', '2019-07-19 10:29:06', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434154', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:25', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434155', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:25', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434156', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:25', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434157', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:40', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434158', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:40', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434159', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:40', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434160', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:55', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434161', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:55', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434162', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:52:55', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434163', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:11', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434164', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:11', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434165', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:11', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434166', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:26', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434167', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:26', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434168', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:26', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434169', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:41', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434170', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:41', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434171', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:41', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434172', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:56', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434173', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:56', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434174', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:53:56', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434175', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:54:11', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434176', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:54:11', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434177', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:54:11', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434178', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:20', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434179', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:20', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434180', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:20', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434181', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:24', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434182', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:24', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434183', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:26', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434184', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:28', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434185', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:38', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434186', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:52:39', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434187', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:54:57', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434188', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:54:57', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434189', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:54:57', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434190', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:54:58', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434191', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:54:58', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434192', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:55:18', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434193', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:55:19', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434194', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:55:27', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434195', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:55:28', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434196', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:50', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434197', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:50', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434198', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:50', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434199', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:51', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434200', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:51', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434201', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:53', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434202', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:57:55', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434203', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:17', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434204', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:18', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434205', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:19', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434206', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:20', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434207', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:51', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434208', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:51', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434209', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:51', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434210', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:52', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434211', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:52', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434212', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:53', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434213', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:58:54', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434214', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:59:23', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434215', '00102', 'admin', '数据服务模块:,', '2019-07-26 08:59:24', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434216', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:05:37', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434217', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:05:38', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434218', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:28', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434219', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:28', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434220', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:28', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434221', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:28', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434222', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:28', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434223', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:32', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434224', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:33', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434225', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:57', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434226', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:08:58', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434227', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:39', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434228', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:40', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434229', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:54', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434230', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:54', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434231', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:54', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434232', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:55', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434233', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:55', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434234', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:57', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434235', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:12:58', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434236', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:13:17', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434237', '00102', 'admin', '数据服务模块:,', '2019-07-26 09:13:18', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434238', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:29', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434239', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:29', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434240', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:29', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434241', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:30', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434242', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:30', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434243', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:33', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434244', '00102', 'admin', '数据服务模块:,', '2019-07-25 17:59:33', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434245', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:08', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434246', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:08', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434247', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:08', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434248', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:09', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434249', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:09', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434250', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:10', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434251', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:10', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434252', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:15', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434253', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:02:15', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434254', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:43', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434255', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:43', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434256', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:43', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434257', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:44', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434258', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:44', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434259', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:47', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434260', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:06:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434261', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:07:22', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434262', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:07:22', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434263', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:08:54', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434264', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:08:54', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434265', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:10:47', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434266', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:10:47', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434267', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:30:59', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434268', '00102', 'admin', '数据服务模块:,', '2019-07-25 18:31:00', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434269', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:09', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434270', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:09', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434271', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:09', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434272', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:11', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434273', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:11', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434274', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:14', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434275', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:14', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434276', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:51', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434277', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:51', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434278', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:51', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434279', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:52', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434280', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:52', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434281', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:55', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434282', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:31:55', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434283', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:37:45', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434284', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:37:45', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434285', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:20', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434286', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:20', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434287', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:20', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434288', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:21', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434289', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:21', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434290', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:22', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434291', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:34:23', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434292', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:51:02', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434293', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:51:02', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434294', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:51:16', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434295', '00102', 'admin', '数据服务模块:,', '2019-07-26 15:51:36', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434296', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:05', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434297', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:05', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434298', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:05', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434299', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:06', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434300', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:06', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434301', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:07', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434302', '00102', 'admin', '数据服务模块:,', '2019-07-26 16:34:08', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434303', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:27', '/service/queryOverview', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434304', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:27', '/service/indextop', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434305', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:27', '/service/rate', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434306', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:28', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434307', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:28', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434308', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-31 15:30:32', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434309', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:37', '/service/tableTitle', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434310', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:37', '/service/tableInfo', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434311', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:38', '/service/queryFloor', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434312', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:38', '/service/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434313', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-31 15:30:43', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434314', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:30:43', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434315', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-31 15:31:15', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434316', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:31:15', '/service/detail', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434317', '00102', 'admin', '时间配置模块:获取时间组信息,', '2019-07-31 15:31:20', '/time/query', '127.0.0.1');
INSERT INTO `syslog` VALUES ('434318', '00102', 'admin', '数据服务模块:,', '2019-07-31 15:31:20', '/service/detail', '127.0.0.1');

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
INSERT INTO `user` VALUES ('1', '1801383644', '管理员 ', 'admin', '1', '21232F297A57A5A743894A0E4A801FC3', '15228585858', '356155@qq.com', '2015-01-01 00:00:00', '2055-01-01 00:00:00', '1', '', '', null);
