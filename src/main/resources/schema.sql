DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user_db;

CREATE TABLE user_db
(
  id                  SERIAL PRIMARY KEY,
  email               VARCHAR           NOT NULL,
  password            VARCHAR           NOT NULL,
  first_name          VARCHAR,
  last_name           VARCHAR,
  patronymic          VARCHAR,
  city                VARCHAR,
  region              VARCHAR,
  diplom_avarage_mark INTEGER,
  diplom_image        BYTEA,
  enabled             BOOL DEFAULT TRUE NOT NULL

);
CREATE UNIQUE INDEX users_unique_email_idx ON user_db (email);

CREATE TABLE user_role
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE
);
