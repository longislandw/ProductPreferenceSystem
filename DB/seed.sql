-- User 資料
INSERT INTO user_table (user_id, user_name, email, account, ppassword)
VALUES ('A1236456789', '王O明', 'test@email.com', '1111999666', '$2a$12$fFe6M1AF8w6X3UgCUAoGseTAwvHnqOCddFigKnyCU8VoAGRUtCuYS'),
       ('A0000000001', 'Ariel', 'ariel@email.com', '2222999667', '$2a$12$au.vbRaudFGCHrJzx5lDsu2wUR5Nazz3bx1J3XO4DlN7K.HsJBw/.');

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