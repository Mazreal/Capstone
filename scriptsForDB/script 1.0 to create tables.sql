DROP DATABASE IF EXISTS fit;

CREATE DATABASE fit;
  
 CREATE TABLE fit.Users(
  `userNo` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `roleID` char,
  PRIMARY KEY (`userNo`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  CONSTRAINT CK_roleID CHECK (roleID IN ('A','W','K','B'))
);

CREATE TABLE fit.Expired_Log(
  `itemNo` int(5) NOT NULL AUTO_INCREMENT,
  `productNo` int(5) NOT NULL,
  `productName` varchar(40) NOT NULL,
  `quantity` decimal(6,2) NOT NULL,
  `dateExpired` date NOT NULL,
  PRIMARY KEY  (`itemNo`,`dateExpired`)
);

CREATE TABLE fit.Station (
  `stationNo` int(2) NOT NULL,
  `stationName` varchar(30) NOT NULL,
  PRIMARY KEY (`stationNo`)
);

CREATE TABLE fit.Ingredient(
 `ingredientNo` int(5) NOT NULL,
  `ingredientName` varchar(20) NOT NULL,
  `ingredientShelfLife` int(2),
  `par` varchar(5) NOT NULL,
  `stationNo` int(2) NOT NULL,
  PRIMARY KEY (`ingredientNo`),
  CONSTRAINT `fk_stationNo` FOREIGN KEY (`stationNo`) REFERENCES `Station` (`stationNo`)
);

CREATE TABLE fit.Ingredient_Item(
 `ingredientNo` int(5) NOT NULL,
 `ingredientDateProduced` date NOT NULL,
 `expired` tinyint(1) NOT NULL,
 `quantity` decimal(6,2) NOT NULL,
 PRIMARY KEY (`ingredientNo`, `ingredientDateProduced`),
 CONSTRAINT `fk_ingredientNO_item` FOREIGN KEY (`ingredientNo`) REFERENCES `Ingredient` (`ingredientNo`),
 CONSTRAINT CK_ingredient CHECK (`ingredientDateProduced` > CURRENT_DATE)
);

CREATE TABLE fit.Client (
  `clientNo` int(7) NOT NULL,
  `clientName` varchar(50) NOT NULL,
  `clientCompany` varchar(50) NOT NULL,
  `clientAddress` varchar(60) NOT NULL,
  `clientEmail` varchar(50) NOT NULL,
  `clientPhoneNumber` varchar(45) NOT NULL,
  PRIMARY KEY (`clientNo`),
  UNIQUE KEY `clientEmail_UNIQUE` (`clientEmail`),
  CONSTRAINT CK_clientPhone CHECK (`clientPhoneNumber` LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]') 
);

CREATE TABLE fit.Purchase_Order(
  `poNo` int(7) NOT NULL,
  `poDatePlaced` date NOT NULL,
  `poDateRequired` date NOT NULL,
  `poNotes` varchar(100),
  `empName` varchar(50) NOT NULL,
  `empEmail` varchar(50) NOT NULL,
  `delivery` tinyint(1),
  `completed` tinyint(1),
  `clientNo` int(7),
  PRIMARY KEY (`poNo`,`poDatePlaced`),
  CONSTRAINT `fk_clientNo` FOREIGN KEY (`clientNo`) REFERENCES `Client` (`clientNo`),
  CONSTRAINT CK_poDateRequired CHECK (`poDateRequired` > CURRENT_DATE+5)
);

CREATE TABLE fit.PO_Items(
`poItemNo` int(7) NOT NULL AUTO_INCREMENT,
`poNo` int(7) NOT NULL,
`empName` VARCHAR(100) NOT NULL,
`quantity` decimal(6,2),
`menuItem` varchar(40),
PRIMARY KEY(`poItemNo`,`empName`),
CONSTRAINT `fk_po_item` FOREIGN KEY (`poNo`) REFERENCES `Purchase_Order` (`poNo`)
);

CREATE TABLE fit.Meal(
`mealItemNo` int(4) NOT NULL,
`mealName` varchar(40) NOT NULL,
`mealShelfLife` int(2),
PRIMARY KEY (`mealItemNo`)
);

CREATE TABLE fit.Meal_Item(
`mealItemNo` int(4) NOT NULL,
`mealDateProduced` date NOT NULL,
`mealExpired` tinyint(1),
`quantity` decimal(6,2),
PRIMARY KEY (`mealItemNo`,`mealDateProduced`),
CONSTRAINT `fk_mealItemNo_MI` FOREIGN KEY (`mealItemNo`) REFERENCES `Meal` (`mealItemNo`),
CONSTRAINT CK_mealDateProduced CHECK (`mealDateProduced` > CURRENT_DATE)

);

CREATE TABLE fit.Meal_Recipe(
  `mealItemNo` int(4) NOT NULL,
  `ingredientNo` int(5) NOT NULL,
  `quantity` decimal(6,2),
  PRIMARY KEY (`mealItemNo`,`ingredientNo`),
  CONSTRAINT `fk_mealItemNo` FOREIGN KEY (`mealItemNo`) REFERENCES `Meal` (`mealItemNo`),
  CONSTRAINT `fk_ingredientNo` FOREIGN KEY (`ingredientNo`) REFERENCES `Ingredient` (`ingredientNo`)
);
CREATE TABLE fit.Order_Item(
  `mealItemNo` int(4) NOT NULL,
  `poNo` int (6) NOT NULL,
  PRIMARY KEY (`mealItemNo`, `poNo`),
  CONSTRAINT `fk_mealItemNoOderItem` FOREIGN KEY (`mealItemNo`) REFERENCES `Meal_Item` (`mealItemNo`),
  CONSTRAINT ` fk_poNoOrderItem` FOREIGN KEY (`poNo`) REFERENCES `Purchase_Order` (`poNo`)
);

CREATE TABLE fit.Grab_List(
	`ingreNo` int(4) NOT NULL,
	`ingredientName` varchar(20) NOT NULL,
	`par` varchar(5) NOT NULL,
	`stationNo` 
);
