package com.BookIng.member.util.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	//DB정보 - DB. DRIVER
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String UID = "java03";
	private final static String UPW = "java03";
	
	//checkDriver가 true면 있는거, false이면 없는거
	private static boolean checkDriver = false;
	
	//static 초기화 블록
	//처음 실행되는곳(main())에서 Class.forName(DB)코드를 넣어 줘야 시행된다.
	static {
		try {
			Class.forName(DRIVER);
			//드라이버가 있는 경우
			checkDriver = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	//연결 객체를 받아가는 메소드 - getConnection()
	public static Connection getConnection() throws SQLException {
		if(checkDriver) return DriverManager.getConnection(URL, UID, UPW);
		throw new SQLException("DB 드라이버가 없습니다.");
		
	}
		
	//2개의 객체 닫기 - con, pstmt
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if (con != null) con.close();
		if (pstmt != null) pstmt.close();
	}
	//3개의 객체 닫기 - con, pstmt, rs
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close (con, pstmt);
		if (rs != null) rs.close();
		
	}
	//2개의 close (같은 클래스에 같은 메소드) 이걸 overload개념  다르게 행동?
}

