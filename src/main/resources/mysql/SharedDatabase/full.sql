CREATE TABLE IF NOT EXISTS `players` (
`uuid` varchar(36) NOT NULL,
`name` varchar(16) NOT NULL,
`skin_url` varchar(255) NOT NULL,
`join_message` varchar(40) DEFAULT NULL,
`leave_message` varchar(40) DEFAULT NULL,
PRIMARY KEY (`uuid`));

CREATE TABLE IF NOT EXISTS `patches` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`patch` INT NOT NULL
);

INSERT INTO `patches` (`id`, `patch`) VALUES (NULL, 0);