DELETE
FROM user_role;
DELETE
FROM user_db;

INSERT INTO user_db (email, password, enabled)
VALUES ('user@ukr.net', '$2y$12$ZlUag7JWB9AlOJYhvau4Fu1PqZ.HEbn5bkK51JMjaSqiqLcoVyIzK', true),
       ('admin@gmail.com', '$2y$12$jIjrcwMQMF3wz/C1Jdwii.jGrp1UNqzAoWb8n/Cc0O/6O10g2USqa', true);

INSERT INTO user_role (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2);
