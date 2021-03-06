-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

-- ******************** Create DB ********************;

CREATE DATABASE resume
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

-- ******************* Drop tables *******************;

DROP TABLE `experiences`;

DROP TABLE `educations`;

DROP TABLE `skills`;

DROP TABLE `achievements`;

DROP TABLE `persons`;

DROP TABLE `contact_informations`;

DROP TABLE `users`;

-- ****************** Create tables ******************;

-- ************************************** `contact_informations`

CREATE TABLE `contact_informations`
(
  `uuid`        VARCHAR(36)  NOT NULL UNIQUE,
  `email`       VARCHAR(100) NOT NULL,
  `phone`       VARCHAR(30)  NOT NULL,
  `street`      VARCHAR(45)  NOT NULL,
  `city`        VARCHAR(45)  NOT NULL,
  `postal_code` VARCHAR(45)  NOT NULL,
  `country`     VARCHAR(45)  NOT NULL,

  PRIMARY KEY (`uuid`)
);

-- ************************************** `users`

CREATE TABLE `users`
(
  `uuid`         VARCHAR(36)   NOT NULL UNIQUE,
  `username`     VARCHAR(100)  NOT NULL UNIQUE,
  `password`     VARCHAR(255)  NOT NULL,
  `role`         TINYINT       NOT NULL,
  `access_token` VARCHAR(1000) NULL,

  PRIMARY KEY (`uuid`)
);

-- ************************************** `persons`

CREATE TABLE `persons`
(
  `uuid`                     VARCHAR(36)  NOT NULL UNIQUE,
  `birth_date`               DATE         NOT NULL,
  `first_name`               VARCHAR(45)  NOT NULL,
  `last_name`                VARCHAR(45)  NOT NULL,
  `overview`                 VARCHAR(255) NOT NULL,
  `created_by_user_uuid`     VARCHAR(36)  NOT NULL,
  `base_url`                 VARCHAR(255) NOT NULL UNIQUE,
  `linkedin_url`             VARCHAR(255) NULL UNIQUE,
  `github_url`               VARCHAR(255) NULL UNIQUE,
  `facebook_url`             VARCHAR(255) NULL UNIQUE,
  `twitter_url`              VARCHAR(255) NULL UNIQUE,
  `contact_information_uuid` VARCHAR(36)  NOT NULL,

  PRIMARY KEY (`uuid`),
  KEY `fkIdx_18` (`created_by_user_uuid`),
  CONSTRAINT `FK_18` FOREIGN KEY `fkIdx_18` (`created_by_user_uuid`) REFERENCES `users` (`uuid`),
  KEY `fkIdx_67` (`contact_information_uuid`),
  CONSTRAINT `FK_67` FOREIGN KEY `fkIdx_67` (`contact_information_uuid`) REFERENCES `contact_informations` (`uuid`)
);

-- ************************************** `experiences`

CREATE TABLE `experiences`
(
  `uuid`        VARCHAR(36)  NOT NULL UNIQUE,
  `start_date`  DATE         NOT NULL,
  `end_date`    DATE         NULL,
  `title`       VARCHAR(30)  NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `city`        VARCHAR(45)  NOT NULL,
  `country`     VARCHAR(45)  NOT NULL,
  `person_uuid` VARCHAR(36)  NOT NULL,

  PRIMARY KEY (`uuid`),
  KEY `fkIdx_94` (`person_uuid`),
  CONSTRAINT `FK_94` FOREIGN KEY `fkIdx_94` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ************************************** `educations`

CREATE TABLE `educations`
(
  `uuid`        VARCHAR(36) NOT NULL UNIQUE,
  `date`        DATE        NOT NULL,
  `title`       VARCHAR(45) NOT NULL,
  `institution` VARCHAR(45) NOT NULL,
  `city`        VARCHAR(45) NOT NULL,
  `country`     VARCHAR(45) NOT NULL,
  `person_uuid` VARCHAR(36) NOT NULL,

  PRIMARY KEY (`uuid`),
  KEY `fkIdx_80` (`person_uuid`),
  CONSTRAINT `FK_80` FOREIGN KEY `fkIdx_80` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ************************************** `skills`

CREATE TABLE `skills`
(
  `uuid`          VARCHAR(36) NOT NULL UNIQUE,
  `mastery`       VARCHAR(45) NOT NULL,
  `level`         TINYINT     NOT NULL,
  `person_uuid`   VARCHAR(36) NOT NULL,

  PRIMARY KEY (`uuid`),
  KEY `fkIdx_42` (`person_uuid`),
  CONSTRAINT `FK_42` FOREIGN KEY `fkIdx_42` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ************************************** `achievements`

CREATE TABLE `achievements`
(
  `uuid`        VARCHAR(36)  NOT NULL UNIQUE,
  `date`        DATE         NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `person_uuid` VARCHAR(36)  NOT NULL,

  PRIMARY KEY (`uuid`),
  KEY `fkIdx_28` (`person_uuid`),
  CONSTRAINT `FK_28` FOREIGN KEY `fkIdx_28` (`person_uuid`) REFERENCES `persons` (`uuid`)
);

-- ***************** Create DB user ******************;

-- ************************************** `create user`

CREATE USER 'db_user'@'localhost'
  IDENTIFIED BY 'password';
CREATE USER 'db_user'@'%'
  IDENTIFIED BY 'password';

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
