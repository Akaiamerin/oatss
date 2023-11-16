CREATE DATABASE IF NOT EXISTS oatss;

USE oatss;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(20) NOT NULL,
    id_card VARCHAR(18) NOT NULL,
    telephone VARCHAR(11) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

TRUNCATE TABLE user;

DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket (
    id INT NOT NULL AUTO_INCREMENT,
    origin VARCHAR(20) NOT NULL,
    destination VARCHAR(20) NOT NULL,
    takeoff DATETIME NOT NULL,
    land DATETIME NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);

TRUNCATE TABLE ticket;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    ticket_id INT NOT NULL,
    user_id INT NoT NULL,
    time DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket (id)
);

TRUNCATE TABLE orders;

DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE persistent_logins (
    username VARCHAR (64) NOT NULL,
    series VARCHAR (64) NOT NULL,
    token VARCHAR (64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

TRUNCATE TABLE persistent_logins;

SET FOREIGN_KEY_CHECKS = 1;