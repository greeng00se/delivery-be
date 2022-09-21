create table menu (
    menu_id bigint NOT NULL AUTO_INCREMENT,
    description varchar(255),
    name varchar(255),
    price bigint,
    priority bigint,
    shop_id bigint,
    PRIMARY KEY (menu_id)
);

create table option_group_spec (
    option_group_spec_id bigint NOT NULL AUTO_INCREMENT,
    create_date timestamp,
    modified_date timestamp,
    basic boolean,
    exclusive boolean,
    name varchar(255),
    menu_id bigint,
    primary key (option_group_spec_id)
);

create table option_spec (
    option_spec_id bigint NOT NULL AUTO_INCREMENT,
    create_date timestamp,
    modified_date timestamp,
    name varchar(255),
    price bigint,
    option_group_spec_id bigint,
    primary key (option_spec_id)
);

create table owner (
    owner_id bigint NOT NULL AUTO_INCREMENT,
    create_date timestamp,
    modified_date timestamp,
    authorities varchar(255),
    email varchar(255),
    name varchar(255),
    password varchar(255),
    primary key (owner_id)
);

create table shop (
    shop_id bigint NOT NULL AUTO_INCREMENT,
    create_date timestamp,
    modified_date timestamp,
    name varchar(255),
    address varchar(255),
    min_order_amount bigint,
    open boolean,
    owner_id bigint,
    phone_number varchar(255),
    primary key (shop_id)
);
