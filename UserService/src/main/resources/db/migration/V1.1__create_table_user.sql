CREATE SCHEMA IF NOT EXISTS user_service;

CREATE TABLE IF NOT EXISTS user_service.users (
    id serial, --bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    uuid uuid UNIQUE,
    name text,
    registered_at date,
    email text,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

COMMENT ON COLUMN user_service.users.id IS 'Уникальный идентификатор для каждого пользователя';
COMMENT ON COLUMN user_service.users.uuid IS 'Глобальный идентификатор пользователя';
COMMENT ON COLUMN user_service.users.name IS 'Имя пользователя';
COMMENT ON COLUMN user_service.users.registered_at IS 'Дата регистрации пользователя';
COMMENT ON COLUMN user_service.users.email IS 'Email пользователя';