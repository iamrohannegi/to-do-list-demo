USE `todo-login-info`;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `entry_text` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entry` for demo
--

INSERT INTO `entry` 
VALUES 
(1,'lebron','Strive for greatness'),
(2,'anthony','Win a ring for lakers');


