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