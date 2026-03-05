/*
    학생 테이블

    이름, 나이, 주소
    */

CREATE SEQUENCE student_db_seq;

CREATE TABLE student_db
(
    s_no   number(2) PRIMARY KEY,
    s_name varchar2(10 char) not null,
    s_age  number(3)         not null,
    s_addr varchar2(30 char) not null
);

-- test data
INSERT INTO student_db
VALUES (student_db_seq.nextval, 'mz', 20, '서울시 종로구');
INSERT INTO student_db
VALUES (student_db_seq.nextval, 'sw', 30, '서울시 금천구');
INSERT INTO student_db
VALUES (student_db_seq.nextval, 'ms', 40, '서울시 강남구');

DELETE student_db
WHERE s_no BETWEEN 4 AND 6;

SELECT *
FROM student_db;

DELETE student_db
WHERE s_no = 10;

CREATE SEQUENCE coffee_test_seq;

CREATE TABLE coffee_test
(
    c_no     number(2) PRIMARY KEY,
    c_name   varchar2(30 char) NOT NULL,
    c_price  number(5)         NOT NULL,
    c_origin varchar2(20 char) NOT NULL
);

INSERT INTO coffee_test
VALUES (coffee_test_seq.nextval, 'americano', 2000, '에티오피아');
INSERT INTO coffee_test
VALUES (coffee_test_seq.nextval, 'cafelatte', 2500, '케냐');

SELECT *
FROM coffee_test;

DROP TABLE bmi;

CREATE TABLE bmi
(
    b_no     number(2) PRIMARY KEY,
    b_name   varchar2(10 char) NOT NULL,
    b_height number(4, 1)      NOT NULL,
    b_weight number(3, 1)      NOT NULL,
    b_bmi    number(3, 1)      NOT NULL,
    b_status VARCHAR2(20)      NOT NULL,
    CONSTRAINT chk_status
        CHECK (b_status IN ('LOW', 'NORMAL', 'HIGH'))
);

CREATE SEQUENCE bmi_seq;

INSERT INTO bmi
VALUES (bmi_seq.nextval, 'sw', 176.5, 70.5, 23.7, 'NORMAL');
INSERT INTO bmi
VALUES (bmi_seq.nextval, 'sw', 176.5, 70.5, 23.7, 'NORMAL2');