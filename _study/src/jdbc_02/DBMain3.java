package jdbc_02;

import java.sql.*;
import java.util.Scanner;

public class DBMain3 {
    public static void main(String[] args) throws SQLException {
        // snack 과자 추가하기
        Connection con = null;
        Statement st = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";

        Scanner sc = new Scanner(System.in);

        System.out.print("과자명: ");
        String name = sc.nextLine();
        System.out.print("회사명: ");
        String company = sc.nextLine();
        System.out.print("무게: ");
        double weight = sc.nextDouble();
        System.out.print("가격: ");
        int price = sc.nextInt();

        String sql = "INSERT INTO snack VALUES(snack_seq.nextval, '%s', '%s', %f, %d, SYSDATE)".formatted(name, company, weight, price);

        try {
            con = DriverManager.getConnection(url, "c##sw1004", "sw1004");
            st = con.createStatement();
            int row = st.executeUpdate(sql);


            if (row > 0) {
                System.out.println("SUCCESS");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            st.close();
            con.close();

            System.out.println("Closing connection");
        }

        sc.close();
    }
}
