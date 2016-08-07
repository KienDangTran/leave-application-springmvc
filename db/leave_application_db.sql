CREATE DATABASE leave_application_db;
USE leave_application_db;
/*-------------------------------------------------------------------- CREATE TABLE --------------------------------------------------------------*/

CREATE TABLE IF NOT EXISTS `MT_ROLE` (
  `role_code` VARCHAR(64) NOT NULL,
  `role_desc` VARCHAR(1000),
  `status`    VARCHAR(1) DEFAULT 'A',

  PRIMARY KEY (`role_code`)

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_PERMISSION` (
  `permission_code` VARCHAR(64),
  `permission_desc` VARCHAR(1000),
  `status`          VARCHAR(1) DEFAULT 'A',

  PRIMARY KEY (`permission_code`)

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_ROLE_PERMISSION_GRANTED` (
  `role_code`       VARCHAR(64) NOT NULL,
  `permission_code` VARCHAR(64) NOT NULL,
  `status`          VARCHAR(1) DEFAULT 'A',

  PRIMARY KEY (`role_code`, `permission_code`),

  CONSTRAINT `FK_ROLE_PERMISSION_GRANTED_ROLE` FOREIGN KEY (`role_code`)
  REFERENCES `leave_application_db`.`MT_ROLE` (`role_code`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,

  CONSTRAINT `FK_ROLE_PERMISSION_GRANTED_PERMISSION` FOREIGN KEY (`permission_code`)
  REFERENCES `leave_application_db`.`MT_PERMISSION` (`permission_code`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_EMPLOYEE` (
  `employee_code` VARCHAR(64) NOT NULL,
  `employee_name` VARCHAR(64) NOT NULL,
  `date_of_birth` DATE,
  `sex`           VARCHAR(1),
  `email`         VARCHAR(64),
  `phone_no`      VARCHAR(64),
  `status`        VARCHAR(1) DEFAULT 'A',

  PRIMARY KEY (`employee_code`)

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_USER` (
  `user_id`                 VARCHAR(64)  NOT NULL,
  `username`                VARCHAR(64)  NOT NULL,
  `password`                VARCHAR(255) NOT NULL,
  `employee_code`           VARCHAR(64)  NOT NULL,
  `theme`                   VARCHAR(32),
  `account_non_expired`     BOOL DEFAULT TRUE,
  `account_non_locked`      BOOL DEFAULT TRUE,
  `credentials_non_expired` BOOL DEFAULT TRUE,
  `enabled`                 BOOL DEFAULT TRUE,

  PRIMARY KEY (`user_id`),

  CONSTRAINT `FK_ROLE_EMPLOYEE` FOREIGN KEY (`employee_code`)
  REFERENCES `leave_application_db`.`MT_EMPLOYEE` (`employee_code`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_USER_ROLE` (
  `user_id`   VARCHAR(64) NOT NULL,
  `role_code` VARCHAR(64) NOT NULL,
  `status`    VARCHAR(3) DEFAULT 'A',

  PRIMARY KEY (`user_id`, `role_code`),

  CONSTRAINT `FK_USER_ROLE_USER` FOREIGN KEY (`user_id`)
  REFERENCES `leave_application_db`.`MT_USER` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,

  CONSTRAINT `FK_USER_ROLE_ROLE` FOREIGN KEY (`role_code`)
  REFERENCES `leave_application_db`.`MT_ROLE` (`role_code`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT

)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username`  VARCHAR(64) NOT NULL,
  `series`    VARCHAR(64) NOT NULL,
  `token`     VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP   NOT NULL,

  PRIMARY KEY (`series`)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `MT_ID_SCHEME` (
  `seq_no`      INT AUTO_INCREMENT NOT NULL,
  `scheme_name` VARCHAR(64) UNIQUE,
  `prefix`      VARCHAR(8),
  `suffix`      VARCHAR(8),
  `length`      INT        DEFAULT 10,
  `filled_char` VARCHAR(1) DEFAULT '0',
  `last_gen_no` BIGINT     DEFAULT 0,
  `status`      VARCHAR(1) DEFAULT 'A',

  PRIMARY KEY (`seq_no`)
)
  ENGINE = InnoDB;
