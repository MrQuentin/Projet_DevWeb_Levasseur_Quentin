CREATE TABLE `version` (
  `version_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `illustration_name` varchar(30),
  PRIMARY KEY (`version_id`)
);


CREATE TABLE `sousversion` (
  `sousversion_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `version_id` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  `illustration_name` varchar(30),
  PRIMARY KEY (`sousversion_id`),
  INDEX `version_id_idx` (`version_id` ASC ),
  CONSTRAINT `version_id_fk`
    FOREIGN KEY (`version_id`)
    REFERENCES `version` (`version_id`)
);

CREATE TABLE `categorie` (
  `categorie_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `illustration_name` varchar(30),
  PRIMARY KEY (`categorie_id`)
);

CREATE TABLE `type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `categorie_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `illustration_name` VARCHAR(45),
  PRIMARY KEY (`type_id`),
  INDEX `categorie_id_idx` (`categorie_id` ASC),
  CONSTRAINT `categorie_id`
    FOREIGN KEY (`categorie_id`)
    REFERENCES `categorie` (`categorie_id`)
);

CREATE TABLE `item` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `type_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NOT NULL,
  `illustration_name` VARCHAR(45),
  PRIMARY KEY (`item_id`),
  INDEX `type_id_idx` (`type_id` ASC),
  CONSTRAINT `type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `type` (`type_id`)
);