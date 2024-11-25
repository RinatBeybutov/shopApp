drop schema if exists user_service cascade;

create schema user_service;

create table user_service.users(
    id serial,
    uuid uuid,
    name text,
    registered_at date,
    email text
);

insert into user_service.users (uuid, name, registered_at, email)
values ('423bd97c-f1af-413c-9f62-18b4ab158293', 'Gaben', '2024-09-19', 'abc@mail.ru'),
       ('d094027a-47b6-4dce-87b7-ce1c7b8ddb33', 'Kob', '2024-09-20', 'abc@mail.ru');

