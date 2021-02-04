ALTER TABLE if exists user_db
  DROP CONSTRAINT IF EXISTS diploma_fk;
DROP TABLE IF EXISTS diploma;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS user_db;



CREATE TABLE user_db
(
  id           SERIAL PRIMARY KEY,
  email        VARCHAR           NOT NULL,
  password     VARCHAR           NOT NULL,
  first_name   VARCHAR,
  last_name    VARCHAR,
  patronymic   VARCHAR,
  city         VARCHAR,
  region       VARCHAR,
  school_name  VARCHAR,
  diplom_image BYTEA,
  enabled      BOOL DEFAULT TRUE NOT NULL,
  diploma_id   INTEGER
);
CREATE UNIQUE INDEX users_unique_email_idx ON user_db (email);

CREATE TABLE user_role
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE
);

CREATE TABLE diploma
(
  id         SERIAL PRIMARY KEY,
  math       INTEGER,
  physics    INTEGER,
  history    INTEGER,
  literature INTEGER,
  chemistry  INTEGER,
  biology    INTEGER,
  user_id    INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE
);

ALTER TABLE user_db
  ADD CONSTRAINT diploma_fk
    FOREIGN KEY (diploma_id)
      REFERENCES diploma (id);