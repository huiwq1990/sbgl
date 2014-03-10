/*
SQLyog Enterprise - MySQL GUI v7.11 
MySQL - 5.5.25 : Database - sbgl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `administrator` */

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

insert  into `administrator`(`id`,`administratorid`,`name`,`gender`,`telephone`,`email`,`photo`,`privilege`,`password`,`makedate`,`modifydate`) values (1,'admin','admin',NULL,NULL,NULL,'admin.jpg',1,'admin',NULL,NULL);

/*Table structure for table `bbspanel` */

CREATE TABLE `bbspanel` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `createuser` varchar(15) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbspanel` */

/*Table structure for table `bbsshare` */

CREATE TABLE `bbsshare` (
  `id` int(11) NOT NULL,
  `user_id` varchar(15) DEFAULT NULL,
  `bbs_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbsshare` */

/*Table structure for table `bbstag` */

CREATE TABLE `bbstag` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `issystemtag` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstag` */

/*Table structure for table `bbstagconnection` */

CREATE TABLE `bbstagconnection` (
  `id` int(11) NOT NULL,
  `tagid` int(11) DEFAULT NULL,
  `bbsid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstagconnection` */

/*Table structure for table `bbstagfavourite` */

CREATE TABLE `bbstagfavourite` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `tagid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstagfavourite` */

/*Table structure for table `bbstipicfavourite` */

CREATE TABLE `bbstipicfavourite` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `topicid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbstipicfavourite` */

/*Table structure for table `cart` */

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `equipment_id` int(11) DEFAULT NULL,
  `equipment_num` int(11) DEFAULT NULL,
  `userid` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `category` */

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

CREATE TABLE `categorydetail` (
  `categorydetailsid` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `userid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`categorydetailsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `categorydetail` */

/*Table structure for table `clazz` */

CREATE TABLE `clazz` (
  `classid` int(11) NOT NULL,
  `classname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clazz` */

/*Table structure for table `computer` */

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

/*Table structure for table `computercategory` */

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

/*Table structure for table `computerconfig` */

CREATE TABLE `computerconfig` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerconfig` */

/*Table structure for table `computerhomework` */

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

/*Table structure for table `computerhomeworkreceiver` */

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

/*Table structure for table `computermodel` */

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

/*Table structure for table `computerorder` */

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

/*Table structure for table `computerorderclassrule` */

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

/*Table structure for table `computerorderclassruledetail` */

CREATE TABLE `computerorderclassruledetail` (
  `id` int(11) NOT NULL,
  `computerorderclassruleid` int(11) DEFAULT NULL,
  `allowedcomputermodelid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerorderclassruledetail` */

/*Table structure for table `computerorderconfig` */

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

/*Table structure for table `computerorderdetail` */

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

/*Table structure for table `computerstatus` */

CREATE TABLE `computerstatus` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `availableborrow` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `computerstatus` */

insert  into `computerstatus`(`id`,`name`,`availableborrow`) values (1,'可借',1),(2,'维护',0),(3,'维修',0),(4,'遗失',0);

/*Table structure for table `course` */

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

/*Table structure for table `coursecomputer` */

CREATE TABLE `coursecomputer` (
  `id` int(11) NOT NULL,
  `lessonid` int(11) DEFAULT NULL,
  `computerid` int(11) DEFAULT NULL,
  `borrownum` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coursecomputer` */

/*Table structure for table `coursecomputerorder` */

CREATE TABLE `coursecomputerorder` (
  `id` int(11) NOT NULL,
  `semesterid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  `computerorderid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coursecomputerorder` */

/*Table structure for table `courseconfig` */

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

/*Table structure for table `courseforum` */

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

/*Table structure for table `courseschedulecomputerorder` */

CREATE TABLE `courseschedulecomputerorder` (
  `id` int(11) NOT NULL,
  `computercoursescheduleid` int(11) DEFAULT NULL,
  `computerorderid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `courseschedulecomputerorder` */

/*Table structure for table `datapaging` */

CREATE TABLE `datapaging` (
  `datapagingid` int(11) NOT NULL COMMENT '数据分页数据主键',
  `tableid` int(11) DEFAULT NULL COMMENT '需要分页的表',
  `recordnum` int(11) DEFAULT NULL COMMENT '一页中的数据条数',
  PRIMARY KEY (`datapagingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `datapaging` */

/*Table structure for table `equipmenborrow` */

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

/*Table structure for table `equipment` */

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

/*Table structure for table `equipmentcategory` */

CREATE TABLE `equipmentcategory` (
  `equipcategoryid` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipcategoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentcategory` */

/*Table structure for table `equipmentclassification` */

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

/*Table structure for table `equipmentdamage` */

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

/*Table structure for table `equipmentnum` */

CREATE TABLE `equipmentnum` (
  `equipmentnumid` int(11) NOT NULL,
  `enumdate` datetime DEFAULT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `borrownum` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipmentnumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipmentnum` */

/*Table structure for table `homework` */

CREATE TABLE `homework` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `homework` */

/*Table structure for table `imagemanage` */

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

CREATE TABLE `listdetail` (
  `listdetailid` int(11) NOT NULL,
  `borrowlistid` int(11) DEFAULT NULL,
  `applynumber` int(11) DEFAULT NULL,
  `borrownumber` int(11) DEFAULT NULL,
  `borrowtime` datetime DEFAULT NULL,
  `returntime` datetime DEFAULT NULL,
  `ifdelay` varchar(1) DEFAULT NULL,
  `comid` int(11) DEFAULT NULL,
  `lantype` varchar(2) DEFAULT NULL,
  `equipdetailids` varchar(1000) NOT NULL,
  PRIMARY KEY (`listdetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `listdetail` */

/*Table structure for table `maxno` */

CREATE TABLE `maxno` (
  `notype` varchar(30) NOT NULL,
  `maxno` int(11) NOT NULL,
  PRIMARY KEY (`notype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产生最大的流水号，所有的号码从1开始';

/*Data for the table `maxno` */

insert  into `maxno`(`notype`,`maxno`) values ('Computerstatus',4),('relationId',1),('usergroupId',7),('userId',1);

/*Table structure for table `message` */

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

/*Table structure for table `messagereceiver` */

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

/*Table structure for table `msgreceive` */

CREATE TABLE `msgreceive` (
  `id` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sendtime` date DEFAULT NULL,
  `readtime` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `msgreceive` */

/*Table structure for table `msgsend` */

CREATE TABLE `msgsend` (
  `id` int(11) NOT NULL,
  `senderid` int(11) DEFAULT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sendtime` date DEFAULT NULL,
  `readtime` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `msgsend` */

/*Table structure for table `notice` */

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

CREATE TABLE `orderaudit` (
  `id` int(11) NOT NULL,
  `orderid` int(11) DEFAULT NULL,
  `audituserid` varchar(15) DEFAULT NULL,
  `allowpass` int(11) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  `audittime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderaudit` */

/*Table structure for table `ordercourserule` */

CREATE TABLE `ordercourserule` (
  `courseruleid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `courserulename` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`courseruleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordercourserule` */

/*Table structure for table `ordercourseruledetail` */

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

/*Table structure for table `orderdetail` */

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `equipmentid` int(11) DEFAULT NULL,
  `applynumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

/*Table structure for table `orderlist` */

CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL,
  `userid` varchar(15) DEFAULT NULL,
  `audituserid` varchar(15) DEFAULT NULL,
  `ordertype` int(11) DEFAULT NULL,
  `applytime` datetime DEFAULT NULL,
  `systemstatus` int(11) DEFAULT NULL,
  `auditstatus` int(11) DEFAULT NULL,
  `applystarttime` datetime DEFAULT NULL,
  `applyendtime` datetime DEFAULT NULL,
  `applyordernum` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderlist` */

/*Table structure for table `ordermessage` */

CREATE TABLE `ordermessage` (
  `id` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `content` int(11) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `isbigfile` int(11) DEFAULT NULL,
  `filepath` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordermessage` */

/*Table structure for table `ordermessagereceiver` */

CREATE TABLE `ordermessagereceiver` (
  `id` int(11) NOT NULL,
  `ordermessageid` int(11) DEFAULT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordermessagereceiver` */

/*Table structure for table `score` */

CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `adm_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `score` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `score` */

/*Table structure for table `selectcourse` */

CREATE TABLE `selectcourse` (
  `id` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `selectcourse` */

/*Table structure for table `shortmessage` */

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

/*Table structure for table `teacher` */

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

/*Table structure for table `teachercourse` */

CREATE TABLE `teachercourse` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teachercourse` */

/*Table structure for table `uploaddoc` */

CREATE TABLE `uploaddoc` (
  `doccode` varchar(8) NOT NULL,
  `projcode` varchar(20) DEFAULT NULL COMMENT '其他表的id',
  `docname` varchar(100) DEFAULT NULL,
  `doctypecode` varchar(4) DEFAULT NULL,
  `docpath` varchar(500) DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `fileno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`doccode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `uploaddoc` */

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `usernumber` varchar(30) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `userpass` varchar(200) DEFAULT NULL,
  `phonenum` varchar(30) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `classbelong` int(11) DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `modifytime` date DEFAULT NULL,
  `privilege` int(11) DEFAULT NULL,
  `roletype` int(11) DEFAULT NULL,
  `createrid` int(11) DEFAULT NULL,
  `initpagelan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `usergroup` */

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

insert  into `usergroup`(`id`,`name`,`ownerid`,`createtime`,`status`,`type`) values (1,'系统管理','1',NULL,NULL,3),(2,'本科','1',NULL,NULL,1),(3,'硕士','1',NULL,NULL,1),(4,'博士','1',NULL,NULL,1),(5,'在职','1',NULL,NULL,2),(6,'退休','1',NULL,NULL,2),(7,'校外','1',NULL,NULL,4);

/*Table structure for table `usergrouprelation` */

CREATE TABLE `usergrouprelation` (
  `id` int(11) NOT NULL,
  `groupid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `grouptype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usergrouprelation` */

insert  into `usergrouprelation`(`id`,`groupid`,`userid`,`status`,`grouptype`) values (1,1,1,NULL,NULL);

/*Table structure for table `worker` */

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

/*!50001 CREATE TABLE `loginuser` (
  `id` int(11) NOT NULL DEFAULT '0',
  `userId` varchar(15) DEFAULT NULL,
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
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `loginuser` AS select `student`.`id` AS `id`,`student`.`studentid` AS `userId`,`student`.`name` AS `name`,`student`.`password` AS `password`,'1' AS `roletype`,'-1' AS `privilege`,`student`.`gender` AS `gender`,`student`.`telephone` AS `telephone`,`student`.`email` AS `email`,`student`.`photo` AS `photo` from `student` union select `teacher`.`id` AS `id`,`teacher`.`teacherid` AS `teacherId`,`teacher`.`name` AS `name`,`teacher`.`password` AS `password`,'2' AS `2`,'-1' AS `-1`,`teacher`.`gender` AS `gender`,`teacher`.`telephone` AS `telephone`,`teacher`.`email` AS `email`,`teacher`.`photo` AS `photo` from `teacher` union select `administrator`.`id` AS `id`,`administrator`.`administratorid` AS `administratorId`,`administrator`.`name` AS `name`,`administrator`.`password` AS `password`,'3' AS `3`,`administrator`.`privilege` AS `privilege`,`administrator`.`gender` AS `gender`,`administrator`.`telephone` AS `telephone`,`administrator`.`email` AS `email`,`administrator`.`photo` AS `photo` from `administrator` union select `worker`.`id` AS `id`,`worker`.`workid` AS `workId`,`worker`.`name` AS `name`,`worker`.`password` AS `password`,'4' AS `4`,'-1' AS `-1`,`worker`.`gender` AS `gender`,`worker`.`telephone` AS `telephone`,`worker`.`email` AS `email`,`worker`.`photo` AS `photo` from `worker` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;