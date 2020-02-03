DELETE from user_roles;
DELETE from users;
DELETE from professors;
DELETE from students;
DELETE from university_courses;
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE university_courses AUTO_INCREMENT = 1;

INSERT INTO users(name, email, password, address )
VALUES ('Ямчеков Н.А', 'fr@ya.ru', 'password', 'ул. Стокгольма 28'),
       ('Григорьев Е.В.','gr@ya.ru','password','ул. Глушко 12В'),
       ('Старостенко В.В', 'st@ya.ru', 'password','ул. Мамонтова 43'),
       ('Белялов Л.Э','fr@yahoo.com','password', 'ул. Белогвардейцев 38' ),
       ('Новожилов Э.А.','fr@gmail.com','password', 'ул. Васильков 34' ),
       ('Савчук А.И', 'fr@mail.ru', 'password', 'ул. Самойловой 33');

INSERT INTO user_roles(user_id,role)
VALUES ('1','ROLE_STUDENT'),
       ('2','ROLE_PROFESSOR'),
       ('3','ROLE_PROFESSOR'),
       ('4','ROLE_STUDENT'),
       ('5','ROLE_STUDENT'),
       ('6','ROLE_STUDENT'),
       ('1','ROLE_PROFESSOR');


INSERT INTO professors(user_id, telephone, cost)
VALUES ('2', '+79787555522', 15000),
       ('3', '+79787555523', 18000);

INSERT INTO students (user_id, record_number, average_rating)
VALUES ('1', 111, 4.7),
       ('4', 112, 4.7),
       ('5', 113, 4.7),
       ('6', 114, 4.7);

INSERT INTO university_courses (user_id, name, number, cost)
VALUES (1,'Теормех', 666, 15250.0),
       (1,'Мат. анализ', 669, 15900.0),
       (1,'Теория струн', 668, 16900.0),
       (6,'Начерталка', 670, 15251.0),
       (6,'Англ. язык', 671, 25900.0),
       (6,'Основы программирования', 672, 16999.0),
       (2,'Капитализм', 34, 255),
       (2,'Марксизм', 35, 275);

