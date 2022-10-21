CREATE TABLE `customer`
(
    `customer_id`   bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`   datetime,
    `modified_date` datetime,
    `authorities`   varchar(255),
    `email`         varchar(255),
    `name`          varchar(255),
    `password`      varchar(255)
);

CREATE TABLE `menu`
(
    `menu_id`       bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`   datetime,
    `modified_date` datetime,
    `description`   varchar(255),
    `name`          varchar(255),
    `price`         bigint,
    `priority`      bigint,
    `shop_id`       bigint
);

CREATE TABLE `option_group_spec`
(
    `option_group_spec_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`          datetime,
    `modified_date`        datetime,
    `basic`                bit,
    `exclusive`            bit,
    `name`                 varchar(255),
    `menu_id`              bigint
);

CREATE TABLE `option_spec`
(
    `option_spec_id`       bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`          datetime,
    `modified_date`        datetime,
    `name`                 varchar(255),
    `price`                bigint,
    `option_group_spec_id` bigint
);

CREATE TABLE `order_line_item`
(
    `order_line_item_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`        datetime,
    `modified_date`      datetime,
    `food_count`         integer,
    `menu_id`            bigint,
    `food_name`          varchar(255),
    `order_id`           bigint
);

CREATE TABLE `order_option`
(
    `order_option_id`       bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`           datetime,
    `modified_date`         datetime,
    `name`                  varchar(255),
    `price`                 bigint,
    `order_option_group_id` bigint
);

CREATE TABLE `order_option_group`
(
    `order_option_group_id` bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`                  varchar(255),
    `order_line_item_id`    bigint
);

CREATE TABLE `orders`
(
    `order_id`      bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`   datetime,
    `modified_date` datetime,
    `status`        varchar(255),
    `ordered_time`  datetime,
    `shop_id`       bigint,
    `user_id`       bigint
);

CREATE TABLE `owners`
(
    `owner_id`      bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`   datetime,
    `modified_date` datetime,
    `authorities`   varchar(255),
    `email`         varchar(255),
    `name`          varchar(255),
    `password`      varchar(255)
);

CREATE TABLE `shop`
(
    `shop_id`          bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `create_date`      datetime,
    `modified_date`    datetime,
    `address`          varchar(255),
    `min_order_amount` bigint,
    `name`             varchar(255),
    `open`             bit,
    `owner_id`         bigint,
    `phone_number`     varchar(255)
);

ALTER TABLE `option_group_spec`
    ADD FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`);

ALTER TABLE `option_spec`
    ADD FOREIGN KEY (`option_group_spec_id`) REFERENCES `option_group_spec` (`menu_id`);

ALTER TABLE `menu`
    ADD FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`);

ALTER TABLE `shop`
    ADD FOREIGN KEY (`owner_id`) REFERENCES `owners` (`owner_id`);

ALTER TABLE `orders`
    ADD FOREIGN KEY (`user_id`) REFERENCES `customer` (`customer_id`);

ALTER TABLE `orders`
    ADD FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`);

ALTER TABLE `order_line_item`
    ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

ALTER TABLE `order_option_group`
    ADD FOREIGN KEY (`order_line_item_id`) REFERENCES `order_line_item` (`order_line_item_id`);

ALTER TABLE `order_option`
    ADD FOREIGN KEY (`order_option_group_id`) REFERENCES `order_option_group` (`order_option_group_id`);
