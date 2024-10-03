
--
-- Заполнение таблицы категорий
--
insert into order_service.categories (uuid, name)
values ('9fafcad1-350b-4c14-aad7-902c89608354', 'Напитки'),
       ('88aa6223-f61a-48b7-a1bf-934ac156d02e', 'Пицца');

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
       ('6ac5dfe6-589f-4e8f-b271-e76ba2bdddb7', 'Сырная пицца', 2);


--
-- Заполнение таблицы связи продукт - заказ
--
insert into order_service.product_to_order (product_id, order_id, product_count)
values (1, 1, 2),
       (2, 1, 3);



