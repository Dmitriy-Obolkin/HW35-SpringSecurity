SHOW DATABASES;
USE hillel_hw;
SHOW TABLES;

CREATE TABLE t_product (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL UNIQUE,
                         cost DOUBLE NOT NULL
);

CREATE TABLE t_order (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         date DATE NOT NULL,
                         cost DOUBLE NOT NULL
);

CREATE TABLE t_order_product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         order_id INT NOT NULL REFERENCES t_order(id),
                         product_id INT NOT NULL REFERENCES t_product(id),
                         UNIQUE (order_id, product_id)
);

CREATE TABLE t_user (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(50) NOT NULL
);

SELECT * FROM t_product;
SELECT * FROM t_order;
SELECT * FROM t_order_product;
SELECT * FROM t_user;

DROP TABLE t_order;
DROP TABLE t_order_product;
DROP TABLE t_product;
DROP TABLE t_user;