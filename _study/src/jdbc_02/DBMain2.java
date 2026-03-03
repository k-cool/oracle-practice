package jdbc_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMain2 {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
		String sql = "SELECT * FROM db_test";
		
		Connection con = null;
		Statement st = null;
		ResultSet rs =null;
		
		try {
			con = DriverManager.getConnection(url, "c##sw1004", "sw1004");
			
			st = con.createStatement();
			
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int no = rs.getInt("d_no");
				String name = rs.getString("d_name");
				int age = rs.getInt("d_age");
				
				System.out.printf("%s %s %d\n", no, name, age);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			rs.close();
			st.close();
			con.close();
				
			System.out.println("CONNECTION CLOSED");
			
		}
		
	}
}
