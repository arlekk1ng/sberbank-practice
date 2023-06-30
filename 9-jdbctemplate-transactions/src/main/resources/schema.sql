create schema if not exists products_bazhenov_is;

create table if not exists products_bazhenov_is.products
(
    id    integer generated always as identity primary key,
    name  varchar(255) not null,
    price numeric      not null,
    count integer      not null
);

create table if not exists products_bazhenov_is.carts
(
    id         integer generated always as identity primary key,
    promo_code varchar(255)
);

create table if not exists products_bazhenov_is.carts_products
(
    id         integer generated always as identity primary key,
    cart_id    integer not null
        constraint cart_product_cart_id_fk
            references products_bazhenov_is.carts,
    product_id integer not null
        constraint cart_product_product_id_fk
            references products_bazhenov_is.products,
    count      integer not null
);

create table if not exists products_bazhenov_is.clients
(
    id       integer generated always as identity primary key,
    name     varchar(255) not null,
    email    varchar(255),
    login    varchar(255) not null,
    password varchar(255) not null,
    cart_id  integer      not null
        constraint client_cart_id_fk
            references products_bazhenov_is.carts
);

create table if not exists products_bazhenov_is.accounts
(
    client_id integer not null
        constraint account_client_id_fk
            references products_bazhenov_is.clients,
    balance   numeric not null
);
