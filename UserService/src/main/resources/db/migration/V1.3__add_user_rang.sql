--
-- Добавление колонки количества заказов
--
alter table users add column order_count integer not null default 0;

--
-- Добавление колонки ранга
-- 0 - нет ранга
-- 1 - новичок
-- 2 - герой
-- 3 - сенсей
-- 4 - магистр
alter table users add column rang integer not null default 0;