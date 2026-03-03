package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class SelectMain2 {
    static Map<String, String> envMap;

    /*
        실행하면 1. 이름으로 조회, 2. 나이(이상)으로 조회

        1 -> 입력한 이름 포함된 사람들 정보 출력
        2 -> 입력한 나이 이상인 사람들 정보 출력
    */

    public static void main(String[] args) throws IOException, SQLException {
        envMap = EnvLoader.loadEnv(".env");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
        String sql = "";

        Scanner sc = new Scanner(System.in);


        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            System.out.print("1. 이름으로 조회, 2. 나이(이상): ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sql = "SELECT * FROM STUDENT_DB WHERE S_NAME = ?";

                pst = con.prepareStatement(sql);

                System.out.print("검색할 입력을 입력해주세요: ");

                pst.setString(1, sc.next());

            } else if (choice == 2) {
                sql = "SELECT * FROM STUDENT_DB WHERE S_AGE >= ?";

                pst = con.prepareStatement(sql);

                System.out.print("나이(이상)?: ");

                pst.setInt(1, sc.nextInt());
            }

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
