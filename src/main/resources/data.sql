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

INSERT INTO subject (name_en, name_ua)
VALUES ('Mathematics', 'Математика'),
       ('Physics', 'Фізика'),
       ('Chemistry', 'Хімія'),
       ('Biology', 'Біологія'),
       ('History', 'Історія'),
       ('Literature', 'Література');

INSERT INTO faculty (name_en, name_ua, vacs, vacs_contr)
VALUES ('Mathematical', 'Математичний', 5, 3),
       ('Physical', 'Фізичний', 5, 3),
       ('Biological', 'Біологічний', 5, 3),
       ('Historical', 'Історичний', 5, 3);

INSERT INTO faculty_subject(faculty_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 3),
       (3, 4),
       (4, 5),
       (4, 6);