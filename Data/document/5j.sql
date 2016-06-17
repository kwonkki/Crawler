/*
Navicat MySQL Data Transfer

Source Server         : MySQLWindows
Source Server Version : 50545
Source Host           : 127.0.0.1:3306
Source Database       : flightdb

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2016-06-13 09:55:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `5j`
-- ----------------------------
DROP TABLE IF EXISTS `5j`;
CREATE TABLE `5j` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `carrier` varchar(11) NOT NULL,
  `flightNumber` varchar(11) NOT NULL,
  `depAirport` varchar(11) NOT NULL,
  `depTime` varchar(20) NOT NULL,
  `arrAirport` varchar(11) NOT NULL,
  `arrTime` varchar(20) NOT NULL,
  `stopCities` varchar(0) DEFAULT NULL,
  `codeShare` varchar(0) DEFAULT NULL,
  `cabin` text,
  `aircraftCode` text,
  `data` text,
  `adultPrice` varchar(11) DEFAULT NULL,
  `adultTax` varchar(11) DEFAULT NULL,
  `childPrice` varchar(11) DEFAULT NULL,
  `childTax` varchar(11) DEFAULT NULL,
  `nationalityType` varchar(11) DEFAULT NULL,
  `nationality` text,
  `suitAge` text,
  `priceType` varchar(11) DEFAULT NULL,
  `applyType` varchar(11) DEFAULT NULL,
  `adultTaxType` varchar(11) DEFAULT NULL,
  `childTaxType` varchar(11) DEFAULT NULL,
  `ticketInvoiceType` varchar(11) DEFAULT NULL,
  `fromSegments` mediumtext,
  `retSegments` mediumtext,
  `hasRefund` varchar(11) DEFAULT NULL,
  `refund` text,
  `partRefund` varchar(11) DEFAULT NULL,
  `partRefundPrice` varchar(11) DEFAULT NULL,
  `hasEndorse` varchar(11) DEFAULT NULL,
  `endorse` text,
  `partEndorse` varchar(11) DEFAULT NULL,
  `partEndorsePrice` varchar(11) DEFAULT NULL,
  `endorsement` varchar(11) DEFAULT NULL,
  `hasBaggage` varchar(11) DEFAULT NULL,
  `baggage` text,
  `hasNoShow` varchar(11) DEFAULT NULL,
  `noShowLimitTime` varchar(11) DEFAULT NULL,
  `penalty` varchar(11) DEFAULT NULL,
  `specialNoShow` varchar(11) DEFAULT NULL,
  `other` text,
  `flyTime` text,
  `createTime` text,
  `count` text,
  `seats` bigint(255) DEFAULT '9',
  PRIMARY KEY (`id`),
  KEY `AK_index` (`carrier`,`flightNumber`,`depAirport`,`depTime`,`arrAirport`,`arrTime`,`adultPrice`,`adultTax`,`seats`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 5j
-- ----------------------------
