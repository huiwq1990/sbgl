create table userlogininfo
(
   id                   int not null,
   userid               int,
   isfirstlogin         varchar(5),
   logincount           int,
   lastlogintime        datetime,
   remark               varchar(100),
   primary key (id)
);