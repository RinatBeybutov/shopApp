--
-- Заполнение таблицы категорий
--
insert into order_service.categories (uuid, name)
values ('9fafcad1-350b-4c14-aad7-902c89608354', 'Напитки'),
       ('88aa6223-f61a-48b7-a1bf-934ac156d02e', 'Пицца'),
       ('f41e4124-8e87-4142-936a-7452ba5e7304', 'Супы'),
       ('5b75d6df-0122-4494-b48a-509cc09c8470', 'Десерты');

--
-- Заполнение таблицы заказов
--
insert into order_service.orders (uuid, created_at, user_uuid)
values ('36c4caec-48ba-4099-abc6-cf80026905d6', '2024-09-30', '423bd97c-f1af-413c-9f62-18b4ab158293');

--
-- Заполнение таблицы продуктов
--
insert into order_service.products (uuid, name, category_id)
values ('c4ec28c3-2369-4709-9b44-355d3e2d5640', 'Coca-cola', 1),
       ('77ff09f2-28cd-4ec0-865b-2e4917433631', 'Вода', 1),
       ('6ac5dfe6-589f-4e8f-b271-e76ba2bdddb7', 'Сырная пицца', 2),
       ('7cf4fa94-db8d-4649-973a-83d6564b5de4', 'Бощик', 3),
       ('c8c61fd0-ec93-440f-9f2e-6bc654ace44f', 'Сырный суп', 3),
       ('09a15b06-2752-4df7-80c0-880493c02e26', 'Суп с фрикадельками', 3),
       ('4a551e01-cf9d-47c6-867c-effb6fb34f4e', 'Пудинг', 4),
       ('44f67d04-52ad-4007-a33d-02b5f87c7526', 'Медовик', 4),
       ('af8c649c-90a7-40e2-9a96-84dbd888c2eb', 'Мороженое', 4),
       ('424a15d6-97c9-469e-9770-0be32caaba4e', 'Шоколад', 4);

--
-- Заполнение таблицы связи продукт - заказ
--
insert into order_service.product_to_order (product_id, order_id, product_count)
values (1, 1, 2),
       (2, 1, 3);