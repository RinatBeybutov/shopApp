CREATE TABLE IF NOT EXISTS user_service (
id            BIGINT NOT NULL,
user_name     VARCHAR(255) NOT NULL,
email         VARCHAR(255) NOT NULL,
registered_at TIMESTAMP NOT NULL,
CONSTRAINT pk_user_service PRIMARY KEY (id)
);

CREATE INDEX        ix_user_service_user_name ON user_service (user_name);
CREATE UNIQUE INDEX ix_user_service_email     ON user_service (email);