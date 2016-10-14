SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema traffic
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `traffic` ;

-- -----------------------------------------------------
-- Schema traffic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `traffic` DEFAULT CHARACTER SET utf8 ;
USE `traffic` ;

-- -----------------------------------------------------
-- Table `traffic`.`Driver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traffic`.`Driver` ;

CREATE TABLE IF NOT EXISTS `traffic`.`Driver` (
  `idDriver` INT NOT NULL,
  `age` INT(2) NOT NULL,
  `sex` TINYINT(1) NOT NULL,
  `experience` INT(2) NOT NULL,
  `previous_infractions` INT(2) NOT NULL,
  `illness` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idDriver`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traffic`.`DateTime`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traffic`.`DateTime` ;

CREATE TABLE IF NOT EXISTS `traffic`.`DateTime` (
  `idDatetime` INT NOT NULL,
  `day` INT(2) NOT NULL,
  `month` INT(2) NOT NULL,
  `year` INT(4) NOT NULL,
  `hour` INT(2) NOT NULL,
  `season` VARCHAR(6) NOT NULL,
  `weather` VARCHAR(10) NOT NULL,
  `weekend` TINYINT(1) NOT NULL,
  `holiday` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idDatetime`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traffic`.`KmPoint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traffic`.`KmPoint` ;

CREATE TABLE IF NOT EXISTS `traffic`.`KmPoint` (
  `idKmPoint` INT NOT NULL,
  `start` INT(4) NOT NULL,
  `end` INT(4) NOT NULL,
  `road_name` VARCHAR(10) NOT NULL,
  `road_type` VARCHAR(10) NOT NULL,
  `black_point` TINYINT(1) NOT NULL,
  `signposting` TINYINT(1) NOT NULL,
  `radar` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idKmPoint`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traffic`.`Vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traffic`.`Vehicle` ;

CREATE TABLE IF NOT EXISTS `traffic`.`Vehicle` (
  `idVehicle` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  `car_spaces` INT(1) NOT NULL,
  `passengers` INT(1) NOT NULL,
  `antiquity` INT(2) NOT NULL,
  `drive_permission` TINYINT(1) NOT NULL,
  `electric` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idVehicle`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traffic`.`Infraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traffic`.`Infraction` ;

CREATE TABLE IF NOT EXISTS `traffic`.`Infraction` (
  `idInfraction` INT NOT NULL,
  `idDriver` INT NOT NULL,
  `idDatetime` INT NOT NULL,
  `idKmPoint` INT NOT NULL,
  `idVehicle` INT NOT NULL,
  `type` ENUM('low', 'medium', 'high') NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `penalty` INT(3) NOT NULL,
  PRIMARY KEY (`idInfraction`),
  INDEX `fk_Infraction_Driver_idx` (`idDriver` ASC),
  INDEX `fk_Infraction_DateTime1_idx` (`idDatetime` ASC),
  INDEX `fk_Infraction_KmPoint1_idx` (`idKmPoint` ASC),
  INDEX `fk_Infraction_Vehicle1_idx` (`idVehicle` ASC),
  CONSTRAINT `fk_Infraction_Driver`
    FOREIGN KEY (`idDriver`)
    REFERENCES `traffic`.`Driver` (`idDriver`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Infraction_DateTime1`
    FOREIGN KEY (`idDatetime`)
    REFERENCES `traffic`.`DateTime` (`idDatetime`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Infraction_KmPoint1`
    FOREIGN KEY (`idKmPoint`)
    REFERENCES `traffic`.`KmPoint` (`idKmPoint`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Infraction_Vehicle1`
    FOREIGN KEY (`idVehicle`)
    REFERENCES `traffic`.`Vehicle` (`idVehicle`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '			';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
