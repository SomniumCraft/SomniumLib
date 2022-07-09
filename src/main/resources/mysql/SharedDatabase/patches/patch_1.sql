CREATE TABLE IF NOT EXISTS `ips`(
`id` INT NOT NULL AUTO_INCREMENT,
`ip` varchar(15) NOT NULL,
`player_uuid` varchar(36) NOT NULL,
`used_date` datetime NOT NULL,
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
PRIMARY KEY (`id`));

ALTER TABLE `ips` ADD INDEX `IDX_ip_player_uuid` (ip, player_uuid);

CREATE TABLE IF NOT EXISTS `warn_types` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` varchar(36) NOT NULL,
`description` TEXT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `warns`(
`id` INT NOT NULL AUTO_INCREMENT,
`reason` TEXT NOT NULL,
`issued_by` varchar(36) NOT NULL,
`duration` bigint NOT NULL,
`issued_date` datetime NOT NULL,
`player_uuid` varchar(36) NOT NULL,
`warn_type_id` INT NOT NULL,
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
FOREIGN KEY (`warn_type_id`) REFERENCES `warn_types`(`id`) ON DELETE CASCADE,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `recommended_punishment` (
`warn_threshold` INT NOT NULL,
`warn_type_id` INT NOT NULL,
`message` varchar(255) NOT NULL,
`commands` TEXT NOT NULL,
FOREIGN KEY (`warn_type_id`) REFERENCES `warn_types`(`id`) ON DELETE CASCADE,
PRIMARY KEY (`warn_threshold`, `warn_type_id`));

CREATE TABLE IF NOT EXISTS `screenshots` (
`id` INT NOT NULL AUTO_INCREMENT,
`path` varchar(255) NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `warn_screenshots` (
`warn_id` INT NOT NULL,
`screenshot_id` INT NOT NULL,
FOREIGN KEY (`warn_id`) REFERENCES `warns`(`id`) ON DELETE CASCADE,
FOREIGN KEY (`screenshot_id`) REFERENCES `screenshots`(`id`) ON DELETE CASCADE,
PRIMARY KEY (`warn_id`, `screenshot_id`));

CREATE TABLE IF NOT EXISTS `punishments` (
`id` INT NOT NULL AUTO_INCREMENT,
`punishment_type` varchar(255) NOT NULL,
`reason` varchar(255) NOT NULL,
`issued_by` varchar(36) NOT NULL,
`duration` bigint,
`remaining_time` bigint,
`issued_date` datetime NOT NULL,
`player_uuid` varchar(36) NOT NULL,
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `punishment_screenshots` (
`punishment_id` INT NOT NULL,
`screenshot_id` INT NOT NULL,
FOREIGN KEY (`punishment_id`) REFERENCES `punishments`(`id`) ON DELETE CASCADE,
FOREIGN KEY (`screenshot_id`) REFERENCES `screenshots`(`id`) ON DELETE CASCADE,
PRIMARY KEY (`punishment_id`, `screenshot_id`));

CREATE TABLE IF NOT EXISTS `private_messages` (
`id` INT NOT NULL AUTO_INCREMENT,
`sender_uuid` varchar(36) NOT NULL,
`receiver_uuid` varchar(36) NOT NULL,
`message` varchar(255) NOT NULL,
`sent_date` datetime NOT NULL,
`read_date` datetime,
FOREIGN KEY (`sender_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
FOREIGN KEY (`receiver_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
PRIMARY KEY (`id`));

INSERT INTO `patches` (`id`, `patch`) VALUES (NULL, 1);