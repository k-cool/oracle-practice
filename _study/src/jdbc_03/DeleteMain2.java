package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class DeleteMain2 {
    static Map<String, String> envMap;

    public static void main(String[] args) throws SQLException, IOException {
        envMap = EnvLoader.loadEnv(".env");

        Scanner sc = new Scanner(System.in);
        Connection con = null;
        PreparedStatement pst = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "DELETE STUDENT_DB WHERE S_NO = ?";

        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            boolean hasData = getStudentList(con);

            if (!hasData) {
                throw new Exception("NO DATA TO DELETE");
            }

            pst = con.prepareStatement(sql);

            System.out.print("삭제할 no?: ");
            pst.setInt(1, sc.nextInt());

            System.out.println(pst.executeUpdate() > 0 ? "SUCCESS" : "FAILURE");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pst.close();
            con.close();
        }

        sc.close();
    }

    public static boolean getStudentList(Connection con) throws SQLException {
        String sql = "SELECT * FROM STUDENT_DB";
        boolean hasData = false;


        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

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

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("FAIL TO SELECT STUDENT");
        }

        return hasData;
    }
}
