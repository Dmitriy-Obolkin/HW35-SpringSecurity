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


INSERT INTO t_user (name, password, role) VALUES ('JohnD', '$2a$10$vsb.VUDlNhM0C0V/Fb9Vmucvu/FSsIPVGpnr3PBzd.aiVbv0h94bi', 'USER');
INSERT INTO t_user (name, password, role) VALUES ('user', '$2a$10$VUMgaC7MWt2yDkKBtiiHqenk7.1.JKKKFYe9/lUgveQkn3P5PIniu', 'USER');
INSERT INTO t_user (name, password, role) VALUES ('admin', '$2a$10$FNXjqWFwqoolPC6khG/f5O6OvFaueeSRdyLNcP.BCIc/G6g0ZUOsK', 'ADMIN');
