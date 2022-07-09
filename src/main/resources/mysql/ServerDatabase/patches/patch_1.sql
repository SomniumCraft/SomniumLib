ALTER TABLE `deaths`
    ADD `death_location_world` varchar(100) NOT NULL,
    ADD `death_location_x` float NOT NULL,
    ADD `death_location_y` float NOT NULL,
    ADD `death_location_z` float NOT NULL,
    ADD `death_location_yaw` float NOT NULL,
    ADD `death_location_pitch` float NOT NULL,
    CHANGE COLUMN `hunger` `food_level` float;