CREATE TABLE black_list
(
    b_no       number(2) PRIMARY KEY,
    b_name     varchar2(10 char) NOT NULL,
    b_point    number(2)         NOT NULL,
    b_is_black char(1) GENERATED ALWAYS AS (
        CASE
            WHEN b_point >= 3 THEN 'Y'
            ELSE 'N'
            END
        ) VIRTUAL
);

DROP TABLE BLACK_LIST;

CREATE SEQUENCE black_list_seq;

INSERT INTO BLACK_LIST
VALUES (BLACK_LIST_SEQ.nextval, '여은사', 5, default);
INSERT INTO BLACK_LIST
VALUES (BLACK_LIST_SEQ.nextval, '조예진', 3, default);
INSERT INTO BLACK_LIST
VALUES (BLACK_LIST_SEQ.nextval, '김지영', 2, default);

SELECT *
FROM BLACK_LIST;



