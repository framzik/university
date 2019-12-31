CREATE DATABASE IF NOT EXISTS university;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS professors;
DROP TABLE IF EXISTS university_courses;
DROP TABLE IF EXISTS fuculties;

CREATE TABLE users(
                      id      INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name    VARCHAR(100) NOT NULL,
                      email   VARCHAR(100) NOT NULL,
                      password VARCHAR(25)  NOT NULL,
                      enabled          BOOL DEFAULT TRUE       NOT NULL,
                      registered       TIMESTAMP DEFAULT now() NOT NULL,
                      address VARCHAR(200) NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
 CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER,
    role    VARCHAR(100),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE students
(
    user_id INTEGER NOT NULL,
    record_number  INTEGER(9)     NOT NULL,
    average_rating FLOAT(5)       NULL,
    CONSTRAINT user_student_idx UNIQUE (user_id,record_number),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE professors
(
    user_id INTEGER NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    cost    FLOAT(15)      NOT NULL,
    CONSTRAINT user_professor_idx UNIQUE (user_id,telephone),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE university_courses
(
    id     SERIAL PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(100) NOT NULL,
    number INTEGER(7)  NOT NULL,
    cost   FLOAT(9)       NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
CREATE UNIQUE INDEX university_courses_unique_number_idx ON university_courses (number);

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
       ('5','ROLE_PROFESSOR'),
       ('6','ROLE_STUDENT');


INSERT INTO professors(user_id, telephone, cost)
VALUES ('2', '+79787555522', 15000),
       ('3', '+79787555523', 18000);

INSERT INTO students (user_id, record_number, average_rating)
VALUES ('1', 111, 4.7),
       ('4', 112, 4.7),
       ('5', 113, 4.7),
       ('6', 114, 4.7);

INSERT INTO university_courses (name, number, cost)
VALUES ('Основы теоритической физики', 1, 172.15),
       ('Основы мат. анализа', 2, 44500.82),
       ('Английский язык', 3, 12789.5);