DELETE
FROM user_role;
DELETE
FROM user_db;

INSERT INTO faculty (name_en, name_ua, vacs, vacs_contr)
VALUES ('Mathematical', 'Математичний', 3, 5),
       ('Physical', 'Фізичний', 2, 3),
       ('Biological', 'Біологічний', 5, 7),
       ('Sociological', 'Соціологічний', 3, 3);

INSERT INTO user_db (email, password, enabled)
VALUES ('admin@gmail.com', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', true),
       ('admin@user.net', '1', true);


INSERT INTO user_db (email, password, first_name, last_name, patronymic, city, region, school_name, enabled, faculty_id)
VALUES ('user@ukr.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Проскурін', 'Дмитро',
        'Сергійович', 'Київ', 'Київська', 'Школа№10', true, 4),
       ('user@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Іван', 'Богданов',
        'Миколайович', 'Сміла', 'Черкаська', 'Школа№4', true, 4),
       ('user1@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Василь', 'Ситник',
        'Іванович', 'Миргород', 'Полтавська', 'Гімназія№5', true, 4),
       ('user2@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Іван', 'Борисов', 'Іванович',
        'Лубни', 'Полтавська', 'Ліцей№5', true, 4),
       ('user3@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Ганна', 'Давидова',
        'Олексіївна', 'Вінниця', 'Вінницька', 'ПТУ№2', true, 4),
       ('user4@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Іван', 'Швець', 'Дмитрович',
        'Стрий', 'Львівська', 'Ліцей№1', true, 4),
       ('user5@user.net', '$2y$12$OiNQAoK940Y2OfWfA06mWexCgcrToKQgG9RzUX1qjDjXUVs5hXQAG', 'Руслан', 'Черненко',
        'Дмитрович', 'Рівне', 'Рівненська', 'Школа№5', true, 4);

INSERT INTO user_role (role, user_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 3),
       ('ROLE_USER', 4),
       ('ROLE_USER', 5),
       ('ROLE_USER', 6),
       ('ROLE_USER', 7),
       ('ROLE_USER', 8),
       ('ROLE_USER', 9);

INSERT INTO subject (name_en, name_ua)
VALUES ('Mathematics', 'Математика'),
       ('Physics', 'Фізика'),
       ('Chemistry', 'Хімія'),
       ('Biology', 'Біологія'),
       ('History', 'Історія'),
       ('Literature', 'Література');



INSERT INTO faculty_subject(faculty_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 3),
       (3, 4),
       (4, 5),
       (4, 6);

INSERT INTO student_faculty(student_id, faculty_id)
VALUES (3, 4),
       (4, 4),
       (5, 4),
       (6, 4),
       (7, 4),
       (8, 4),
       (9, 4);

INSERT INTO student_mark(user_id, subject_id, mark)
VALUES (3, 5, 100),
       (3, 6, 100),
       (4, 5, 110),
       (4, 6, 110),
       (5, 5, 120),
       (5, 6, 120),
       (6, 5, 130),
       (6, 6, 130),
       (7, 5, 140),
       (7, 6, 140),
       (8, 5, 150),
       (8, 6, 150),
       (9, 5, 160),
       (9, 6, 160);
