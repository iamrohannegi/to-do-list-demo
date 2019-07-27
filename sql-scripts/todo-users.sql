DROP DATABASE  IF EXISTS `todo-login-info`;

CREATE DATABASE  IF NOT EXISTS `todo-login-info`;
USE `todo-login-info`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Default passwords here are: lakers123


INSERT INTO `users` 
VALUES 
('lebron','{bcrypt}$2a$10$ZcFCp3O2B3uA/EXZV0gxjOgxdcwFykCCDd.pLYfySTl5lv2DXbFJG',1),
('anthony','{bcrypt}$2a$10$ZcFCp3O2B3uA/EXZV0gxjOgxdcwFykCCDd.pLYfySTl5lv2DXbFJG',1),
('boogie','{bcrypt}$2a$10$ZcFCp3O2B3uA/EXZV0gxjOgxdcwFykCCDd.pLYfySTl5lv2DXbFJG',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('lebron','ROLE_USER'),
('anthony','ROLE_USER'),
('boogie','ROLE_USER')


