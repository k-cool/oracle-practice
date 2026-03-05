CREATE TABLE menu
(
    m_no    number(3) PRIMARY KEY,
    m_name  varchar2(20 char) not null,
    m_price number(6)         not null,
    m_place number(3)         not null
);

CREATE SEQUENCE menu_seq;

CREATE TABLE restaurant
(
    r_no    number(3) PRIMARY KEY,
    r_name  varchar2(30 char) not null,
    r_place varchar2(20 char) not null
);

CREATE SEQUENCE restaurant_seq;

insert into menu
values (menu_seq.nextval, '돼지곱창', 10000, 1);
insert into menu
values (menu_seq.nextval, '소곱창', 15000, 1);
insert into menu
values (menu_seq.nextval, '야채곱창', 9000, 2);
insert into menu
values (menu_seq.nextval, '카페라떼', 6000, 3);
insert into menu
values (menu_seq.nextval, '아메리카노', 4000, 3);
insert into menu
values (menu_seq.nextval, '돌체라떼', 7000, 4);
insert into RESTAURANT
values (RESTAURANT_seq.nextval, '황소곱창', '종로');
insert into RESTAURANT
values (RESTAURANT_seq.nextval, '동대문곱창', '동대문');
insert into RESTAURANT
values (RESTAURANT_seq.nextval, '황소곱창', '동대문');
insert into RESTAURANT
values (RESTAURANT_seq.nextval, '스타벅스', '종로');
insert into RESTAURANT
values (RESTAURANT_seq.nextval, '스타벅스', '강남');
insert into RESTAURANT
values (500, '스타벅스', '광화문');

SELECT *
FROM menu;

SELECT *
FROM restaurant;

drop table menu;
drop sequence menu_seq;

DROP SEQUENCE menu_seq;

create sequence menu_seq increment by 1 start with 1;
create sequence restaurant_seq increment by 1 start with 1;

INSERT INTO menu
VALUES (menu_seq.nextval, '체리블로썸', 2000, 500);

INSERT INTO menu
VALUES (menu_seq.nextval, '딸기스무디', 1000, 2);

UPDATE menu
SET m_place =4
WHERE m_no = 8;

DELETE menu
WHERE m_no = 7;


CREATE TABLE human
(
    h_no    number(3) PRIMARY KEY,
    h_name  varchar2(10 char) not null,
    h_birth date              not null
);

CREATE TABLE owner
(
    o_no         number(2) PRIMARY KEY,
    o_ceo        number(2) not null,
    o_restaurant number(3) not null
);

CREATE SEQUENCE human_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE owner_seq INCREMENT BY 1 START WITH 1;


insert into HUMAN
values (human_seq.nextval, '김', to_date('1980-05-05', 'YYYY-MM-DD'));
insert into HUMAN
values (human_seq.nextval, '나', to_date('1985-06-05', 'YYYY-MM-DD'));
insert into HUMAN
values (human_seq.nextval, '박', to_date('1980-07-05', 'YYYY-MM-DD'));
insert into HUMAN
values (human_seq.nextval, '이', to_date('1985-08-05', 'YYYY-MM-DD'));
insert into HUMAN
values (20, '최', to_date('1985-08-05', 'YYYY-MM-DD'));

SELECT *
FROM human;

insert into owner
values (OWNER_seq.nextval, 1, 1);
insert into owner
values (OWNER_seq.nextval, 2, 2);
insert into owner
values (OWNER_seq.nextval, 3, 3);
insert into owner
values (OWNER_seq.nextval, 4, 4);
insert into owner
values (OWNER_seq.nextval, 20, 4);

SELECT *
FROM owner;

INSERT INTO owner
VALUES (OWNER_SEQ.nextval, 4, 500);

-- 내가 알고 있는 맛집의 이름, 위치(매장조회) - 이름 가나다순으로.
SELECT r_name, r_place
FROM restaurant
ORDER BY r_name;

-- 제일 비싼 메뉴 정보
SELECT *
FROM menu
WHERE m_price = (SELECT MAX(m_price) FROM menu);

-- 최연장자
SELECT *
FROM human
WHERE h_birth = (SELECT MIN(h_birth) FROM human);

-- 곱창시리즈 평균가격
SELECT TRUNC(AVG(m_price), 2)
FROM menu
WHERE m_name LIKE '%곱창%';

-- 종로 가게들 정보
SELECT *
FROM restaurant
WHERE r_place = '종로';

-- 제일 싼 메뉴를 파는 식당의 이름과 위치
SELECT r_name, r_place
FROM restaurant
WHERE r_no = (SELECT m_place
              FROM menu
              WHERE m_price = (SELECT MIN(m_price)
                               FROM menu));

-- 동대문에서 먹을 수 있는 음식의 이름, 가격
SELECT m_name, m_price
FROM menu
WHERE m_place IN (SELECT r_no
                  FROM restaurant
                  WHERE r_place = '동대문');

-- 곱창 시리즈는 어디 지역에 가면 먹을 수 있나?(어느가게? 가게 이름과 장소가 궁금)
SELECT r_name, r_place
FROM restaurant
WHERE r_no IN (SELECT m_place
               FROM menu
               WHERE m_name LIKE '%곱창%');

-- 제일 싼 커피를 파는 매장의 이름, 지역
SELECT r_name, r_place
FROM restaurant
WHERE r_no = (SELECT m_place
              FROM menu
              WHERE m_price = (SELECT MIN(m_price)
                               FROM menu
                               WHERE m_name LIKE '??'));
-- 설계오류
/*
    카테고리 컬럼 필요
    - m_type: 1 음식 / 2 음료 / 3 커피
*/

/*
    JOIN
    - 서로 다른 테이블을 연결
    - 관계를 명시할 것
*/

SELECT m_name, m_price, r_name, r_place
from menu
         JOIN restaurant ON m_place = r_no;

-- 스타벅스 종로지점의 메뉴 이름과 가격, 레스토랑 정보
SELECT m_name, m_price, r_no, r_name, r_place
FROM menu,
     restaurant
WHERE m_place = r_no
  AND r_name = '스타벅스'
  AND r_place = '종로';


SELECT m_name, m_price, r.*
FROM menu m,
     restaurant r
WHERE m_place = r_no
  AND r_name = '스타벅스'
  AND r_place = '종로';


-- 불필요한 서브쿼리
SELECT m_name, m_price, r_no, r_name, r_place
FROM menu,
     restaurant
WHERE m_place = r_no
  AND m_place = (SELECT r_no
                 FROM restaurant
                 WHERE r_name = '스타벅스'
                   AND r_place = '종로');

-- 제일 젊은 사장님네 가게의 매장명, 위치, 사장님 이름, 생일 ,메뉴명, 가격
SELECT r_name, r_place, h_name, h_birth, m_name, m_price
FROM menu,
     restaurant,
     human,
     owner
WHERE m_place = r_no
  AND o_ceo = h_no
  AND o_restaurant = r_no
  AND h_birth = (SELECT MAX(h_birth) FROM human);

SELECT *
FROM human
WHERE h_birth = (SELECT MAX(h_birth)
                 FROM human);


-- 최연장자 아저씨네 메뉴명, 메뉴 가격
SELECT m_name, m_price
FROM menu,
     human,
     owner
WHERE m_place = o_restaurant
  AND h_no = o_ceo
  AND h_birth = (SELECT MIN(h_birth)
                 FROM human);

-- 황소곱창 가게의 전체 메뉴명, 가격, 매장 위치
SELECT m_name, m_price, r_place
FROM menu,
     restaurant
WHERE m_place = r_no
  AND r_name = '황소곱창';

-- 제일 싼거 파는 매장명, 위치, 메뉴명, 가격
SELECT r_name, r_place, m_name, m_price
FROM menu,
     restaurant
WHERE m_place = r_no
  AND m_price = (SELECT MIN(m_price) FROM menu);


-- 돼지곱창 가격 인상(13000원으로)
UPDATE menu
SET m_price= 13000
WHERE m_name = '돼지곱창';
-- WHERE m_no = 1;

-- 제일 싼 메뉴 무료 이벤트 진행
UPDATE menu
SET m_price = 0
WHERE m_price = (SELECT MIN(m_price) FROM menu);

-- 메뉴 전체 평균가보다 비싼 메뉴 10% 할인
UPDATE menu
SET m_price = m_price * 0.9
WHERE m_price > (SELECT AVG(m_price) from menu);

-- 동대문 지역 메뉴를 1000원 인상
UPDATE menu
SET m_price = m_price + 1000
WHERE m_place IN (SELECT r_no FROM restaurant WHERE r_place = '동대문');

-- 커피중 '라떼' 종류 가격을 500원 인상
UPDATE menu
SET m_price = m_price + 500
WHERE m_name LIKE '%라떼%';

-- '라떼' 종류 메뉴 다 삭제
DELETE menu
WHERE m_name LIKE '%라떼%';

-- 강남 스벅의 메뉴 다 삭제
DELETE menu
WHERE m_place = 5;

-- 강남 스벅 폐점
DELETE restaurant
WHERE r_no = 5;

-- 가게 이름에 '곱창' 들어간 매장의 메뉴를 다 삭제
DELETE menu
WHERE m_place = (SELECT r_no
                 FROM restaurant
                 WHERE r_name LIKE '%곱창%');

--- 조회용 ---
SELECT *
FROM restaurant;

SELECT *
FROM menu;

SELECT *
FROM human;

SELECT *
FROm owner;