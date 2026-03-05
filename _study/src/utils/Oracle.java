package utils;

import java.sql.*;
import java.util.Map;

public class Oracle {
    static Map<String, String> envMap;
    public Connection con;
    public PreparedStatement pst;
    public ResultSet rs;

    public Oracle() {
        envMap = EnvLoader.loadEnv(".env");

        try {
            con = DriverManager.getConnection(envMap.get("DB_URL"), envMap.get("DB_USER"), envMap.get("DB_PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void createPreparedStatement(String sql) {
        if (pst != null) return;

        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void executeQuery() {
        if (con == null || pst == null) return;

        try {
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        쿼리를 정상적으로 실행하지 못하면 -1을 반환
    */
    public Integer executeUpdate() {
        if (con == null || pst == null) return -1;

        try {
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeAll() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
