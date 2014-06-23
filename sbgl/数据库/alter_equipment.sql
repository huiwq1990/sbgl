ALTER TABLE `sbgl`.`equipment` DROP COLUMN `rent` , ADD COLUMN `rentvalue` FLOAT NULL  AFTER `imgname` , ADD COLUMN `rentunit` INT NULL  AFTER `rentvalue` ;

create table rentunit
(
   id                   int not null,
   unitname             varchar(10),
   formula              varchar(200),
   primary key (id)
);


INSERT INTO `sbgl`.`rentunit` (`id`, `unitname`) VALUES ('1', '¸ö');

INSERT INTO `sbgl`.`rentunit` (`id`, `unitname`) VALUES ('2', 'Ê±');

INSERT INTO `sbgl`.`rentunit` (`id`, `unitname`) VALUES ('3', 'Ìì');