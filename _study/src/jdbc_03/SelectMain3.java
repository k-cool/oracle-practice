package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class SelectMain3 {
    static Map<String, String> envMap;

    /*
        나이조회

        1. 이상 2. 이하
    */

    public static void main(String[] args) throws IOException, SQLException {
        envMap = EnvLoader.loadEnv(".env");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";

        //noinspection SqlDialectInspection
        String sql = "SELECT * FROM STUDENT_DB WHERE 1=1 ";

        Scanner sc = new Scanner(System.in);


        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            System.out.print("1. 나이(이상), 2. 나이(이하): ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sql += "AND S_AGE >= ?";

            } else if (choice == 2) {
                sql += "AND S_AGE <= ?";
            }

            pst = con.prepareStatement(sql);

            System.out.print("나이?: ");
            pst.setInt(1, sc.nextInt());

            rs = pst.executeQuery();

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                int no = rs.getInt("s_no");

                String name = rs.getString("s_name");
                int age = rs.getInt("s_age");
                String addr = rs.getString("s_addr");

                System.out.printf("%d %s %d %s\n", no, name, age, addr);
            }

            if (!hasData) {
                System.out.println("NO RESULTS");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            pst.close();
            con.close();
        }
    }
}
