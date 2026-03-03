package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class SelectMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "SELECT * FROM STUDENT_DB WHERE S_NAME = ?";

        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 사람?: ");
        String nameInput = sc.nextLine();

        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
            pst = con.prepareStatement(sql);

            pst.setString(1, nameInput);

            rs = pst.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("s_no");
                String name = rs.getString("s_name");
                int age = rs.getInt("s_age");
                String addr = rs.getString("s_addr");

                System.out.printf("%d %s %d %s\n", no, name, age, addr);
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
