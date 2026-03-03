package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionMain {
    static Map<String, String> envMap;

    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");
        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";

        Connection con = null;
        PreparedStatement pst = null;

        String sql = "INSERT INTO db_test VALUES (db_test_seq.nextval, ?, ?)";

//        Scanner sc = new Scanner(System.in);
//        System.out.println("이름: ");
//        String name = sc.nextLine();
//        System.out.println("나이: ");
//        int age = sc.nextInt();


        try {
            con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            pst = con.prepareStatement(sql);

            System.out.println("CONNECTION IS OK");

//            int row = pst.executeUpdate();
//
//            if (row == 1) {
//                System.out.println("SUCCESS");
//            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                pst.close();
                con.close();

                System.out.println("Closing connection");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
