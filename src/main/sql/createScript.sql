-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

-- ******************** Create DB ********************;

CREATE DATABASE resume DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

-- ******************* Drop tables *******************;

DROP TABLE `experiences`;

DROP TABLE `educations`;

DROP TABLE `skills`;

DROP TABLE `achievements`;

DROP TABLE `persons`;

DROP TABLE `contact_informations`;

DROP TABLE `categories`;

DROP TABLE `users`;

-- ****************** Create tables ******************;

-- ************************************** `contact_informations`

CREATE TABLE `contact_informations`
(
 `uuid`        VARCHAR(36) NOT NULL ,
 `email`       VARCHAR(100) NOT NULL ,
 `phone`       VARCHAR(30) NOT NULL ,
 `street`      VARCHAR(45) NOT NULL ,
 `city`        VARCHAR(45) NOT NULL ,
 `postal_code` VARCHAR(45) NOT NULL ,
 `country`     VARCHAR(45) NOT NULL ,

 PRIMARY KEY (`uuid`)
);

-- ************************************** `categories`

CREATE TABLE `categories`
(
 `uuid` VARCHAR(36) NOT NULL ,
 `name` VARCHAR(45) NOT NULL ,

 PRIMARY KEY (`uuid`)
);

-- ************************************** `users`

CREATE TABLE `users`
(
 `uuid`     VARCHAR(36) NOT NULL ,
 `username` VARCHAR(100) NOT NULL ,
 `password` VARCHAR(255) NOT NULL ,
 `role`     TINYINT NOT NULL ,

 PRIMARY KEY (`uuid`)
);

-- ************************************** `persons`

CREATE TABLE `persons`
(
 `uuid`                     VARCHAR(36) NOT NULL ,
 `birth_date`               DATE NOT NULL ,
 `first_name`               VARCHAR(45) NOT NULL ,
 `last_name`                VARCHAR(45) NOT NULL ,
 `overview`                 VARCHAR(255) NOT NULL ,
 `created_by_user_uuid`     VARCHAR(36) NOT NULL ,
 `contact_information_uuid` VARCHAR(36) NOT NULL ,

 PRIMARY KEY (`uuid`),
 KEY `fkIdx_18` (`created_by_user_uuid`),
 CONSTRAINT `FK_18` FOREIGN KEY `fkIdx_18` (`created_by_user_uuid`) REFERENCES `users` (`uuid`),
 KEY `fkIdx_67` (`contact_information_uuid`),
 CONSTRAINT `FK_67` FOREIGN KEY `fkIdx_67` (`contact_information_uuid`) REFERENCES `contact_informations` (`uuid`)
);

-- ************************************** `experiences`

CREATE TABLE `experiences`
(
 `uuid`        VARCHAR(36) NOT NULL ,
 `start_date`  DATE NOT NULL ,
 `end_date`    DATE NOT NULL ,
 `title`       VARCHAR(30) NOT NULL ,
 `description` VARCHAR(255) NOT NULL ,
 `city`        VARCHAR(45) NOT NULL ,
 `country`     VARCHAR(45) NOT NULL ,
 `person_uuid` VARCHAR(36) NOT NULL ,

 PRIMARY KEY (`uuid`),
 KEY `fkIdx_94` (`person_uuid`),
 CONSTRAINT `FK_94` FOREIGN KEY `fkIdx_94` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ************************************** `educations`

CREATE TABLE `educations`
(
 `uuid`        VARCHAR(36) NOT NULL ,
 `date`        DATE NOT NULL ,
 `title`       VARCHAR(45) NOT NULL ,
 `institution` VARCHAR(45) NOT NULL ,
 `city`        VARCHAR(45) NOT NULL ,
 `country`     VARCHAR(45) NOT NULL ,
 `person_uuid` VARCHAR(36) NOT NULL ,

 PRIMARY KEY (`uuid`),
 KEY `fkIdx_80` (`person_uuid`),
 CONSTRAINT `FK_80` FOREIGN KEY `fkIdx_80` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ************************************** `skills`

CREATE TABLE `skills`
(
 `uuid`          VARCHAR(36) NOT NULL ,
 `level`         TINYINT NOT NULL ,
 `person_uuid`   VARCHAR(36) NOT NULL ,
 `category_uuid` VARCHAR(36) NOT NULL ,

 PRIMARY KEY (`uuid`),
 KEY `fkIdx_42` (`person_uuid`),
 CONSTRAINT `FK_42` FOREIGN KEY `fkIdx_42` (`person_uuid`) REFERENCES `persons` (`uuid`),
 KEY `fkIdx_53` (`category_uuid`),
 CONSTRAINT `FK_53` FOREIGN KEY `fkIdx_53` (`category_uuid`) REFERENCES `categories` (`uuid`)
);

-- ************************************** `achievements`

CREATE TABLE `achievements`
(
 `uuid`        VARCHAR(36) NOT NULL ,
 `date`        DATE NOT NULL ,
 `description` VARCHAR(255) NOT NULL ,
 `person_uuid` VARCHAR(36) NOT NULL ,

 PRIMARY KEY (`uuid`),
 KEY `fkIdx_28` (`person_uuid`),
 CONSTRAINT `FK_28` FOREIGN KEY `fkIdx_28` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ***************** Create DB user ******************;

-- ************************************** `create user`

CREATE USER 'db_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'db_user'@'%' IDENTIFIED BY 'password';

-- ************************************** `add read access`

GRANT SELECT ON resume.* TO 'db_user'@'localhost';
GRANT SELECT ON resume.* TO 'db_user'@'%';

-- ************************************** `add create access`

GRANT INSERT ON resume.* TO 'db_user'@'localhost';
GRANT INSERT ON resume.* TO 'db_user'@'%';

-- ************************************** `add update access`

GRANT UPDATE ON resume.* TO 'db_user'@'localhost';
GRANT UPDATE ON resume.* TO 'db_user'@'%';

-- ************************************** `add delete access`

GRANT DELETE ON resume.* TO 'db_user'@'localhost';
GRANT DELETE ON resume.* TO 'db_user'@'%';
