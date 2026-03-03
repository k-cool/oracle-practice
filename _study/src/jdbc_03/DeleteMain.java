package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class DeleteMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        Connection con = null;
        PreparedStatement pst = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
//        String sql = "DELETE STUDENT_DB WHERE S_NO = ?";
        String sql = "DELETE STUDENT_DB WHERE S_NAME = ?";

        Scanner sc = new Scanner(System.in);

//        System.out.print("삭제할 no?: ");
//        int no = sc.nextInt();
        System.out.print("삭제할 사람이름?: ");
        String name = sc.nextLine();


        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
            pst = con.prepareStatement(sql);

            pst.setString(1, name);

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
