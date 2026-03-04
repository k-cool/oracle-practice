package jdbc_04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
    유저가 입력하는 커피 데이터 삽입
*/

public class CoffeeInsert {
    public Connection con;
    public Scanner sc;
    public String sql = "INSERT INTO coffee_test VALUES(coffee_test_seq.nextVal, ?, ?, ?)";
    public String i_name = "";
    public int i_price = 0;
    public String i_origin = "";

    public CoffeeInsert(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public void inputData() {
        System.out.print("커피 이름을 입력: ");
        i_name = sc.next();
        System.out.print("가격 입력: ");
        i_price = sc.nextInt();
        System.out.print("원두산지 입력: ");
        i_origin = sc.next();

        insert();
    }

    public void insert() {
        if (i_name.isEmpty() || i_price <= 0 || i_origin.isEmpty()) {
            System.out.println("추가할 데이터를 입력해주세요.");
            inputData();
            return;
        }

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, i_name);
            pst.setInt(2, i_price);
            pst.setString(3, i_origin);
            int row = pst.executeUpdate();

            if (row > 0) {
                System.out.println(row + "건의 데이터가 추가되었습니다.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            initInput();
        }
    }

    public void initInput() {
        i_name = "";
        i_price = 0;
        i_origin = "";
    }
}
