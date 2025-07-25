-- user_table
CREATE TABLE user_table (
    user_id VARCHAR(50) NOT NULL PRIMARY KEY,
    user_name VARCHAR(100),
    email VARCHAR(100),
    account VARCHAR(100) NOT NULL UNIQUE,
    ppassword VARCHAR(100) NOT NULL  -- 儲存 hash + salt 後的密碼
);

-- product
CREATE TABLE product (
    product_no BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DOUBLE,
    fee_rate DOUBLE
);

-- like_list
CREATE TABLE like_list (
    sn BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_name INT,
    account VARCHAR(100),
    total_fee DOUBLE,
    total_amount DOUBLE,
    user_id VARCHAR(50) NOT NULL,
    product_no BIGINT NOT NULL,
    CONSTRAINT fk_like_user FOREIGN KEY (user_id) REFERENCES user_table(user_id),
    CONSTRAINT fk_like_product FOREIGN KEY (product_no) REFERENCES product(product_no),
    CONSTRAINT uc_user_product UNIQUE (user_id, product_no)  -- 設定一個使用者與一項商品只能對應一筆資料
);