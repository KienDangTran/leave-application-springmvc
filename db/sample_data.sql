-- SAMPLE DATA

-- EMPLOYEE
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('SYS_ADMIN', 'System Administrator', null, 'kiendang91@gmail.com', null, 'S');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('ADMIN', 'Administrator', '1991-09-12', 'kiendang91@gmail.com', '0936358833', 'A');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('OFFICER', 'Approving Officer', null, 'kiendang91@gmail.com', null, 'A');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('EMPLOYEE', 'Employee', null, 'kiendang91@gmail.com', null, 'A');

-- User
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `ACCOUNT_NON_EXPIRED`, `ACCOUNT_NON_LOCKED`, `CREDENTIALS_NON_EXPIRED`, `ENABLED`) VALUES ('U000000000', 'root', '$2a$10$PaMU6xG2CxfGj1jX60Ag.eqqNBvG7oKngtV6AWZYWSyuMmZcVSqTu', 'SYS_ADMIN', false, false, false, false);
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `ACCOUNT_NON_EXPIRED`, `ACCOUNT_NON_LOCKED`, `CREDENTIALS_NON_EXPIRED`,  `ENABLED`) VALUES ('U000000001', 'admin', '$2a$10$PaMU6xG2CxfGj1jX60Ag.eqqNBvG7oKngtV6AWZYWSyuMmZcVSqTu', 'ADMIN', true, true, true, true);
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `ACCOUNT_NON_EXPIRED`, `ACCOUNT_NON_LOCKED`, `CREDENTIALS_NON_EXPIRED`,  `ENABLED`) VALUES ('U000000002', 'officer', '$2a$10$PaMU6xG2CxfGj1jX60Ag.eqqNBvG7oKngtV6AWZYWSyuMmZcVSqTu', 'OFFICER', true, true, true, true);
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `ACCOUNT_NON_EXPIRED`, `ACCOUNT_NON_LOCKED`, `CREDENTIALS_NON_EXPIRED`,  `ENABLED`) VALUES ('U000000003', 'employee', '$2a$10$PaMU6xG2CxfGj1jX60Ag.eqqNBvG7oKngtV6AWZYWSyuMmZcVSqTu', 'EMPLOYEE', true, true, true, true);

-- Role
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('SYS_ADMIN', 'System Administrator', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('ADMIN', 'Administrator', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('OFFICER', 'Approving Ofifcer', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('EMPLOYEE', 'Employee', 'A');

-- User-Role
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000000', 'SYS_ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000001', 'ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000002', 'OFFICER', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000003', 'EMPLOYEE', 'A');

-- MT_PERMISSION
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`, `STATUS`) VALUES ('FULL_CONTROL', 'Acess & Execute all screen', 'A');
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`, `STATUS`) VALUES ('FULL_ACCESS', 'Acess & View all screen', 'A');
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`, `STATUS`) VALUES ('EXE_EMP', 'Execute Employee', 'A');
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`, `STATUS`) VALUES ('VIEW_EMP', 'View All Employee', 'A');
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`, `STATUS`) VALUES ('EXE_USER', 'Excute User', 'A');
INSERT INTO `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`, `PERMISSION_DESC`) VALUES ('VIEW_USER', 'View All User');

-- MT_ROLE_PERMISSION_GRANTED
INSERT INTO `leave_application_db`.`MT_ROLE_PERMISSION_GRANTED` (`ROLE_CODE`, `PERMISSION_CODE`, `STATUS`) VALUES ('SYS_ADMIN', 'FULL_CONTROL', 'A');
INSERT INTO `leave_application_db`.`MT_ROLE_PERMISSION_GRANTED` (`ROLE_CODE`, `PERMISSION_CODE`, `STATUS`) VALUES ('ADMIN', 'EXE_EMP', 'A');
INSERT INTO `leave_application_db`.`MT_ROLE_PERMISSION_GRANTED` (`ROLE_CODE`, `PERMISSION_CODE`, `STATUS`) VALUES ('ADMIN', 'VIEW_EMP', 'A');

-- MT_ID_SCHEME
INSERT INTO `leave_application_db`.`MT_ID_SCHEME` (`SCHEME_NAME`, `PREFIX`) VALUES ('EMP', 'EMP');



