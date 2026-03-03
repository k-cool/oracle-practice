package jdbc_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMain {
	public static void main(String[] args) {
		// db -> 3종세트
		/*
		 * db_test
		 * 
		 * select (R) - rs
		 * insert (C), update(U), delete(D) -> rs 필요없음
		 */
		
		String url = "jdbc:oracle:thin:@192.168.0.57:1521:XE";
		String sql = "INSERT INTO db_test VALUES (db_test_seq.nextval, 'test2', 30)";
		Connection con = null;
		Statement st = null;
		
		try {
			con = DriverManager.getConnection(url, "c##sw1004", "sw1004");
			
			st = con.createStatement();
			
			int row = st.executeUpdate(sql);
			
			if(row==1) {
				System.out.println("SUCCESS");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 사용 역순으로 닫아주기
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
