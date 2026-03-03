CREATE TABLE db_test
(
    d_no   number(2) PRIMARY KEY,
    d_name varchar2(20 char),
    d_age  number(2)
);

CREATE sequence db_test_seq;

INSERT INTO db_test
VALUES (db_test_seq.nextval, 'sw', 20);

INSERT INTO db_test
VALUES (db_test_seq.nextval, 'test2', 30);

SELECT *
FROM db_test;

