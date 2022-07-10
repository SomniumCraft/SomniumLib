ALTER TABLE `inventories`
    ADD `location_world` varchar(100) NOT NULL,
    ADD `location_x` float NOT NULL,
    ADD `location_y` float NOT NULL,
    ADD `location_z` float NOT NULL,
    ADD `location_yaw` float NOT NULL,
    ADD `location_pitch` float NOT NULL,
    CHANGE COLUMN `hunger` `food_level` float;