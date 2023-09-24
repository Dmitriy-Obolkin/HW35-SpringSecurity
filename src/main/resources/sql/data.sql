INSERT INTO t_product (name, cost) VALUES ('Product 1', 10.0);
INSERT INTO t_product (name, cost) VALUES ('Product 2', 20.0);
INSERT INTO t_product (name, cost) VALUES ('Product 3', 30.0);
INSERT INTO t_product (name, cost) VALUES ('Product 4', 40.0);
INSERT INTO t_product (name, cost) VALUES ('Product 5', 50.0);
INSERT INTO t_product (name, cost) VALUES ('Product 6', 60.0);
INSERT INTO t_product (name, cost) VALUES ('Product 7', 70.0);
INSERT INTO t_product (name, cost) VALUES ('Product 8', 80.0);
INSERT INTO t_product (name, cost) VALUES ('Product 9', 90.0);
INSERT INTO t_product (name, cost) VALUES ('Product 10', 100.0);


INSERT INTO t_order (date, cost) VALUES ('2023-09-21', 30.0);
INSERT INTO t_order (date, cost) VALUES ('2023-09-22', 120.0);
INSERT INTO t_order (date, cost) VALUES ('2023-09-23', 220.0);
INSERT INTO t_order (date, cost) VALUES ('2023-09-24', 190.0);


INSERT INTO t_order_product (order_id, product_id) VALUES (1, 1);
INSERT INTO t_order_product (order_id, product_id) VALUES (1, 2);
INSERT INTO t_order_product (order_id, product_id) VALUES (2, 3);
INSERT INTO t_order_product (order_id, product_id) VALUES (2, 4);
INSERT INTO t_order_product (order_id, product_id) VALUES (2, 5);
INSERT INTO t_order_product (order_id, product_id) VALUES (3, 1);
INSERT INTO t_order_product (order_id, product_id) VALUES (3, 6);
INSERT INTO t_order_product (order_id, product_id) VALUES (3, 7);
INSERT INTO t_order_product (order_id, product_id) VALUES (3, 8);
INSERT INTO t_order_product (order_id, product_id) VALUES (4, 9);
INSERT INTO t_order_product (order_id, product_id) VALUES (4, 10);