--
-- Database `db_ca_viso` for schema `dev` 
--
CREATE DATABASE IF NOT EXISTS `db_ca_viso`;
USE db_ca_viso;

--
-- Table structure for table `address`
--
CREATE table IF NOT EXISTS `db_ca_viso`.`address` (
  address_id INT NOT NULL AUTO_INCREMENT COMMENT 'Unique id of the table',
  street VARCHAR(120) NOT NULL,
  number VARCHAR(5) NOT NULL,
  letter VARCHAR(3) DEFAULT NULL,
  town VARCHAR(80) DEFAULT NULL,
  city VARCHAR(60) DEFAULT NULL,
  country VARCHAR(40) DEFAULT NULL,
  postal_code INT NOT NULL DEFAULT '41520',
  latitude DECIMAL(8,6) DEFAULT -5.7199300,
  longitude DECIMAL(8,6) DEFAULT 37.3910600,
  additional_info LONGTEXT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(80) NOT NULL DEFAULT 'rol_admin',
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(80) NOT NULL DEFAULT 'rol_admin',
  CONSTRAINT address_pk PRIMARY KEY (address_id),
  INDEX index_postal_code (postal_code ASC)
) ENGINE=InnoDB COMMENT='This table represents the address entity.';