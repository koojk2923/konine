package com.BookIng.util.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String UID = "java03";
	private final static String UPW = "java03";
	
	private static boolean checkDiraver = false;
	
	static {
		try {
			Class.forName(DRIVER);
			checkDiraver = true;
			System.out.println("드라이버 확인 완료");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버가 존재하지 않습니다.");
		}
	}
	public static Connection getConnection() throws Exception {
		if(checkDiraver)
			return DriverManager.getConnection(URL, UID, UPW);
		throw new Exception("드라이버가 존재하지 않습니다.");
	}
	
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if(con != null) con.close();
		if(pstmt != null) pstmt.close();
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close(con, pstmt);
		if(rs != null) rs.close();
	}
}	

