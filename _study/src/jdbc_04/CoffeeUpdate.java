package jdbc_04;

import java.sql.Connection;
import java.util.Scanner;

public class CoffeeUpdate {
    public Connection con;
    public Scanner sc;
    public CoffeeSelect cs;
    public int targetNo = 0;
    public int targetCol = 0;

    public CoffeeUpdate(Connection con, Scanner sc, CoffeeSelect coffeeSelect) {
        this.con = con;
        this.sc = sc;
        this.cs = coffeeSelect;
    }

    public void selectTarget() {
        CoffeeSelect.printList(cs.coffeeMap);
        System.out.println("수정할 메뉴의 NO를 입력: ");

        targetNo = sc.nextInt();
    }

    public void selectTargetCol() {
        System.out.println("1. 이름 2. 가격 3. 원두산지");
        System.out.println("수정할 컬럼을 선택: ");
        targetCol = sc.nextInt() + 1;


    }

}
