CREATE TABLE IF NOT EXISTS `players` (
`uuid` varchar(36) NOT NULL,
`display_name` varchar(100),
`death_location_world` varchar(100),
`death_location_x` float,
`death_location_y` float,
`death_location_z` float,
`death_location_yaw` float,
`death_location_pitch` float,
`death_timestamp` datetime,
`logout_location_world` varchar(100),
`logout_location_x` float,
`logout_location_y` float,
`logout_location_z` float,
`logout_location_yaw` float,
`logout_location_pitch` float,
`logout_timestamp` datetime,
`teleport_location_world` varchar(100),
`teleport_location_x` float,
`teleport_location_y` float,
`teleport_location_z` float,
`teleport_location_yaw` float,
`teleport_location_pitch` float,
`teleport_timestamp` datetime,
`login_timestamp` datetime,
`total_playtime` bigint,
`balance` decimal(13,2),
`fly_time` bigint,
`last_fly_time_added` datetime,
`last_keep_inventory` datetime,
`book_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`wooden_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`stone_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`iron_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`golden_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`diamond_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`netherite_enchanted_without_mending` INT NOT NULL DEFAULT 0,
`marine_enchanted_without_mending` INT NOT NULL DEFAULT 0,
PRIMARY KEY (`uuid`));

CREATE TABLE IF NOT EXISTS `kits` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(36) NOT NULL UNIQUE,
`description` TEXT,
`item_icon` TINYTEXT NOT NULL,
`cooldown` BIGINT,
`max_use_times` INT,
`experience_cost` INT NOT NULL,
`money_cost` INT NOT NULL,
`activation_commands` TEXT NOT NULL,
`is_active` tinyint(1) NOT NULL,
`is_default` tinyint(1) NOT NULL,
`items` MEDIUMTEXT NOT NULL,
`permission` VARCHAR(255),
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `player_kits` (
`player_uuid` VARCHAR(36) NOT NULL,
`kit_id` INT NOT NULL,
`use_times` INT NOT NULL,
`last_use_timestamp` BIGINT NOT NULL,
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
FOREIGN KEY (`kit_id`) REFERENCES `kits`(`id`) ON DELETE CASCADE,
PRIMARY KEY (`player_uuid`, `kit_id`)
);

CREATE TABLE IF NOT EXISTS `warps` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(36) NOT NULL UNIQUE,
`item_icon` TINYTEXT NOT NULL,
`creator_uuid` VARCHAR(36) NOT NULL,
`description` TEXT,
`location_world` VARCHAR(100) NOT NULL,
`location_x` FLOAT NOT NULL,
`location_y` FLOAT NOT NULL,
`location_z` FLOAT NOT NULL,
`location_yaw` FLOAT NOT NULL,
`location_pitch` FLOAT NOT NULL,
`permission` VARCHAR(255),
FOREIGN KEY (`creator_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `inventories` (
`id` INT NOT NULL AUTO_INCREMENT,
`player_uuid` VARCHAR(36) NOT NULL,
`killer_uuid` VARCHAR(36),
`timestamp` datetime NOT NULL,
`death_reason` VARCHAR(255),
`experience` INT NOT NULL,
`health` FLOAT NOT NULL,
`hunger` FLOAT NOT NULL,
`saturation` FLOAT NOT NULL,
`potion_effects` MEDIUMTEXT NOT NULL,
`inventory` MEDIUMTEXT NOT NULL,
`keep_inventory` tinyint(1),
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
FOREIGN KEY (`killer_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `homes` (
`id` INT NOT NULL AUTO_INCREMENT,
`player_uuid` VARCHAR(36) NOT NULL,
`name` VARCHAR(36) NOT NULL,
`item_icon` TINYTEXT NOT NULL,
`location_world` VARCHAR(100) NOT NULL,
`location_x` FLOAT NOT NULL,
`location_y` FLOAT NOT NULL,
`location_z` FLOAT NOT NULL,
`location_yaw` FLOAT NOT NULL,
`location_pitch` FLOAT NOT NULL,
FOREIGN KEY (`player_uuid`) REFERENCES `players`(`uuid`) ON DELETE CASCADE,
CONSTRAINT `homes_unique` UNIQUE (`player_uuid`, `name`),
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `patches` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`patch` INT NOT NULL
);

INSERT INTO `patches` (`id`, `patch`) VALUES (NULL, 0);