package jdbc_01;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class JDBCMain2 {
    static Map<String, String> envMap;

    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");

        /*
         * DB => 껍데기 3종
         * - interface -> connection, statement, resultSet
         */

        String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
//		String sql = "SELECT s_no, s_name, s_price FROM snack";
        String sql = "SELECT * FROM snack";

        try {

            Connection con = DriverManager.getConnection(url, envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int no = rs.getInt("s_no");
                String name = rs.getString("s_name");
                int price = rs.getInt("s_price");

                System.out.printf("%s %s %d\n", no, name, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
