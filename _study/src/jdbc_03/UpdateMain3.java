package jdbc_03;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class UpdateMain3 {
    static Map<String, String> envMap;

    /*
        이름 검색(포함)

        - "s" 로 검색하면 그게 이름에 포함된 사람들의 나이를 입력받은 값으로 수정
        - sql like %%
    */

    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");

        Scanner sc = new Scanner(System.in);

        String sql = "UPDATE STUDENT_DB SET S_AGE = ? WHERE S_NAME LIKE '%'||?||'%'";

        System.out.print("검색어: ");
        String search = sc.next();

        System.out.print("수정할 나이: ");
        int age = sc.nextInt();

        try (
                Connection con = DriverManager.getConnection(envMap.get("DB_URL"), envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
                PreparedStatement pst = con.prepareStatement(sql);
        ) {
            pst.setInt(1, age);
            pst.setString(2, search);

            int row = pst.executeUpdate();

            if (row > 0) {
                System.out.println("SUCCESS");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
