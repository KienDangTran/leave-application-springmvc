
use leave_application_db;
/*-------------------------------------------------------------------- CREATE TABLE --------------------------------------------------------------*/

create table if not exists `MT_ROLE` (
    `ROLE_CODE` varchar(64) not null,
    `ROLE_DESC` varchar(1000),
    `STATUS` varchar(1) default 'A',
    
    primary key (`ROLE_CODE`)
    
)  engine = InnoDB;

create table if not exists `MT_PERMISSION` (
	`PERMISSION_CODE` varchar(64),
    `PERMISSION_DESC` varchar(1000),
    `STATUS` varchar(1) default 'A',
    
    primary key (`PERMISSION_CODE`)
    
) engine = InnoDB;

create table if not exists `MT_ROLE_PERMISSION_GRANTED` (
    `ROLE_CODE` varchar(64) not null,
    `PERMISSION_CODE` varchar(64)  not null,
    `STATUS` varchar(1) default 'A',
    
    primary key (`ROLE_CODE`, `PERMISSION_CODE`),
    
    constraint `FK_ROLE_PERMISSION_GRANTED_ROLE` foreign key (`ROLE_CODE`)
        references `leave_application_db`.`MT_ROLE` (`ROLE_CODE`)
        on delete restrict on update restrict,
	
    constraint `FK_ROLE_PERMISSION_GRANTED_PERMISSION` foreign key (`PERMISSION_CODE`)
        references `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`)
        on delete restrict on update restrict
        
) engine = InnoDB;

create table if not exists `MT_EMPLOYEE` (
    `EMPLOYEE_CODE` varchar(64) not null,
    `EMPLOYEE_NAME` varchar(64) not null,
    `DATE_OF_BIRTH` date,
    `SEX` varchar(1),
    `EMAIL` varchar(64),
    `PHONE_NO` varchar(64),
    `STATUS` varchar(1) default 'A',
    
    primary key (`EMPLOYEE_CODE`)
    
)  engine = InnoDB;

create table if not exists `MT_USER` (
	`USER_ID` varchar(64) not null,
	`USERNAME` varchar(64) not null,
	`PASSWORD` varchar(255) not null,
    `EMPLOYEE_CODE` varchar(64) not null,
    `THEME` varchar(32),
    `ACCOUNT_NON_EXPIRED` bool default true,
    `ACCOUNT_NON_LOCKED` bool default true,
    `CREDENTIALS_NON_EXPIRED` bool default true,
    `ENABLED` bool default true,
	
	primary key (`USER_ID`),
    
    constraint `FK_ROLE_EMPLOYEE` foreign key (`EMPLOYEE_CODE`)
        references `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`)
        on delete cascade on update restrict
        
) engine = InnoDB;

create table if not exists `MT_USER_ROLE` (
	`USER_ID` varchar(64) not null,
	`ROLE_CODE` varchar(64) not null,
    `STATUS` varchar(3) default 'A',
    
    primary key (`USER_ID`, `ROLE_CODE`),
    
    constraint `FK_USER_ROLE_USER` foreign key (`USER_ID`)
        references `leave_application_db`.`MT_USER` (`USER_ID`)
        on delete cascade on update restrict,
        
	constraint `FK_USER_ROLE_ROLE` foreign key (`ROLE_CODE`)
        references `leave_application_db`.`MT_ROLE` (`ROLE_CODE`)
        on delete cascade on update restrict
        
) engine = InnoDB;

create table if not exists `persistent_logins` (
	`USERNAME` varchar(64) not null,
	`SERIES` varchar(64) not null,
    `TOKEN` varchar(64) not null,
    `LAST_USED` timestamp not null,
    
    primary key (`SERIES`)
) engine = InnoDB;

create table if not exists `MT_ID_SCHEME` (
	`SEQ_NO` int auto_increment not null,
    `SCHEME_NAME` varchar(64) unique,
    `PREFIX` varchar(8),
    `SUFFIX`  varchar(8),
    `LENGTH` int default 10,
    `FILLED_CHAR` varchar(1) default '0',
    `LAST_GEN_NO` bigint default 0,
    `STATUS` varchar(1) default 'A',
    
    primary key(`SEQ_NO`)
) engine = InnoDB;
