--
-- Create Database
--
DROP DATABASE IF EXISTS LiveDB;
CREATE DATABASE LiveDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE LiveDB;

--
-- Table structure for table `user_info`
--
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE user_info(
  username varchar(255) NOT NULL,
  password varchar(100) NOT NULL,
  tel varchar(50),
  name varchar(50),
  address varchar(255),
  idCardNum varchar(50),
  pushAddress varchar(100),
  autoStopPushMinutes int(100),
  userStatus int(1) default 1,
  isAdmin int(1) default 0,
  PRIMARY KEY (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `device`
--
DROP TABLE IF EXISTS `device`;
CREATE TABLE device(
  ip VARCHAR (50) NOT NULL,
  mac VARCHAR (100),
  imei VARCHAR (100),
  gps VARCHAR (100),
  serviceName VARCHAR (100) UNIQUE ,
  username VARCHAR(255) NOT NULL,
  status INT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(`ip`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
