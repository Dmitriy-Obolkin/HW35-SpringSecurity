SHOW DATABASES;
USE hillel_hw;
SHOW TABLES;

CREATE TABLE t_product (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
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

select * from t_product;

drop table t_order;
drop table t_order_product;
drop table t_product;