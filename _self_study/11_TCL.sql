-- TCL: Transaction Control Language

ALTER SESSION DISABLE PARALLEL DML;

CREATE TABLE DEPT_TCL
AS
SELECT *
FROM DEPT;

SELECT *
FROM DEPT_TCL;

INSERT INTO DEPT_TCL
VALUES (50, 'DATABASE', 'SEOUL');

UPDATE DEPT_TCL
SET LOC = 'BUSAN'
WHERE DEPTNO = 40;

DELETE
FROM DEPT_TCL
WHERE DNAME = 'RESEARCH';

SELECT *
FROM DEPT_TCL;

ROLLBACK;

/*
    LOCK
    - row level lock: 행 단위로 조작할때 해당 행만 락을 거는것. where절이 없으면 전체 데이터에 락이 걸림.
    - table level log: 데이터가 변경되는 경우 해당 테이블의 DDL로 테이블 구조를 변경할 수 없음.

    => SQL구문에 따라서 일부 데이터만 락이 걸릴수도 있고 테이블 전체가 락이 걸릴 수도 있다.

    * 스레드, 커넥션 전략에 따른 세션상태
    - 공유 커넥션 사용     	1	        1	트랜잭션 충돌 가능, 스레드 간 동기화 필요
    - 스레드별 커넥션 생성   	5       	5	안전, 각 스레드는 독립 트랜잭션, 성능 저하 가능
    - 커넥션 풀 사용	        N(풀 크기)	N	스레드가 필요할 때 커넥션 빌려 쓰고 반환, 효율적
*/

