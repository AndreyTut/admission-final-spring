DROP TABLE IF EXISTS diploma CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS user_db CASCADE;
DROP TABLE IF EXISTS subject CASCADE;
DROP TABLE IF EXISTS faculty CASCADE;
DROP TABLE IF EXISTS faculty_subject CASCADE;
DROP TABLE IF EXISTS student_mark CASCADE;
DROP TABLE IF EXISTS student_faculty CASCADE;

CREATE TABLE faculty
(
  id         SERIAL PRIMARY KEY,
  name_en    VARCHAR,
  name_ua    VARCHAR,
  vacs       INTEGER,
  vacs_contr INTEGER,
  finalized  BOOLEAN DEFAULT FALSE
);


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
  diploma_id   INTEGER,
  faculty_id   INTEGER,
  status       INTEGER,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id)
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

CREATE TABLE subject
(
  id      SERIAL PRIMARY KEY,
  name_en VARCHAR,
  name_ua VARCHAR,
  CONSTRAINT name_idx UNIQUE (name_en, name_ua)
);


CREATE TABLE faculty_subject
(
  faculty_id INTEGER,
  subject_id INTEGER,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE
);

CREATE TABLE student_mark
(
  id         SERIAL PRIMARY KEY,
  user_id    INTEGER,
  subject_id INTEGER,
  mark       INTEGER,
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE
);

CREATE TABLE student_faculty
(
  student_id INTEGER,
  faculty_id INTEGER,
  FOREIGN KEY (student_id) REFERENCES user_db (id) ON DELETE CASCADE,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE
)