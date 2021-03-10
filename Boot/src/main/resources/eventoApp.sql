DROP DATABASE  IF EXISTS `eventoApp`;

CREATE DATABASE  IF NOT EXISTS `eventoApp`;
USE `eventoApp`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `guest` ------------------------------
--

DROP TABLE IF EXISTS `guest`;

CREATE TABLE `guest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_name` varchar(80) NOT NULL,
  `event_code` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EVENT_idx` (`event_code`),
  CONSTRAINT `FK_EVENT` FOREIGN KEY (`event_code`) REFERENCES `event` (`code`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `event` ----------------------------------------
--

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `city` char(50) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `role` ---------------------------------------------
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_MANAGER'),('ROLE_ADMIN');


--
-- Table structure for table `user` ---------------------------------------------------
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_idx` (`role_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: senha
--

INSERT INTO `user` (username, password, first_name, last_name, email)
VALUES 
('john','$2a$10$5FSFFaXRKdbDEvs1aS.z5.kb.xQqbcuK3r1IqzfghpXQebTn2TdQO','John','Doe','john@luv2code.com'),
('mary','$2a$10$5FSFFaXRKdbDEvs1aS.z5.kb.xQqbcuK3r1IqzfghpXQebTn2TdQO','Mary','Public','mary@gmail.com'),
('susana','$2a$10$5FSFFaXRKdbDEvs1aS.z5.kb.xQqbcuK3r1IqzfghpXQebTn2TdQO','Susana','Silva','susana@yahoo.com.br');



--
-- Table structure for table `users_roles` -------------------------------------------
--


DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles` -------------------------------------------------------------------
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 3)
