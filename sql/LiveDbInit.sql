--
-- Create Database
--
DROP DATABASE IF EXISTS LiveDB;
CREATE DATABASE LiveDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE LiveDB;

--
--Table structure for table `badge_target`
--
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE user_info(
  user_id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  school_num varchar(20) NOT NULL UNIQUE,
  name varchar(50) NOT NULL,
  sex varchar(10),
  grade varchar(100),
  school varchar(100),
  major varchar(100),
  class varchar(100),
  qq varchar(20),
  phone varchar(20),
  email varchar(60) NOT NULL UNIQUE,
  password varchar(120) NOT NULL DEFAULT '123456',
  adress varchar(255),
  role int(2) NOT NULL DEFAULT '2',
  PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;