CREATE DATABASE IF NOT EXISTS university;

DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS professors;
DROP TABLE IF EXISTS university_course;

CREATE TABLE students
(
    id             SERIAL PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(50) NOT NULL,
    address        VARCHAR(90) NOT NULL,
    email          VARCHAR(50) NOT NULL,
    record_number  INTEGER     NOT NULL,
    average_rating FLOAT       NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX students_unique_email_record_number_idx ON students (email, record_number);


CREATE TABLE professors
(
    id      SERIAL PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    address VARCHAR(90) NOT NULL,
    cost    FLOAT       NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE university_course
(
    id     SERIAL PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(90) NOT NULL,
    number INTEGER(7)  NOT NULL,
    cost   FLOAT       NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX university_course_unique_number_idx ON university_course (number);

INSERT INTO professors(name, address, cost)
VALUES ('Невмержицкий В.А.', 'Крым, Джанкой, ул. Васильков 25', 15000),
       ('Дуйко В. А.', 'Крым, с. Карповка, ул. Мира 66а', 18000);

INSERT INTO students (name, address, email, record_number, average_rating)
VALUES ('Ямчеков Н.А', 'ул. Стокгольма 28', 'fr@ya.ru', 111, 4.7),
       ('Белялов Л.Э', 'ул. Белогвардейцев 38', 'fr@yahoo.com', 112, 4.7),
       ('Новожилов Э.А.', 'ул. Васильков 34', 'fr@gmail.com', 113, 4.7),
       ('Савчук А.И', 'ул. Самойловой 33', 'fr@mail.ru', 114, 4.7);

INSERT INTO university_course (name, number, cost)
VALUES ('Основы теоритической физики', 1, 172.15),
       ('Основы мат. анализа', 2, 44500.82),
       ('Английский язык', 3, 12789.5);