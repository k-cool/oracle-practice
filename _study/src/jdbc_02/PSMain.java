package jdbc_02;

import utils.EnvLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PSMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "INSERT INTO db_test VALUES (db_test_seq.nextval, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null; // SQL Injection 방어하기 위해서도 사용

        Scanner sc = new Scanner(System.in);

        System.out.println("이름: ");
        String name = sc.nextLine();
        System.out.println("나이: ");
        int age = sc.nextInt();


        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setInt(2, age);

            int row = pst.executeUpdate();

            if (row == 1) {
                System.out.println("SUCCESS");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                // 사용 역순으로 닫아주기
                pst.close();
                con.close();

                System.out.println("Closing connection");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
