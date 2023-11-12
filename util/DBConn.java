package joongo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private static Connection con;
	private DBConn() {}		//1.외부에서 접근할 수 없는 기본생성자 작성
	
	public static Connection getConnection() { 
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "tr";
		String password = "1234";
		
		if( con == null ) { 		//2.Connection 객체가 null인 경우에만 객체를 생성하고 반환하는 공유 메서드
		try {
				Class.forName(driver);	// 드라이버 로딩
				con = DriverManager.getConnection(url, username, password);   
				System.out.println("con ok");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 				
		}
		return con;
	}
	
	//3.Statement 객체를 매개변수로 받아서 닫는 공유 메서드 close() 작성 (접근 제한 X )
	public static void close(Statement stmt){ 
		try {
			if (stmt != null)			
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//4.PreparedStatement 객체를 매개변수로 받아서 닫는 공유 메서드 close() 작성 (접근 제한 X )
	public static void close(PreparedStatement pstmt) { 
		try {
			if (pstmt != null)			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//5.PreparedStatement, ResultSet 객체를 매개변수로 받아서 
	//   닫는 공유 메서드 close() 작성 (접근 제한 X )
	public static void close(PreparedStatement pstmt, ResultSet rs) { 
		try {
			if (rs != null)					rs.close();
			if (pstmt != null)			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//6.매개변수를 받지 않고 Connection 클래스의 객체 con을 종료하는
	//  공유 메서드 close() 작성
	public static void close() { 
		try {
			if (con != null)			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
