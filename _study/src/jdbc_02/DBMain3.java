package jdbc_02;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class DBMain3 {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        // snack 과자 추가하기
        Connection con = null;
        PreparedStatement st = null;

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

//        String sql = "INSERT INTO snack VALUES(snack_seq.nextval, '%s', '%s', %f, %d, SYSDATE)".formatted(name, company, weight, price);
        String sql = "INSERT INTO snack VALUES(snack_seq.nextval, ?, ?, ?, ?, SYSDATE)";

        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
            st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, company);
            st.setDouble(3, weight);
            st.setInt(4, price);

            int row = st.executeUpdate();

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
