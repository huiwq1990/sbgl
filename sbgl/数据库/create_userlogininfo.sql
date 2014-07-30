drop table if exists userlogininfo;

/*==============================================================*/
/* Table: userlogininfo                                         */
/*==============================================================*/
create table userlogininfo
(
   id                   int not null,
   userid               int,
   isfirstlogin         varchar(5),
   logincount           int,
   lastlogintime        datetime,
   pagelanguage         varchar(5),
   remark               varchar(100),
   primary key (id)
);
