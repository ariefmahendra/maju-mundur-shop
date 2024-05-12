create table mst_products (
    id varchar primary key,
    product_code varchar,
    product_name varchar,
    product_price bigint,
    stock integer
);

create table mst_merchant(
    id varchar primary key,
    email varchar unique not null,
    password varchar not null
);

create table mst_users(
    id varchar primary key,
    name varchar,
    email varchar unique not null ,
    password varchar not null ,
    phone_number varchar unique,
    rewards bigint
);

create table tx_purchases (
    id varchar primary key,
    purchase_date varchar not null,
    total bigint,
    user_id varchar,
    foreign key (user_id) references mst_users(id)
);

create table tx_purchase_details(
    id varchar primary key,
    purchase_id varchar,
    product_id varchar,
    qty integer,
    foreign key (purchase_id) references tx_purchases(id),
    foreign key (product_id) references mst_products(id)
);
