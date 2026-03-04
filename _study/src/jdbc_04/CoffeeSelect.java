package jdbc_04;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoffeeSelect {
    public Connection con;
    public HashMap<Integer, ArrayList<Object>> coffeeMap = new HashMap<>();

    /*
        모든 커피 조회
    */

    public CoffeeSelect(Connection con) {
        this.con = con;
        fetchCoffees();
    }

    public void fetchCoffees() {
        String sql = "SELECT * FROM COFFEE_TEST";
        boolean hasData = false;

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                hasData = true;

                int no = rs.getInt("c_no");
                String name = rs.getString("c_name");
                int age = rs.getInt("c_price");
                String origin = rs.getString("c_origin");

                ArrayList<Object> list = new ArrayList<>();

                list.add(no);
                list.add(name);
                list.add(age);
                list.add(origin);
                coffeeMap.put(no, list);
            }

            if (!hasData) {
                System.out.println("NO RESULTS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printList(HashMap<Integer, ArrayList<Object>> coffeeMap) {
        System.out.println("=== 커피 메뉴 ===");

        for (Map.Entry<Integer, ArrayList<Object>> entry : coffeeMap.entrySet()) {
            String row = "";

            for (int i = 0; i < entry.getValue().size(); i++) {
                row += " " + entry.getValue().get(i);
            }

            System.out.println(row);

        }

        System.out.println("=============");
    }
}
