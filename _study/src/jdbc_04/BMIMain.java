package jdbc_04;

import utils.EnvLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BMIMain {
    static Map<String, String> envMap;
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, ArrayList<Object>> dataMap = new HashMap<>();

    static String name;
    static double height;
    static double weight;
    static double bmi;
    static String status = "NORMAL";


    /*
        이름, 키, 체중, bmi, status

        1. DB 구축
        2. 검사이력 조회
        3. 검사실시

    */
    public static void main(String[] args) throws IOException {
        envMap = EnvLoader.loadEnv(".env");

        connect();

        fetchData();

        printList();


        System.out.print("1. 신규 검사 2. 종료 -> ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                // 검사
                inputInfo();
                calcBMI();
                calcStatus();
                showResult();

                // 결과 저장
                insertData();

                break;

            case 2:
                return;

            default:
                break;
        }

    }

    public static void inputInfo() {
        System.out.print("이름: ");
        name = sc.next();
        System.out.print("키: ");
        height = sc.nextDouble();
        System.out.print("몸무게: ");
        weight = sc.nextDouble();
    }

    public static void calcBMI() {
        if (name.isEmpty() || height == 0 || weight == 0) {
            System.out.println("진단 정보를 입력해주세요.");
            return;
        }

        double rawBmi = weight / ((height / 100) * (height / 100));

        bmi = Math.floor(rawBmi * 10) / 10;
    }

    public static void calcStatus() {
        if (bmi == 0) {
            System.out.println("bmi를 계산해주세요.");
        }

        if (bmi < 18.5) {
            status = "LOW";
        } else if (bmi < 23) {
            status = "NORMAL";
        } else {
            status = "HIGH";
        }
    }

    public static void showResult() {
        System.out.printf("당신의 BMI는 %.1f이며 %s입니다.\n", bmi, getStatusText());
    }

    public static String getStatusText() {
        return switch (status) {
            case "LOW" -> "저체중";
            case "NORMAL" -> "정상체중";
            case "HIGH" -> "과체중";
            default -> "정상체중";
        };
    }

    // DB
    public static void insertData() {
        String sql = "INSERT INTO bmi VALUES (bmi_seq.nextval, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setString(1, name);
            pst.setDouble(2, height);
            pst.setDouble(3, weight);
            pst.setDouble(4, bmi);
            pst.setString(5, status);

            int row = pst.executeUpdate();

            if (row > 0) {
                System.out.println("진단 결과가 정상적으로 저장되었습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fetchData() {
        String sql = "SELECT * FROM BMI";
        boolean hasData = false;

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                hasData = true;

                int no = rs.getInt("b_no");
                String name = rs.getString("b_name");
                double height = rs.getDouble("b_height");
                double weight = rs.getDouble("b_weight");
                double bmi = rs.getDouble("b_bmi");
                String status = rs.getString("b_status");

                ArrayList<Object> list = new ArrayList<>();

                list.add(no);
                list.add(name);
                list.add(height);
                list.add(weight);
                list.add(bmi);
                list.add(status);

                dataMap.put(no, list);
            }

            if (!hasData) {
                System.out.println("NO RESULTS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printList() {
        System.out.println("=== 검사 이력 ===");

        for (Map.Entry<Integer, ArrayList<Object>> entry : dataMap.entrySet()) {
            String row = "";

            for (int i = 0; i < entry.getValue().size(); i++) {
                row += " " + entry.getValue().get(i);
            }

            System.out.println(row);

        }

        System.out.println("================");
    }

    public static void connect() {
        try {
            con = DriverManager.getConnection(envMap.get("DB_URL"), envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
