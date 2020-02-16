CREATE DATABASE IF NOT EXISTS university;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS university_courses;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS professors;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS fuculties;

CREATE TABLE users(
                      id      INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name    VARCHAR(100) NOT NULL,
                      email   VARCHAR(100) NOT NULL,
                      password VARCHAR(100)  NOT NULL,
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
    user_id INTEGER NOT NULL,
    name   VARCHAR(100) NOT NULL,
    number INTEGER(7)  NOT NULL,
    cost   FLOAT(9)       NOT NULL,
    CONSTRAINT university_courses_unique_number_idx UNIQUE (number)
);


