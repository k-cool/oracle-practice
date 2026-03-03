package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class UpdateMain2 {
    static Map<String, String> envMap;

    /*
        1. 전체 리스트업

        2. PK를 입력하면 -> 나이를 수정

        3. ㅇㅇ 님의 나이가 ㅇㅇ 살로 수정되었습니다.
    */

    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");

        Scanner sc = new Scanner(System.in);

        String sql = "UPDATE STUDENT_DB SET S_AGE = ? WHERE S_NO = ?";

        try (
                Connection con = DriverManager.getConnection(envMap.get("DB_URL"), envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
                PreparedStatement pst = con.prepareStatement(sql)
        ) {
            HashMap<Integer, ArrayList<Object>> map = getStudentList(con);

            if (map.isEmpty()) {
                throw new Exception("NO DATA TO UPDATE");
            }

            System.out.print("수정할 데이터의 PK: ");
            int pk = sc.nextInt();
            pst.setInt(2, pk);

            ArrayList<Object> target = map.get(pk);

            System.out.print("수정할 데이터: ");
            target.forEach((item) -> System.out.print(item + " "));
            System.out.println();

            System.out.print("몇살로 수정할까요?: ");
            int updatedAge = sc.nextInt();

            pst.setInt(1, updatedAge);

            if (pst.executeUpdate() == 1) {
                System.out.printf("%s님의 나이가 %d살에서 %d살로 수정 되었습니다.\n", target.get(1), (int) target.get(2), updatedAge);
            } else {
                System.out.println("FAILED TO UPDATE");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }

    public static HashMap<Integer, ArrayList<Object>> getStudentList(Connection con) throws SQLException {
        String sql = "SELECT * FROM STUDENT_DB";
        HashMap<Integer, ArrayList<Object>> map = new HashMap<>();
        boolean hasData = false;


        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                hasData = true;

                int no = rs.getInt("s_no");
                String name = rs.getString("s_name");
                int age = rs.getInt("s_age");
                String addr = rs.getString("s_addr");

                ArrayList<Object> list = new ArrayList<>();
                list.add(no);
                list.add(name);
                list.add(age);
                list.add(addr);
                map.put(no, list);

                System.out.printf("%d %s %d %s\n", no, name, age, addr);
            }

            if (!hasData) {
                System.out.println("NO RESULTS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("FAIL TO SELECT STUDENT");
        }

        return map;
    }
}
