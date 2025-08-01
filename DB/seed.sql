-- User 資料
INSERT INTO user_table (user_id, user_name, email, account, ppassword)
VALUES ('A1236456789', '王O明', 'test@email.com', '1111999666', '{bcrypt}$2a$10$P/I2HrXQobQdeyowWfG6Leslq.IvVBruVClh/MyjrEZpaMXBvdBy2'),
       ('0000', '訪客', 'visitor@email.com', '00000000', '{bcrypt}$2a$10$QGSWI8lbtzduxN8fwer/8.VE3ja0anxRIdPdhTDPqroDU4xzhm5HK'),
       ('A0000000001', 'Ariel', 'ariel@email.com', '2222999667', '{bcrypt}$2a$10$QGSWI8lbtzduxN8fbYt/8.VE3ja0anxRIdPdhTDPqroDU4xzhm5HK'),
       ('0001', 'ADMIN', 'admin@email.com', '00000001', '{bcrypt}$2a$10$P/I2HrXQobQdeyowWfG6Leslq.IvVBruVClh/MyjrEZpaMXBvdBy2');

-- User Authority 資料
INSERT INTO user_authority (authority, description) VALUES ('VISITOR', '訪客權限'), ('USER', '一般使用者'), ('ADMIN', '管理員');

-- user_authority_mapping 資料
INSERT INTO user_authority_mapping (user_id, authority) VALUES ('0000', 'VISITOR'), ('A1236456789', 'USER'), ('A0000000001', 'USER'), ('0001', 'USER'), ('0001', 'ADMIN');

-- Product 資料
INSERT INTO product (product_no, product_name, price, fee_rate)
VALUES (1, '基金', 1000.0, 0.1),
       (2, '債券', 2000.0, 0.01),
       (3, '股票A', 3500.0, 0.04),
       (4, '股票B', 4000.0, 0.05),
       (5, '股票C', 1200.0, 0.06);

-- LikeList 資料
INSERT INTO like_list (order_name, account, total_fee, total_amount, user_id, product_no)
VALUES (2, '1111999666', 200.0, 2200.0, 'A1236456789', 1),
       (1, '1111999666', 20.0, 2020.0, 'A1236456789', 2);