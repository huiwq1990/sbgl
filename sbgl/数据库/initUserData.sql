insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (1,1,'ϵͳ����',3);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (2,1,'����',1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (3,1,'˶ʿ',1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (4,1,'��ʿ',1);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (5,1,'��ְ',2);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (6,1,'����',2);
insert  into  `usergroup`(`id`,`ownerid`,`name`,`type`) values (7,1,'У��',4);

insert  into `maxno`(`NoType`,`MaxNo`) values ('usergroupId',7);


insert  into `administrator`(`id`,`administratorid`,`name`,`gender`,`telephone`,`email`,`photo`,`privilege`,`password`,`makedate`,`modifydate`) values (1,'admin','admin',NULL,NULL,NULL,NULL,1,'admin',NULL,NULL);


insert  into `maxno`(`NoType`,`MaxNo`) values ('userId',1);

insert  into `usergrouprelation`(`id`,`groupid`,`userid`) values (1,1,1);

insert  into `maxno`(`NoType`,`MaxNo`) values ('relationId',1);