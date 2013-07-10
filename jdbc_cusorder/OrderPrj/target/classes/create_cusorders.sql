delimiter ;
DROP SCHEMA IF EXISTS `cs548_orders`; 
CREATE SCHEMA `cs548_orders` ;
use `cs548_orders`;

delimiter $$

CREATE TABLE `cusorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusnum` varchar(15) NOT NULL,
  `date` date DEFAULT NULL,
  `amount` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `totalOrders` int(10) unsigned zerofill DEFAULT NULL,
  `price` float unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter ;
INSERT INTO `cusorder` (`cusnum`,`date`,`amount`) VALUES ('5B999','2013-03-21',100);
INSERT INTO `cusorder` (`cusnum`,`date`,`amount`) VALUES ('6C779','2012-11-03',200);

INSERT INTO `cs548_orders`.`product` (`name`, `totalOrders`, `price`) VALUES ('Z500', '5', '99.99');
INSERT INTO `cs548_orders`.`product` (`name`, `totalOrders`, `price`) VALUES ('X2200', '10', '53.67');