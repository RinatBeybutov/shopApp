drop table if exists users;

create table users(
    id serial,
    name text,
    registered_at date,
    email text
);