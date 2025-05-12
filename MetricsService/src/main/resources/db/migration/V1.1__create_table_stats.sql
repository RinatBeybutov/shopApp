CREATE SCHEMA IF NOT EXISTS metric_service;

CREATE TABLE IF NOT EXISTS metric_service.stats(
    id serial,
    date TIMESTAMP NOT NULL UNIQUE,
    total_orders integer NOT NULL,
    CONSTRAINT pk_stats PRIMARY KEY (id)
);

COMMENT ON COLUMN metric_service.stats.id IS 'Уникальный идентификатор';
COMMENT ON COLUMN metric_service.stats.date IS 'Дата';
COMMENT ON COLUMN metric_service.stats.total_orders IS 'Количество заказов';