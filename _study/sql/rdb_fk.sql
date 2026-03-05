CREATE TABLE menu10
(
    m_no    number(3) PRIMARY KEY,
    m_name  varchar2(20 char) not null,
    m_price number(6)         not null,
    m_place number(3)         not null,
    CONSTRAINT menu_res
        FOREIGN KEY (m_place) REFERENCES restaurant10 (r_no) ON DELETE CASCADE
);



CREATE TABLE restaurant10
(
    r_no    number(3) PRIMARY KEY,
    r_name  varchar2(30 char) not null,
    r_place varchar2(20 char) not null
);

INSERT INTO restaurant10
VALUES (10, 'res10', '종각');
INSERT INTO restaurant10
VALUES (20, 'res20', '혜화');

SELECT *
FROM restaurant10;

INSERT INTO menu10
VALUES (1, 'menu1', 1000, 20);

SELECT *
FROM menu10;

DELETE restaurant10
WHERE r_no = 20;


CREATE TABLE human10
(
    h_no    number(3) PRIMARY KEY,
    h_name  varchar2(10 char) not null,
    h_birth date              not null
);

CREATE TABLE owner10
(
    o_no         number(2) PRIMARY KEY,
    o_ceo        number(2) not null,
    o_restaurant number(3) not null,
    CONSTRAINT o_c_r
        FOREIGN KEY (o_ceo) REFERENCES human10 (h_no) ON DELETE CASCADE,
    FOREIGN KEY (o_restaurant) REFERENCES restaurant10 (r_no) ON DELETE CASCADE
);

INSERT INTO human10
VALUES (50, '김', TO_DATE('1998-06-02'));
INSERT INTO owner10
VALUES (1, 50, 10);

SELECT *
FROM owner10;

DELETE restaurant10
WHERE r_no = 10;
DELETE human10
WHERE h_no = 50;