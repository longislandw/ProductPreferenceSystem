-- User 資料
INSERT INTO user_table (user_id, user_name, email, account)
VALUES ('A1236456789', '王O明', 'test@email.com', '1111999666');

-- Product 資料
INSERT INTO product (product_no, product_name, price, fee_rate)
VALUES (1, '基金', 1000.0, 0.1),
       (2, '債券', 2000.0, 0.01);

-- LikeList 資料
INSERT INTO like_list (order_name, account, total_fee, total_amount, user_id, product_no)
VALUES (2, '1111999666', 20.0, 2020.0, 'A1236456789', 1),
       (1, '1111999666', 10.0, 1010.0, 'A1236456789', 2);