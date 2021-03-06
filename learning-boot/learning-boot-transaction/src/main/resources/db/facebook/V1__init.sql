-- CREATE DATABASE IF NOT EXISTS facebook DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `book`
(
    `id`          INT
(
    11
) NOT NULL AUTO_INCREMENT,
    `description` VARCHAR
(
    255
) DEFAULT NULL,
    `title`       VARCHAR
(
    255
) DEFAULT NULL,
    PRIMARY KEY
(`id`
)
    )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8;


CREATE TABLE `user`
(
    `id`   INT
(
    11
) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR
(
    255
) DEFAULT NULL,
    PRIMARY KEY
(`id`
)
    )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8;


