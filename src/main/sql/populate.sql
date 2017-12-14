DROP TABLE item;
DROP TABLE type;
DROP TABLE categorie;
DROP TABLE sousversion;
DROP TABLE version;

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

-- Populate categorie
INSERT INTO categorie (name) VALUES ('blocks');
INSERT INTO categorie (name) VALUES ('weapons');
INSERT INTO categorie (name) VALUES ('armor');
INSERT INTO categorie (name) VALUES ('miscelanious');

-- Populate type
INSERT INTO type (categorie_id, name) VALUES (1, 'decoration');
INSERT INTO type (categorie_id, name) VALUES (1, 'utils');
INSERT INTO type (categorie_id, name) VALUES (2, 'axe');
INSERT INTO type (categorie_id, name) VALUES (2, 'sword');
INSERT INTO type (categorie_id, name) VALUES (3, 'chestplate');
INSERT INTO type (categorie_id, name) VALUES (3, 'boots');

-- Populate items
INSERT INTO item (type_id, name, description) VALUES (5, 'diamond chestplate', 'chestplate en diamand');
INSERT INTO item (type_id, name, description) VALUES (5, 'gold chestplate', 'chestplate en gold');
INSERT INTO item (type_id, name, description) VALUES (1, 'dirt', 'block de dirt');
INSERT INTO item (type_id, name, description) VALUES (2, 'enchant table', 'table d enchantement');
INSERT INTO item (type_id, name, description) VALUES (3, 'diamond axe', 'ache en diamand');
INSERT INTO item (type_id, name, description) VALUES (4, 'diamond sword', 'épée en diamand');
INSERT INTO item (type_id, name, description) VALUES (4, 'diamond boots', 'botes en diamand');

-- Populate version
INSERT INTO version (name) VALUES ('Version 1.12');
INSERT INTO version (name) VALUES ('Version 1.11');
INSERT INTO version (name) VALUES ('Version 1.10');
INSERT INTO version (name) VALUES ('Version 1.9');
INSERT INTO version (name) VALUES ('Version 1.8');
INSERT INTO version (name) VALUES ('Version 1.7');

-- Populate sousversion
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.12.2', 1, 'version 1.12.2');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.12.1', 1, 'version 1.12.1');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.12.0', 1, 'version 1.12.0');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.11.2', 2, 'version 1.11.2');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.11.1', 2, 'version 1.11.1');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.11.0', 2, 'version 1.11.0');
INSERT INTO sousversion (name, version_id, description) VALUES ('Version 1.10.2', 3, 'version 1.10.2');

