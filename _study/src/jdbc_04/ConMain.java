package jdbc_04;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class ConMain {
    static Map<String, String> envMap;
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SQLException {
        envMap = EnvLoader.loadEnv(".env");

        connect();

        CoffeeSelect cs = new CoffeeSelect(con);
//        CoffeeSelect.printList(cs.coffeeMap);

        CoffeeInsert ci = new CoffeeInsert(con, sc);
        ci.insert();

        CoffeeUpdate cu = new CoffeeUpdate(con, sc, cs);


        try {
            con.close();
            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void connect() {
        try {
            con = DriverManager.getConnection(envMap.get("DB_URL"), envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
