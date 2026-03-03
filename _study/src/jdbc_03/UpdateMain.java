package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class UpdateMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        // 사람이름 입력하면 나이를 수정
        Connection con = null;
        PreparedStatement pst = null;

        Scanner sc = new Scanner(System.in);

        System.out.print("수정할 사람 이름: ");
        String name = sc.next();
        System.out.print("수정할 나이: ");
        int age = sc.nextInt();

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "UPDATE STUDENT_DB SET S_AGE = ? WHERE S_NAME = ?";

        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
            pst = con.prepareStatement(sql);

            pst.setInt(1, age);
            pst.setString(2, name);

            if (pst.executeUpdate() > 0) {
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
