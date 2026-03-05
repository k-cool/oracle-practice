/*
    Sub Query

    - 특수한 몇몇 경우를 제외한 대부분의 서브쿼리에서는 ORDER BY 절을 사용할 수 없다.
    - 메인쿼리의 비교 대상 데이터가 하나라면 서브쿼리의 SELECT절도 같은 자료형인 열을 하나만 지정해야함.
    - 메인쿼리의 연산 행수와 서브쿼리 결과의 행수가 일치해야한다.
    */


SELECT *
FROM EMP
WHERE SAL > (SELECT SAL FROM EMP WHERE ENAME = 'JONES');

/*
    다중행 서브쿼리
    - IN, ANY, SOME, ALL, EXISTS
*/

SELECT *
FROM EMP
WHERE DEPTNO IN (20, 30);

SELECT *
FROM EMP
WHERE SAL IN (

    )