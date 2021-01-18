use contrast_db;
-- never do this in production
SET FOREIGN_KEY_CHECKS = 0; 

CREATE TABLE IF NOT EXISTS `application_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL,
  `platform_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`),
  FOREIGN KEY (`platform_id`) REFERENCES `platform` (`id`)
);

CREATE TABLE IF NOT EXISTS `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

truncate organization;
truncate platform;
truncate application_table;

insert into organization VALUES(1,'Google');
insert into organization VALUES(2,'Facebook');
insert into organization VALUES(3,'Microsoft');
insert into organization VALUES(4,'nodejs');


insert into platform VALUES(1,'Java');
insert into platform VALUES(2,'Go');
insert into platform VALUES(3,'Python');
insert into platform VALUES(4,'Javascript');
insert into platform VALUES(5,'.NET');
insert into platform VALUES(6,'Typescript');

insert into application_table VALUES(1,'Kubernetes',1,2);
insert into application_table VALUES(2,'React',2,6);
insert into application_table VALUES(3,'vscode',3,5);
insert into application_table VALUES(4,'CoreFX',3,5);
insert into application_table VALUES(5,'Roslyn',3,5);
insert into application_table VALUES(6,'Node',4,4);
insert into application_table VALUES(7,'Tensorflow',1,2);
insert into application_table VALUES(8,'Kubectl',1,2);



SET FOREIGN_KEY_CHECKS = 1;
