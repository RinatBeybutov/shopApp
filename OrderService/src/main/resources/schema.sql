--
-- Создание схемы для сервиса
--
drop schema if exists order_service cascade;

create schema order_service;

--
-- Создание таблицы категорий
--
create table order_service.categories(
    id serial unique,
    uuid uuid,
    name text
);

--
-- Создание таблицы заказов
--
create table order_service.orders(
    id serial unique,
    uuid uuid,
    created_at date,
    user_uuid uuid
);

--
-- Создание таблицы продуктов
--
create table order_service.products(
    id serial unique,
    uuid uuid,
    name text,
    category_id integer,
    foreign key (category_id) references order_service.categories (id)
);

--
-- Создание таблицы связи продукт - заказ
--
create table order_service.product_to_order(
    id serial unique,
    product_id integer,
    order_id integer,
    product_count integer,
    foreign key (product_id) references order_service.products (id),
    foreign key (order_id) references order_service.orders (id)
);