insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (1,1,`系统管理`,3);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (2,1,`本科`,1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (3,1,`硕士`,1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (4,1,`博士`,1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (5,1,`在职`,2);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (6,1,`退休`,2);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (7,1,`校外`,4);

insert  into `maxno`(`NoType`,`MaxNo`) values ('usergroupId',7);

insert  into  `administrator`(`id`,`administratorId`,`password`,`privilege`) values (1,`admin`,`admin`,1);

insert  into `maxno`(`NoType`,`MaxNo`) values ('userId',1);

insert  into `usergrouprelation`(`id`,`groupid`,`userid`) values (1,1,1);

insert  into `maxno`(`NoType`,`MaxNo`) values ('relationId',1);