package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class InsertMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        Connection con = null;
        PreparedStatement pst = null;

        Scanner sc = new Scanner(System.in);

        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("나이: ");
        int age = sc.nextInt();
        System.out.print("주소: ");
        sc.nextLine();
        String addr = sc.nextLine();

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "INSERT INTO STUDENT_DB VALUES (STUDENT_DB_SEQ.nextval, ?, ?, ?)";

        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
            pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, addr);

            if (pst.executeUpdate() == 1) {
                System.out.println("SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
            con.close();

        }

        sc.close();
    }
}
