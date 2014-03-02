/*
SQLyog Enterprise - MySQL GUI v7.11 
MySQL - 5.5.32 : Database - sbgl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`sbgl` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sbgl`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `administratorid` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `privilege` int(11) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`id`,`administratorid`,`name`,`gender`,`telephone`,`email`,`photo`,`privilege`,`password`,`makedate`,`modifydate`) values (15,'10001','admin','0','13000000000','1@2.com','2.JPG',4,'admin','2014-03-02 08:26:19',NULL),(19,'a','a','0','','',NULL,0,'a','2014-03-02 08:29:11',NULL);

/*Table structure for table `bbspanel` */

DROP TABLE IF EXISTS `bbspanel`;

CREATE TABLE `bbspanel` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(15) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbspanel` */

/*Table structure for table `bbsshare` */

DROP TABLE IF EXISTS `bbsshare`;

CREATE TABLE `bbsshare` (
  `id` int(11) NOT NULL,
  `user_id` varchar(15) DEFAULT NULL,
  `bbs_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbsshare` */

/*Table structure for table `bbstag` */

DROP TABLE IF EXISTS `bbstag`;

CREATE TABLE `bbstag` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isSystemTag` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstag` */

/*Table structure for table `bbstagconnection` */

DROP TABLE IF EXISTS `bbstagconnection`;

CREATE TABLE `bbstagconnection` (
  `id` int(11) NOT NULL,
  `tagId` int(11) DEFAULT NULL,
  `BbsId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstagconnection` */

/*Table structure for table `bbstagfavourite` */

DROP TABLE IF EXISTS `bbstagfavourite`;

CREATE TABLE `bbstagfavourite` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstagfavourite` */

/*Table structure for table `bbstipicfavourite` */

DROP TABLE IF EXISTS `bbstipicfavourite`;

CREATE TABLE `bbstipicfavourite` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `topicId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstipicfavourite` */

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `equipment_id` int(11) DEFAULT NULL,
  `equipment_num` int(11) DEFAULT NULL,
  `userId` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL,
  `categoryname` varchar(200) DEFAULT NULL,
  `maketime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `categorydetail` */

DROP TABLE IF EXISTS `categorydetail`;

CREATE TABLE `categorydetail` (
  `categorydetailsid` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `userid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`categorydetailsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `categorydetail` */

/*Table structure for table `clazz` */

DROP TABLE IF EXISTS `clazz`;

CREATE TABLE `clazz` (
  `classid` int(11) NOT NULL,
  `classname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clazz` */

insert  into `clazz`(`classid`,`classname`) values (4,'班级1');

/*Table structure for table `computer` */

DROP TABLE IF EXISTS `computer`;

CREATE TABLE `computer` (
  `id` int(11) NOT NULL,
  `serialnumber` varchar(30) DEFAULT NULL,
  `computertype` int(11) DEFAULT NULL,
  `languagetype` varchar(2) DEFAULT NULL,
  `computermodelid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `computerstatusid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computer` */

insert  into `computer`(`id`,`serialnumber`,`computertype`,`languagetype`,`computermodelid`,`createtime`,`createuserid`,`status`,`remark`,`computerstatusid`) values (95,'pc111001-1',48,'0',38,'2014-03-02 01:07:17',1,0,'应该是备注哦！',3),(96,NULL,48,'1',38,'2014-03-02 01:07:17',1,0,'',3),(97,'pc00010-2',49,'0',38,'2014-03-02 01:07:31',1,0,'备注？',4),(98,NULL,49,'1',38,'2014-03-02 01:07:31',1,0,NULL,4),(99,'pc-3',50,'0',38,'2014-03-02 01:27:13',1,0,'备注！！！！！',1),(100,NULL,50,'1',38,'2014-03-02 01:27:13',1,0,'',1),(101,'ed2',51,'0',40,'2014-03-02 07:00:56',1,0,'',1),(102,NULL,51,'1',40,'2014-03-02 07:00:56',1,0,NULL,1);

/*Table structure for table `computercategory` */

DROP TABLE IF EXISTS `computercategory`;

CREATE TABLE `computercategory` (
  `id` int(11) NOT NULL,
  `computercategorytype` int(11) DEFAULT NULL,
  `languagetype` varchar(2) DEFAULT NULL,
  `parentcomputercategoryid` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computercategory` */

insert  into `computercategory`(`id`,`computercategorytype`,`languagetype`,`parentcomputercategoryid`,`name`,`createtime`,`createuserid`,`status`) values (45,23,'0',0,'工作站',NULL,1,0),(46,23,'1',0,'WorkStation',NULL,1,0),(47,24,'0',0,'剪辑机房',NULL,1,0),(48,24,'1',0,'Editing Room',NULL,1,0),(53,27,'0',0,'调色机房',NULL,1,0),(54,27,'1',0,'Coloring Room',NULL,1,0),(61,31,'0',0,'cate',NULL,15,0),(62,31,'1',0,'ca',NULL,15,0);

/*Table structure for table `computerconfig` */

DROP TABLE IF EXISTS `computerconfig`;

CREATE TABLE `computerconfig` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerconfig` */

/*Table structure for table `computerhomework` */

DROP TABLE IF EXISTS `computerhomework`;

CREATE TABLE `computerhomework` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `computerorderclassruleid` int(11) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `attachment` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerhomework` */

insert  into `computerhomework`(`id`,`name`,`computerorderclassruleid`,`content`,`createuserid`,`attachment`,`status`,`createtime`) values (44,'hhh',39,'ll',0,NULL,0,'2014-03-02 08:40:57'),(45,'cc',40,'',0,NULL,0,'2014-03-02 13:22:13');

/*Table structure for table `computerhomeworkreceiver` */

DROP TABLE IF EXISTS `computerhomeworkreceiver`;

CREATE TABLE `computerhomeworkreceiver` (
  `id` int(11) NOT NULL,
  `computerhomeworkid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `hasview` int(11) DEFAULT NULL,
  `hasorder` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerhomeworkreceiver` */

insert  into `computerhomeworkreceiver`(`id`,`computerhomeworkid`,`userid`,`hasview`,`hasorder`,`status`) values (79,44,20,0,0,0),(80,44,21,0,0,0),(81,45,20,0,0,0),(82,45,21,0,0,0);

/*Table structure for table `computermodel` */

DROP TABLE IF EXISTS `computermodel`;

CREATE TABLE `computermodel` (
  `id` int(11) NOT NULL,
  `computermodeltype` int(11) DEFAULT NULL,
  `languagetype` varchar(2) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `computercategoryid` int(11) DEFAULT NULL,
  `picpath` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `computercount` int(11) DEFAULT NULL,
  `availableborrowcountnumber` int(11) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computermodel` */

insert  into `computermodel`(`id`,`computermodeltype`,`languagetype`,`name`,`computercategoryid`,`picpath`,`createtime`,`createuserid`,`computercount`,`availableborrowcountnumber`,`description`,`status`) values (75,38,'0','戴尔 T7100 台式工作站',23,'1393710328042.jpg','2014-03-02 00:59:00',1,3,1,'戴尔 T7100 台式工作站中文描述。',0),(76,38,'1','DELL T7100 Workstation',23,'1393710328042.jpg','2014-03-02 00:59:00',1,3,1,'This is DELL T7100 description。',0),(77,39,'0','Mac Pro 工作站',23,NULL,'2014-03-02 01:04:11',1,0,0,'Mac Pro 工作站描述',0),(78,39,'1','Mac Pro Workstation',23,NULL,'2014-03-02 01:04:11',1,0,0,'Mac Pro Workstation description。',0),(79,40,'0','剪辑机房-1',24,'1393704036257.jpg','2014-03-02 01:16:37',1,1,1,'剪辑机房-1描述',0),(80,40,'1','Editing Room 1',24,'1393704036257.jpg','2014-03-02 01:16:37',1,1,1,'This is Editing Room 1 description。',0);

/*Table structure for table `computerorder` */

DROP TABLE IF EXISTS `computerorder`;

CREATE TABLE `computerorder` (
  `id` int(11) NOT NULL,
  `serialnumber` varchar(100) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `ordertype` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `rejectreason` varchar(500) DEFAULT NULL,
  `computerhomeworkid` int(11) DEFAULT NULL,
  `audituserid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorder` */

insert  into `computerorder`(`id`,`serialnumber`,`createuserid`,`title`,`ordertype`,`createtime`,`remark`,`rejectreason`,`computerhomeworkid`,`audituserid`,`status`) values (61,'PP001111393718858388',1,'预约1',1,'2014-03-02 08:07:38','',NULL,0,NULL,4),(62,'PP019111393720288615',19,'wo de',1,'2014-03-02 08:31:28','','',0,NULL,1),(63,'PP015111393720683546',15,'tilte',1,'2014-03-02 08:38:03','','',0,NULL,3);

/*Table structure for table `computerorderclassrule` */

DROP TABLE IF EXISTS `computerorderclassrule`;

CREATE TABLE `computerorderclassrule` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `classname` varchar(500) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `orderstarttime` datetime DEFAULT NULL,
  `orderendtime` datetime DEFAULT NULL,
  `availableordertime` int(11) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorderclassrule` */

insert  into `computerorderclassrule`(`id`,`name`,`classname`,`classid`,`orderstarttime`,`orderendtime`,`availableordertime`,`createuserid`,`createtime`,`status`) values (39,NULL,NULL,44,'2014-03-06 00:00:00','2014-03-15 00:00:00',4,NULL,NULL,NULL),(40,NULL,NULL,46,'2014-03-05 00:00:00','2014-03-20 00:00:00',4,NULL,NULL,NULL);

/*Table structure for table `computerorderclassruledetail` */

DROP TABLE IF EXISTS `computerorderclassruledetail`;

CREATE TABLE `computerorderclassruledetail` (
  `id` int(11) NOT NULL,
  `computerorderclassruleid` int(11) DEFAULT NULL,
  `allowedcomputermodelid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorderclassruledetail` */

insert  into `computerorderclassruledetail`(`id`,`computerorderclassruleid`,`allowedcomputermodelid`) values (60,39,38),(61,39,39),(62,40,38),(63,40,39);

/*Table structure for table `computerorderconfig` */

DROP TABLE IF EXISTS `computerorderconfig`;

CREATE TABLE `computerorderconfig` (
  `id` int(11) DEFAULT NULL,
  `openorder` int(11) DEFAULT NULL,
  `orderperiod` varchar(50) DEFAULT NULL,
  `orderperiodnum` int(11) DEFAULT NULL,
  `maxorderday` int(11) DEFAULT NULL,
  `orderintroduction` varbinary(1000) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `currentconfig` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorderconfig` */

insert  into `computerorderconfig`(`id`,`openorder`,`orderperiod`,`orderperiodnum`,`maxorderday`,`orderintroduction`,`createuserid`,`createtime`,`currentconfig`,`status`) values (1,1,'111',3,14,NULL,1,'2014-03-02',0,0),(2,0,'111',3,14,NULL,1,'2014-03-02',0,0),(3,1,'111',3,14,NULL,1,'2014-03-02',0,0),(4,0,'111',3,14,NULL,15,'2014-03-02',0,0),(5,1,'111',3,16,NULL,15,'2014-03-02',1,0);

/*Table structure for table `computerorderdetail` */

DROP TABLE IF EXISTS `computerorderdetail`;

CREATE TABLE `computerorderdetail` (
  `id` int(11) NOT NULL,
  `computerorderid` int(11) DEFAULT NULL,
  `computermodelid` int(11) DEFAULT NULL,
  `borrownumber` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `borrowday` datetime DEFAULT NULL,
  `borrowperiod` int(11) DEFAULT NULL,
  `computerid` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorderdetail` */

insert  into `computerorderdetail`(`id`,`computerorderid`,`computermodelid`,`borrownumber`,`createtime`,`borrowday`,`borrowperiod`,`computerid`,`status`) values (97,61,38,1,'2014-03-02 08:07:38','2014-03-05 00:00:00',2,NULL,4),(98,61,38,1,'2014-03-02 08:07:38','2014-03-06 00:00:00',4,NULL,4),(99,62,38,1,'2014-03-02 08:31:28','2014-03-06 00:00:00',2,NULL,1),(100,62,38,1,'2014-03-02 08:31:28','2014-03-07 00:00:00',4,NULL,1),(101,63,38,1,'2014-03-02 08:38:03','2014-03-06 00:00:00',2,NULL,3),(102,63,38,1,'2014-03-02 08:38:03','2014-03-06 00:00:00',4,NULL,3);

/*Table structure for table `computerstatus` */

DROP TABLE IF EXISTS `computerstatus`;

CREATE TABLE `computerstatus` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `availableborrow` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerstatus` */

insert  into `computerstatus`(`id`,`name`,`availableborrow`) values (1,'可借',1),(2,'维护',0),(3,'维修',0),(4,'遗失',0);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `coursetype` int(11) DEFAULT NULL,
  `languagetype` int(11) DEFAULT NULL,
  `adduserid` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`description`,`type`,`coursetype`,`languagetype`,`adduserid`,`teacherid`,`addtime`,`status`) values (44,'视觉效果',NULL,3,20,0,1,7,'2014-03-02 05:09:56',0),(45,'VFX',NULL,3,20,1,1,7,'2014-03-02 05:09:56',0),(46,'course1',NULL,4,21,0,15,24,'2014-03-02 13:13:27',0),(47,'c1',NULL,4,21,1,15,24,'2014-03-02 13:13:27',0);

/*Table structure for table `coursecomputer` */

DROP TABLE IF EXISTS `coursecomputer`;

CREATE TABLE `coursecomputer` (
  `id` int(11) NOT NULL,
  `lessonid` int(11) DEFAULT NULL,
  `computerid` int(11) DEFAULT NULL,
  `borrownum` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coursecomputer` */

/*Table structure for table `courseconfig` */

DROP TABLE IF EXISTS `courseconfig`;

CREATE TABLE `courseconfig` (
  `id` int(11) NOT NULL,
  `schoolyear` varchar(100) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `firstday` datetime DEFAULT NULL,
  `lastday` datetime DEFAULT NULL,
  `firstweekfirstday` datetime DEFAULT NULL,
  `weeknum` int(11) DEFAULT NULL,
  `currentsemester` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `courseconfig` */

insert  into `courseconfig`(`id`,`schoolyear`,`semester`,`firstday`,`lastday`,`firstweekfirstday`,`weeknum`,`currentsemester`,`status`) values (5,'2013-2014',1,NULL,NULL,NULL,5,1,1),(6,'2013-2014',1,'2014-03-03 00:00:00','2014-03-13 00:00:00','2014-03-03 00:00:00',12,1,1),(7,'2013-2014',1,'2014-03-03 00:00:00','2014-03-03 00:00:00','2014-03-05 00:00:00',455,1,1);

/*Table structure for table `courseforum` */

DROP TABLE IF EXISTS `courseforum`;

CREATE TABLE `courseforum` (
  `id` int(11) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `panel_id` int(11) DEFAULT NULL,
  `chapter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `courseforum` */

/*Table structure for table `courseschedule` */

DROP TABLE IF EXISTS `courseschedule`;

CREATE TABLE `courseschedule` (
  `id` int(11) NOT NULL,
  `courseid` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `adduserid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `courseschedule` */

/*Table structure for table `datapaging` */

DROP TABLE IF EXISTS `datapaging`;

CREATE TABLE `datapaging` (
  `datapagingid` int(11) NOT NULL COMMENT '数据分页数据主键',
  `tableid` int(11) DEFAULT NULL COMMENT '需要分页的表',
  `recordnum` int(11) DEFAULT NULL COMMENT '一页中的数据条数',
  PRIMARY KEY (`datapagingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datapaging` */

/*Table structure for table `equipmenborrow` */

DROP TABLE IF EXISTS `equipmenborrow`;

CREATE TABLE `equipmenborrow` (
  `borrowid` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `applytime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `borrowtime` datetime DEFAULT NULL,
  `returntime` datetime DEFAULT NULL,
  `reason` varchar(2000) DEFAULT NULL,
  `borrowaudituser` int(11) DEFAULT NULL,
  `returnaudituser` int(11) DEFAULT NULL,
  `teachersuggest` varchar(2000) DEFAULT NULL,
  `examstate` varchar(1) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL,
  `examuser` int(11) DEFAULT NULL,
  `homeworkid` int(11) DEFAULT NULL,
  `examdate` datetime DEFAULT NULL,
  PRIMARY KEY (`borrowid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmenborrow` */

insert  into `equipmenborrow`(`borrowid`,`category`,`userid`,`teacherid`,`applytime`,`status`,`borrowtime`,`returntime`,`reason`,`borrowaudituser`,`returnaudituser`,`teachersuggest`,`examstate`,`title`,`remark`,`examuser`,`homeworkid`,`examdate`) values (2,1,1,NULL,NULL,8,'2014-03-02 05:51:20','2014-03-02 07:13:38',NULL,1,1,NULL,'5','2014年03月02日-设备预约','dasd',1,NULL,'2014-03-02 03:36:59'),(4,1,1,NULL,NULL,1,'2014-03-02 00:00:00','2014-03-04 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,1,20,NULL,NULL,8,'2014-03-02 15:12:29','2014-03-02 15:15:53',NULL,20,20,NULL,'1','2014年03月06日-设备预约','ddff',20,NULL,'2014-03-02 15:10:08');

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `equipmentid` int(11) NOT NULL,
  `equipmentname` varchar(200) DEFAULT NULL,
  `brandid` int(11) DEFAULT NULL,
  `classificationid` int(11) DEFAULT NULL,
  `administrationid` int(11) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `equipmentnum` int(11) DEFAULT NULL,
  `activenum` int(11) DEFAULT NULL,
  `maintainnum` int(11) DEFAULT NULL,
  `repairnum` int(11) DEFAULT NULL,
  `losednum` int(11) DEFAULT NULL,
  `recyclingnum` int(11) DEFAULT NULL,
  `equipmentdetail` varchar(2000) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  `imgnamesaved` varchar(200) DEFAULT NULL,
  `imgname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`equipmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipment` */

insert  into `equipment`(`equipmentid`,`equipmentname`,`brandid`,`classificationid`,`administrationid`,`makedate`,`modifydate`,`equipmentnum`,`activenum`,`maintainnum`,`repairnum`,`losednum`,`recyclingnum`,`equipmentdetail`,`category`,`remark`,`lantype`,`comid`,`imgnamesaved`,`imgname`) values (37,'尼康',1,33,NULL,'2014-03-02 02:26:31','2014-03-02 02:58:16',1,1,NULL,NULL,NULL,NULL,'fghfhfghfghfgh',NULL,NULL,'0',46,'1393700293125.jpg','Chrysanthemum.jpg'),(38,'Nikon',1,33,NULL,'2014-03-02 02:26:31','2014-03-02 02:58:17',1,1,NULL,NULL,NULL,NULL,'sdfsdf',NULL,NULL,'1',46,'1393700293125.jpg','Chrysanthemum.jpg'),(39,'xinghao',1,31,15,'2014-03-02 14:14:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'jnlknknkn',NULL,NULL,'0',53,'',''),(40,'poipi',1,31,15,'2014-03-02 14:14:43',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,'1',53,'','');

/*Table structure for table `equipmentcategory` */

DROP TABLE IF EXISTS `equipmentcategory`;

CREATE TABLE `equipmentcategory` (
  `equipcategoryid` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipcategoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentcategory` */

/*Table structure for table `equipmentclassification` */

DROP TABLE IF EXISTS `equipmentclassification`;

CREATE TABLE `equipmentclassification` (
  `classificationid` int(11) NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `maketime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  PRIMARY KEY (`classificationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentclassification` */

insert  into `equipmentclassification`(`classificationid`,`parentid`,`name`,`maketime`,`modifytime`,`userid`,`lantype`,`comid`) values (29,0,'www','2014-03-01 21:21:00',NULL,1,'0',36),(30,0,'cccc','2014-03-01 21:21:00',NULL,1,'1',36),(31,29,'eeee','2014-03-02 00:48:25',NULL,1,'0',37),(32,29,'eeee','2014-03-02 00:48:26',NULL,1,'1',37),(33,29,'测试分类01','2014-03-02 01:17:36','2014-03-02 01:17:52',1,'0',38),(34,29,'test012','2014-03-02 01:17:36','2014-03-02 01:17:52',NULL,'1',38),(35,29,'测试分类2','2014-03-02 01:23:59',NULL,1,'0',39),(36,29,'testcate2','2014-03-02 01:24:00',NULL,1,'1',39),(37,0,'hhh','2014-03-02 14:10:18',NULL,15,'0',52),(38,0,'jjjj','2014-03-02 14:10:18',NULL,15,'1',52),(39,37,'kkkkk','2014-03-02 14:10:37',NULL,15,'0',53),(40,37,';;;;lll','2014-03-02 14:10:37',NULL,15,'1',53);

/*Table structure for table `equipmentdamage` */

DROP TABLE IF EXISTS `equipmentdamage`;

CREATE TABLE `equipmentdamage` (
  `id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `audit_administration_id` int(11) DEFAULT NULL,
  `detail` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentdamage` */

/*Table structure for table `equipmentdetail` */

DROP TABLE IF EXISTS `equipmentdetail`;

CREATE TABLE `equipmentdetail` (
  `equipdetailid` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `administrationid` int(11) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `sysremark` varchar(2000) DEFAULT NULL,
  `usermark` varchar(2000) DEFAULT NULL,
  `manufacturedate` date DEFAULT NULL,
  `acquiredate` date DEFAULT NULL,
  `manufacturer` varchar(200) DEFAULT NULL,
  `supplyer` varchar(200) DEFAULT NULL,
  `worth` float DEFAULT NULL,
  `usemanagedept` varchar(100) DEFAULT NULL,
  `manager` varchar(20) DEFAULT NULL,
  `storageplace` varchar(200) DEFAULT NULL,
  `storageposition` varchar(200) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  `storenumber` int(11) DEFAULT NULL,
  `equipserial` varchar(50) DEFAULT NULL,
  `assetnumber` int(11) DEFAULT NULL,
  `classificationid` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentdetail` */

insert  into `equipmentdetail`(`equipdetailid`,`equipmentid`,`status`,`administrationid`,`makedate`,`modifydate`,`sysremark`,`usermark`,`manufacturedate`,`acquiredate`,`manufacturer`,`supplyer`,`worth`,`usemanagedept`,`manager`,`storageplace`,`storageposition`,`lantype`,`comid`,`storenumber`,`equipserial`,`assetnumber`,`classificationid`) values (14,-1,'5',1,'2014-03-01 23:56:54','2014-03-02 02:04:01',NULL,'','1999-09-09','1999-09-09','','',676.78,'','','','',NULL,NULL,1,'1',1,0),(15,-1,'3',1,'2014-03-01 23:57:09','2014-03-02 02:04:01',NULL,'','1999-09-09','1999-09-09','','',676.78,'','','','',NULL,NULL,2,'2',2,0),(16,-1,'4',1,'2014-03-01 23:57:41','2014-03-02 02:04:01',NULL,'','1999-09-09','1999-09-09','','',676.78,'','','','',NULL,NULL,3,'3',3,0),(17,-1,'0',1,'2014-03-02 00:31:51','2014-03-02 02:04:02',NULL,'','1999-09-09','1999-09-09','','',45646.8,'','','','',NULL,NULL,6,'6',6,0),(18,-1,'1',1,'2014-03-02 00:32:18','2014-03-02 02:04:03',NULL,'','1999-09-09','1999-09-09','','',45646.8,'','','','',NULL,NULL,7,'7',7,0),(19,-1,'5',15,'2014-03-02 01:32:48','2014-03-02 14:18:58',NULL,'dsads','2014-03-01','2014-03-01','','',1111.03,'','','','',NULL,NULL,NULL,'',12333,0),(20,46,'0',1,'2014-03-02 01:32:58','2014-03-02 02:27:50',NULL,'dasdasd','2014-03-01','2014-03-01','','',1111.03,'','','','',NULL,NULL,NULL,'',123331,0),(21,-1,'0',1,'2014-03-02 01:33:09','2014-03-02 02:04:02',NULL,'123123','2014-03-01','2014-03-01','','',1111,'','','','',NULL,NULL,NULL,'',1231,31),(22,-1,'0',1,'2014-03-02 02:12:06','2014-03-02 02:25:48',NULL,'','1999-09-09','1999-09-09','','',454.67,'','','','',NULL,NULL,5555555,'555555',3455555,0);

/*Table structure for table `equipmentnum` */

DROP TABLE IF EXISTS `equipmentnum`;

CREATE TABLE `equipmentnum` (
  `equipmentNumid` int(11) NOT NULL,
  `enumdate` datetime DEFAULT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `borrownum` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipmentNumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentnum` */

/*Table structure for table `homework` */

DROP TABLE IF EXISTS `homework`;

CREATE TABLE `homework` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `homework` */

/*Table structure for table `imagemanage` */

DROP TABLE IF EXISTS `imagemanage`;

CREATE TABLE `imagemanage` (
  `imagemanageid` int(11) NOT NULL COMMENT 'id',
  `ownerid` int(11) DEFAULT NULL COMMENT '图片对应的所有者所在表的id',
  `imagecategory` varchar(1) DEFAULT NULL COMMENT '图片是设备图片还是人员图片',
  `imageurl` varchar(200) DEFAULT NULL COMMENT '图片存放地址',
  `imagetype` varchar(1) DEFAULT NULL COMMENT '同一张图片不同的切割大小辨别',
  PRIMARY KEY (`imagemanageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `imagemanage` */

/*Table structure for table `listdetail` */

DROP TABLE IF EXISTS `listdetail`;

CREATE TABLE `listdetail` (
  `listdetailid` int(11) NOT NULL,
  `borrowlistid` int(11) DEFAULT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `applynumber` int(11) DEFAULT NULL,
  `borrownumber` int(11) DEFAULT NULL,
  `borrowtime` datetime DEFAULT NULL,
  `returntime` datetime DEFAULT NULL,
  `ifdelay` varchar(1) DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  `equipdetailids` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`listdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `listdetail` */

insert  into `listdetail`(`listdetailid`,`borrowlistid`,`equipmentid`,`applynumber`,`borrownumber`,`borrowtime`,`returntime`,`ifdelay`,`comid`,`lantype`,`equipdetailids`) values (3,2,37,1,0,'2014-03-02 05:51:20','2014-03-02 07:13:38','N',46,'0',NULL),(5,4,37,1,NULL,'2014-03-02 00:00:00','2014-03-04 00:00:00','N',46,'0',NULL),(6,6,37,1,0,'2014-03-02 15:12:29','2014-03-02 15:15:53','N',46,'0',NULL);

/*Table structure for table `listequipdetail` */

DROP TABLE IF EXISTS `listequipdetail`;

CREATE TABLE `listequipdetail` (
  `listequipdetailid` int(11) NOT NULL,
  `listdetailid` int(11) DEFAULT NULL,
  `borrowlistid` int(11) DEFAULT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `equipstatus` varchar(1) DEFAULT NULL,
  `equipdetailid` int(11) DEFAULT NULL,
  PRIMARY KEY (`listequipdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `listequipdetail` */

insert  into `listequipdetail`(`listequipdetailid`,`listdetailid`,`borrowlistid`,`equipmentid`,`equipstatus`,`equipdetailid`) values (1,3,2,NULL,'4',20),(2,6,6,NULL,'4',20);

/*Table structure for table `maxno` */

DROP TABLE IF EXISTS `maxno`;

CREATE TABLE `maxno` (
  `NoType` varchar(30) NOT NULL,
  `MaxNo` int(11) NOT NULL,
  PRIMARY KEY (`NoType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产生最大的流水号，所有的号码从1开始';

/*Data for the table `maxno` */

insert  into `maxno`(`NoType`,`MaxNo`) values ('clazzId',4),('Computer',102),('Computercategory',62),('Computercategorytype',31),('Computerconfig',4),('Computerhomework',45),('Computerhomeworkreceiver',82),('Computermodel',80),('Computermodeltype',40),('Computerorder',63),('Computerorderclassrule',40),('Computerorderclassruledetail',63),('Computerorderconfig',5),('Computerorderdetail',102),('Computerstatus',4),('Computertype',51),('Course',47),('Coursecomputer',1208),('Courseconfig',7),('Courseschedule',738),('Coursetype',21),('equipClassificationComId',53),('equipClassificationId',40),('equipComId',53),('equipDetailComId',33),('equipDetailId',22),('equipId',40),('equipImgCode',18),('equipmenborrow',6),('ListDetail',6),('Listequipdetail',2),('Message',21),('Messagereceiver',18),('Ordercourserule',1),('Ordercourseruledetail',1),('relationId',24),('usergroupId',10),('userId',24),('userImgCode',2);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `senderid` int(11) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `replyid` int(11) DEFAULT NULL,
  `readstatus` int(11) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  `isbigfile` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`title`,`content`,`senderid`,`sendtime`,`replyid`,`readstatus`,`filepath`,`isbigfile`,`type`,`status`) values (16,'讨论','分页机制',1,'2014-02-24 10:31:47',0,0,NULL,0,0,1),(17,'你好','hello',1,'2014-02-24 10:34:36',0,0,NULL,0,0,1),(18,NULL,'嗖嗖嗖',1,'2014-02-24 10:52:14',16,0,NULL,0,0,1),(19,NULL,'<p>\n	嗯\n</p>\n<p>\n	<br />\n</p>',1,'2014-02-24 11:03:05',16,0,NULL,0,0,1),(20,NULL,'erty',1,'2014-02-24 19:58:22',16,0,NULL,0,0,1),(21,'test','ss',1,'2014-02-24 20:02:15',0,0,NULL,0,0,1);

/*Table structure for table `messagereceiver` */

DROP TABLE IF EXISTS `messagereceiver`;

CREATE TABLE `messagereceiver` (
  `id` int(11) NOT NULL,
  `messageid` int(11) DEFAULT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `hasview` int(11) DEFAULT NULL,
  `viewdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `messagereceiver` */

insert  into `messagereceiver`(`id`,`messageid`,`receiverid`,`hasview`,`viewdate`,`status`) values (15,16,1,0,NULL,1),(16,17,2,0,NULL,1),(17,21,1,0,NULL,1),(18,21,2,0,NULL,1);

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

/*Table structure for table `noticereply` */

DROP TABLE IF EXISTS `noticereply`;

CREATE TABLE `noticereply` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `notice_id` char(10) DEFAULT NULL,
  `cotent` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `noticereply` */

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `senderid` int(11) DEFAULT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `readstatus` int(11) DEFAULT NULL,
  `modeltype` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notification` */

/*Table structure for table `orderaudit` */

DROP TABLE IF EXISTS `orderaudit`;

CREATE TABLE `orderaudit` (
  `id` int(11) NOT NULL,
  `orderId` int(11) DEFAULT NULL,
  `auditUserId` varchar(15) DEFAULT NULL,
  `allowPass` int(11) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderaudit` */

/*Table structure for table `ordercourserule` */

DROP TABLE IF EXISTS `ordercourserule`;

CREATE TABLE `ordercourserule` (
  `courseruleid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `courserulename` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`courseruleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordercourserule` */

insert  into `ordercourserule`(`courseruleid`,`courseid`,`teacherid`,`courserulename`,`createtime`) values (1,46,20,'uukkk','2014-03-02 15:22:13');

/*Table structure for table `ordercourseruledetail` */

DROP TABLE IF EXISTS `ordercourseruledetail`;

CREATE TABLE `ordercourseruledetail` (
  `courseruledetailid` int(11) NOT NULL,
  `courseruleid` int(11) NOT NULL,
  `borrowlistid` int(11) DEFAULT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `applynumber` int(11) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`courseruledetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordercourseruledetail` */

insert  into `ordercourseruledetail`(`courseruledetailid`,`courseruleid`,`borrowlistid`,`equipmentid`,`applynumber`,`starttime`,`endtime`,`comid`,`lantype`) values (1,1,NULL,37,NULL,NULL,NULL,46,'0');

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `equipmentId` int(11) DEFAULT NULL,
  `applyNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

/*Table structure for table `orderlist` */

DROP TABLE IF EXISTS `orderlist`;

CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL,
  `userId` varchar(15) DEFAULT NULL,
  `auditUserId` varchar(15) DEFAULT NULL,
  `orderType` int(11) DEFAULT NULL,
  `applyTime` datetime DEFAULT NULL,
  `systemStatus` int(11) DEFAULT NULL,
  `auditStatus` int(11) DEFAULT NULL,
  `applyStartTime` datetime DEFAULT NULL,
  `applyEndTime` datetime DEFAULT NULL,
  `applyOrderNum` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderlist` */

/*Table structure for table `ordermessage` */

DROP TABLE IF EXISTS `ordermessage`;

CREATE TABLE `ordermessage` (
  `id` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `content` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `isBigFile` int(11) DEFAULT NULL,
  `filePath` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordermessage` */

/*Table structure for table `ordermessagereceiver` */

DROP TABLE IF EXISTS `ordermessagereceiver`;

CREATE TABLE `ordermessagereceiver` (
  `id` int(11) NOT NULL,
  `OrderMessageId` int(11) DEFAULT NULL,
  `receiverId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordermessagereceiver` */

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `Adm_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `score` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

/*Table structure for table `selectcourse` */

DROP TABLE IF EXISTS `selectcourse`;

CREATE TABLE `selectcourse` (
  `id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `selectcourse` */

/*Table structure for table `shortmessage` */

DROP TABLE IF EXISTS `shortmessage`;

CREATE TABLE `shortmessage` (
  `id` int(11) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shortmessage` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `studentid` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `couldborrow` varchar(1) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`studentid`,`name`,`gender`,`telephone`,`email`,`photo`,`password`,`classid`,`couldborrow`,`makedate`,`modifydate`) values (20,'11','学生1','0','','','','12',4,'0','2014-03-02 08:39:01','2014-03-02 08:40:52'),(21,'22','学生2','0','','','','22',4,'0','2014-03-02 08:39:15','2014-03-02 08:40:58'),(22,'33','学生3','0','','',NULL,'33',-1,'0','2014-03-02 08:41:50',NULL);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `teacherid` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`teacherid`,`name`,`gender`,`telephone`,`email`,`photo`,`password`,`makedate`,`modifydate`) values (24,'9999','aaa','1','','',NULL,'123','2014-03-02 13:13:07',NULL);

/*Table structure for table `teachercourse` */

DROP TABLE IF EXISTS `teachercourse`;

CREATE TABLE `teachercourse` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teachercourse` */

/*Table structure for table `uploaddoc` */

DROP TABLE IF EXISTS `uploaddoc`;

CREATE TABLE `uploaddoc` (
  `DocCode` varchar(8) NOT NULL,
  `ProjCode` varchar(20) DEFAULT NULL COMMENT '其他表的id',
  `DocName` varchar(100) DEFAULT NULL,
  `DocTypeCode` varchar(4) DEFAULT NULL,
  `DocPath` varchar(500) DEFAULT NULL,
  `MakeDate` datetime DEFAULT NULL,
  `FileNo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DocCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `uploaddoc` */

/*Table structure for table `usergroup` */

DROP TABLE IF EXISTS `usergroup`;

CREATE TABLE `usergroup` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `ownerid` varchar(15) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usergroup` */

insert  into `usergroup`(`id`,`name`,`ownerid`,`createtime`,`status`,`type`) values (3,'本科',NULL,'2014-03-01 17:05:08',NULL,1),(4,'硕士',NULL,'2014-03-01 17:05:21',NULL,1),(5,'博士',NULL,'2014-03-01 17:05:30',NULL,1),(6,'在职klk',NULL,'2014-03-01 17:05:44',NULL,2),(7,'离职',NULL,'2014-03-01 17:05:55',NULL,2),(9,'机房管理',NULL,'2014-03-02 08:24:14',NULL,3),(10,'器材管理',NULL,'2014-03-02 08:24:23',NULL,3);

/*Table structure for table `usergrouprelation` */

DROP TABLE IF EXISTS `usergrouprelation`;

CREATE TABLE `usergrouprelation` (
  `id` int(11) NOT NULL,
  `groupid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `grouptype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usergrouprelation` */

insert  into `usergrouprelation`(`id`,`groupid`,`userid`,`status`,`grouptype`) values (1,NULL,1,NULL,1),(2,NULL,2,NULL,1),(3,NULL,3,NULL,1),(15,9,15,NULL,3),(19,9,19,NULL,3),(20,3,20,NULL,1),(21,3,21,NULL,1),(22,3,22,NULL,1),(24,6,24,NULL,2);

/*Table structure for table `worker` */

DROP TABLE IF EXISTS `worker`;

CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `workid` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `worker` */

/*Table structure for table `loginuser` */

DROP TABLE IF EXISTS `loginuser`;

/*!50001 DROP VIEW IF EXISTS `loginuser` */;
/*!50001 DROP TABLE IF EXISTS `loginuser` */;

/*!50001 CREATE TABLE `loginuser` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userid` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `roletype` varchar(1) NOT NULL DEFAULT '',
  `privilege` varchar(11) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

/*View structure for view loginuser */

/*!50001 DROP TABLE IF EXISTS `loginuser` */;
/*!50001 DROP VIEW IF EXISTS `loginuser` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `loginuser` AS select `student`.`id` AS `id`,`student`.`studentid` AS `userid`,`student`.`name` AS `name`,`student`.`password` AS `password`,'1' AS `roletype`,'-1' AS `privilege`,`student`.`gender` AS `gender`,`student`.`telephone` AS `telephone`,`student`.`email` AS `email`,`student`.`photo` AS `photo` from `student` union select `teacher`.`id` AS `id`,`teacher`.`teacherid` AS `teacherId`,`teacher`.`name` AS `name`,`teacher`.`password` AS `password`,'2' AS `2`,'-1' AS `-1`,`teacher`.`gender` AS `gender`,`teacher`.`telephone` AS `telephone`,`teacher`.`email` AS `email`,`teacher`.`photo` AS `photo` from `teacher` union select `administrator`.`id` AS `id`,`administrator`.`administratorid` AS `administratorId`,`administrator`.`name` AS `name`,`administrator`.`password` AS `password`,'3' AS `3`,`administrator`.`privilege` AS `privilege`,`administrator`.`gender` AS `gender`,`administrator`.`telephone` AS `telephone`,`administrator`.`email` AS `email`,`administrator`.`photo` AS `photo` from `administrator` union select `worker`.`id` AS `id`,`worker`.`workid` AS `workId`,`worker`.`name` AS `name`,`worker`.`password` AS `password`,'4' AS `4`,'-1' AS `-1`,`worker`.`gender` AS `gender`,`worker`.`telephone` AS `telephone`,`worker`.`email` AS `email`,`worker`.`photo` AS `photo` from `worker` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
