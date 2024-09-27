drop table if exists users;

create table users(
    id serial,
    name text,
    registered_at date,
    email text
);

insert into users (name, registered_at, email)
values ('Gaben', '2024-09-19', 'abc@mail.ru'),
       ('Kob', '2024-09-20', 'abc@mail.ru');