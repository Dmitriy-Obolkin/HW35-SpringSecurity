SHOW DATABASES;
USE hillel_hw;
SHOW TABLES;

CREATE TABLE t_product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);

CREATE TABLE t_order_product (
    order_id INT REFERENCES t_order(id),
    product_id INT REFERENCES t_product(id),
    PRIMARY KEY(order_id, product_id)
);
