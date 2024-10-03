drop schema if exists user_service cascade;

create schema user_service;

create table user_service.users(
    id serial,
    name text,
    registered_at date,
    email text
);