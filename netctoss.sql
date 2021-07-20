DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `recommender_id` int(9) DEFAULT NULL,
  `login_name` varchar(30) NOT NULL,
  `login_passwd` varchar(30) NOT NULL,
  `status` int(2) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `pause_date` varchar(20) DEFAULT NULL,
  `close_date` varchar(20) DEFAULT NULL,
  `real_name` varchar(20) NOT NULL,
  `idcard_no` varchar(18) DEFAULT NULL,
  `birthdate` varchar(20) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `telephone` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mailaddress` varchar(50) DEFAULT NULL,
  `zipcode` varchar(8) DEFAULT NULL,
  `last_login_time` varchar(20) DEFAULT NULL,
  `last_login_ip` varchar(15) DEFAULT NULL,
  `process_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  UNIQUE KEY `idcard_no` (`idcard_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1021 DEFAULT CHARSET=utf8;


LOCK TABLES `account` WRITE;
INSERT INTO `account` VALUES (1005,NULL,'taiji001','256528',1,'2008-03-15',NULL,NULL,'zhangsanfeng','410381194302256528','19430225',NULL,NULL,'13669351234',NULL,NULL,NULL,NULL,NULL,'2020-12-17 23:17:46'),(1007,NULL,'tom','12345',1,'2019-09-01',NULL,NULL,'lili','130825189439021214','1984-08-02',0,NULL,'13426484515',NULL,'','',NULL,NULL,'2020-12-30 19:57:34'),(1010,NULL,'xl18z60','190613',1,'2009-01-10',NULL,NULL,'guojing','330682196903190613','19690319',NULL,NULL,'13338924567',NULL,NULL,NULL,NULL,NULL,'2019-11-26 00:09:10'),(1011,NULL,'liming','123456',1,'2019-11-18',NULL,NULL,'liming','110111234567890981','1984-08-05',1,NULL,'13426481516',NULL,'Bohnenberg Str.11 In Hebei  ','068153',NULL,NULL,'2019-11-26 00:09:21'),(1012,NULL,'limingming','123456',1,'2019-11-18',NULL,NULL,'limingming','130825198408051516','1984-08-05',1,NULL,'13426481516',NULL,'Bohnenberg Str.11 In Hebei  ','068153',NULL,NULL,'2019-12-03 22:21:06'),(1013,NULL,'limi','123456',1,'2019-11-18',NULL,NULL,'lim','130825198408051517','1984-08-05',0,NULL,'13426481512',NULL,'Bohnenberg Str.11 In Hebei In China','068153',NULL,NULL,'2020-01-27 21:43:59'),(1014,NULL,'mili','123456',3,'2019-11-20',NULL,NULL,'mili','1234567',NULL,0,NULL,'13421212114',NULL,'uniklinik berg 11','1232',NULL,NULL,'2020-12-17 22:03:22'),(1018,NULL,'mili2','123456',1,'2019-12-06',NULL,NULL,'mili2','123456789',NULL,0,NULL,'13412121232',NULL,'uniklinik berg 11','1232',NULL,NULL,'2019-12-06 23:34:20'),(1019,NULL,'mili3','1234567',1,'2019-12-06',NULL,NULL,'mili3','123456788',NULL,0,NULL,'13412121232',NULL,'uniklinik berg 11','1232',NULL,NULL,'2019-12-06 23:38:57'),(1020,NULL,'mili5','1234567',1,'2019-12-27',NULL,NULL,'mili5','13082519840509131',NULL,0,NULL,'13412121232',NULL,'uniklinik berg 11','1232',NULL,NULL,NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_code` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enrolldate` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_code` (`admin_code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;


LOCK TABLES `admin_info` WRITE;
INSERT INTO `admin_info` VALUES (17,'admin','a123456','ren li','13426484515','renlk84@163.com','01-01-2021'),(18,'likun','a123456','likun','13426485531','renlk84@163.com','03-02-2021');
UNLOCK TABLES;


DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `admin_id` int(11) NOT NULL,
  `role_id` int(9) NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



LOCK TABLES `admin_role` WRITE;
INSERT INTO `admin_role` VALUES (17,1),(17,13),(18,53);
UNLOCK TABLES;


DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(9) NOT NULL,
  `bill_month` varchar(6) DEFAULT NULL,
  `cost` double(13,2) DEFAULT NULL,
  `parment_mode` char(1) DEFAULT NULL,
  `pay_state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `service_account_id_fk` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


LOCK TABLES `bill` WRITE;
INSERT INTO `bill` VALUES (1,1011,'202012',16.30,'1','1');
UNLOCK TABLES;


DROP TABLE IF EXISTS `bill_item`;

CREATE TABLE `bill_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_id` int(11) NOT NULL,
  `service_id` int(10) NOT NULL,
  `cost` double(13,2) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



LOCK TABLES `bill_item` WRITE;
INSERT INTO `bill_item` VALUES (1,1,2003,7.90),(2,1,2005,8.40);
UNLOCK TABLES;


DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `base_duration` varchar(8) DEFAULT NULL,
  `base_cost` varchar(8) DEFAULT NULL,
  `unit_cost` varchar(8) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `creatime` varchar(19) DEFAULT NULL,
  `startime` varchar(19) DEFAULT NULL,
  `costtype` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;



LOCK TABLES `cost` WRITE;
INSERT INTO `cost` VALUES (3,'7.9 Euro Pack','50','7.90','0.3000','1','7.9 Euro 50 Stunden/Monat,über den Teil ist 0.3 Euro pro Stunde','2019-09-29 21:03:28','2019-11-06 22:18:56','2'),(12,'8.3 Euro Pack','50','8.3','0.5','1','adfadfdsafdsafadsfsfdsa                    ','2019-11-10 22:43:18','2019-11-10 22:44:45','2'),(14,'8.4 Euro Pack','','','6.3','1','asdfadfafa          asdfadsfadsfffff','2019-11-10 22:48:09','2019-11-12 00:42:22','3');
UNLOCK TABLES;


DROP TABLE IF EXISTS `host`;
CREATE TABLE `host` (
  `id` varchar(15) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `host` WRITE;
INSERT INTO `host` VALUES ('192.168.0.26','sunv210','beijing');
UNLOCK TABLES;


DROP TABLE IF EXISTS `month_duration`;
CREATE TABLE `month_duration` (
  `service_id` int(10) NOT NULL DEFAULT '0',
  `month_id` char(6) NOT NULL DEFAULT '',
  `service_detail_id` int(11) DEFAULT NULL,
  `sofar_duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`service_id`,`month_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `month_duration` WRITE;
INSERT INTO `month_duration` VALUES (2003,'202012',NULL,120),(2005,'202012',NULL,60);
UNLOCK TABLES;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;


LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'Administrator'),(13,'networker'),(14,'abteilung'),(52,'kasse'),(53,'xiao');
UNLOCK TABLES;


DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `role_id` int(9) NOT NULL,
  `privilege_id` int(9) NOT NULL,
  PRIMARY KEY (`role_id`,`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `role_privilege` WRITE;
INSERT INTO `role_privilege` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,2),(2,3),(2,4),(13,1),(13,2),(13,4),(14,1),(14,2),(14,3),(52,4),(52,5),(52,7),(53,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` int(9) NOT NULL,
  `unix_host` varchar(15) NOT NULL,
  `os_username` varchar(8) NOT NULL,
  `login_passwd` varchar(8) NOT NULL,
  `status` char(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `pause_date` datetime DEFAULT NULL,
  `close_date` datetime DEFAULT NULL,
  `cost_id` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unix_host` (`unix_host`,`os_username`),
  KEY `fk_serv_acc_id` (`account_id`),
  KEY `fk_serv_cost_id` (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2006 DEFAULT CHARSET=utf8;


LOCK TABLES `service` WRITE;
INSERT INTO `service` VALUES (2003,1011,'192.168.0.26','huangr','huang234','1','2009-03-01 15:30:10','2020-12-23 23:09:39',NULL,3),(2004,1020,'192.168.0.26','s1','s11','3','2020-12-21 00:00:00','2020-12-23 23:06:56',NULL,3),(2005,1011,'192.168.0.26','s11','s11','1','2020-12-23 00:00:00',NULL,NULL,14);
UNLOCK TABLES;


DROP TABLE IF EXISTS `service_update`;
CREATE TABLE `service_update` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `service_id` int(10) NOT NULL,
  `unix_host` varchar(15) NOT NULL,
  `os_username` varchar(8) NOT NULL,
  `cost_id` int(4) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `service_id` (`service_id`),
  KEY `fk_supdate_cost_id` (`cost_id`),
  KEY `fk_supdate_host_uhost` (`unix_host`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `service_update` WRITE;
INSERT INTO `service_update` VALUES (2,2003,'192.168.0.26','huangr',12,'2020-12-19 01:07:56');
UNLOCK TABLES;

DROP TABLE IF EXISTS `servie_detail`;
CREATE TABLE `servie_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(10) NOT NULL,
  `client_host` varchar(15) DEFAULT NULL,
  `os_username` varchar(8) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `cost` double(20,9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `servie_detail` WRITE;
INSERT INTO `servie_detail` VALUES (1,2003,'192.168.0.26','huangr',NULL,'2020-12-23 09:35:00','2020-12-23 12:35:20',120,NULL),(2,2005,'192.168.0.126','s11',NULL,'2020-12-23 09:35:00','2020-12-11 08:30:00',60,NULL);
UNLOCK TABLES;