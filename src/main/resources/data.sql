DELETE
FROM user_role;
DELETE
FROM user_db;

INSERT INTO user_db (email, password, enabled)
VALUES ('user@ukr.net', 'password', true),
       ('admin@gmail.com', 'admin', true);

INSERT INTO user_role (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2);
