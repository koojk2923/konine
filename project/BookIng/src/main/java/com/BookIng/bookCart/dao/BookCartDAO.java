package com.BookIng.bookCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.BookIng.bookCart.vo.BookCartVO;
import com.BookIng.util.db.DB;

public class BookCartDAO {

	// 필요한 객체
	// 전역변수인 경우는 참조형 변수를 선언하면 초기값이 null로 자동 세팅이 되므로 = null;를 안해줘도 됨
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;

	public List<BookCartVO> list(BookCartVO vo) throws Exception {
		List<BookCartVO> list = null;
		// 예외처리
		try {
			// 1. 드라이버 확인, 2. 연결
			// Connection으로 db에 연결한다.
			con = DB.getConnection();
			// 3. sql
			// db데이터를 불러올 sql 쿼리문을 String sql에 입력한다.
			String sql = "select m.id, c.cartNo, b.bookNo, b.cover, b.title, b.writer, "
					+ " b.price, b.genre, GREATEST(c.quantity, 0) quantity, "
					+ " GREATEST(b.price*c.quantity, 0) totalPrice "
					+ " from member m, bookBoard b, bookCart c "
					+ " where (m.id = c.id) and (b.bookNo = c.bookNo) and m.id = ? ";
			// 4. 실행객체
			// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			// 5. 실행
			// executeQuery를 호출하여 sql 쿼리문을 실행한다.
			rs = pstmt.executeQuery();
			// 6. 표시 또는 담기
			// ResultSet에 저장된 결과값이 null이 아닐시 db에 저장된 데이터를 출력한다.
			if (rs != null) {
				// rs에 저장되어있는 데이터를 반복처리 하여 한 행씩 불러온다.
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<BookCartVO>();
					vo = new BookCartVO();
					// setter를 이용해서 데이터 담기
					vo.setId(rs.getString("id"));
					vo.setBookNo(rs.getLong("bookNo"));
					vo.setCartNo(rs.getLong("cartNo"));
					vo.setCover(rs.getString("cover"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setGenre(rs.getString("genre"));
					vo.setPrice(rs.getLong("price"));
					vo.setQuantity(rs.getLong("quantity"));
					vo.setTotalPrice(rs.getLong("totalPrice"));

					// vo를 list에 담기
					list.add(vo);
				}
			} // if문의 끝
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. 닫기
			// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("BookCartDAO.list().list" + list);
		return list;
	}
		
		public List<BookCartVO> masterList(BookCartVO vo) throws Exception {
			List<BookCartVO> list = null;
			// 예외처리
			try {
				// 1. 드라이버 확인, 2. 연결
				// Connection으로 db에 연결한다.
				con = DB.getConnection();
				// 3. sql
				// db데이터를 불러올 sql 쿼리문을 String sql에 입력한다.
				String sql = "select m.id, c.cartNo, b.bookNo, b.cover, b.title, b.writer, "
						+ " b.price, b.genre, GREATEST(c.quantity, 0) quantity, "
						+ " GREATEST(b.price*c.quantity, 0) totalPrice "
						+ " from member m, bookBoard b, bookCart c "
						+ " where (m.id = c.id) and (b.bookNo = c.bookNo) ";
				// 4. 실행객체
				// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
				pstmt = con.prepareStatement(sql);
				// 5. 실행
				// executeQuery를 호출하여 sql 쿼리문을 실행한다.
				rs = pstmt.executeQuery();
				// 6. 표시 또는 담기
				// ResultSet에 저장된 결과값이 null이 아닐시 db에 저장된 데이터를 출력한다.
				if (rs != null) {
					// rs에 저장되어있는 데이터를 반복처리 하여 한 행씩 불러온다.
					while (rs.next()) {
						if (list == null)
							list = new ArrayList<BookCartVO>();
						vo = new BookCartVO();
						// setter를 이용해서 데이터 담기
						vo.setId(rs.getString("id"));
						vo.setBookNo(rs.getLong("bookNo"));
						vo.setCartNo(rs.getLong("cartNo"));
						vo.setCover(rs.getString("cover"));
						vo.setTitle(rs.getString("title"));
						vo.setWriter(rs.getString("writer"));
						vo.setGenre(rs.getString("genre"));
						vo.setPrice(rs.getLong("price"));
						vo.setQuantity(rs.getLong("quantity"));
						vo.setTotalPrice(rs.getLong("totalPrice"));

						// vo를 list에 담기
						list.add(vo);
					}
				} // if문의 끝
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				// 7. 닫기
				// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
				try {
					DB.close(con, pstmt, rs);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		System.out.println("BookCartDAO.list().list" + list);
		return list;
	}

		
	public BookCartVO add(BookCartVO vo) throws Exception {
		int result = 0;
		// 예외처리
		try {

			// 1. 드라이버 확인, 2. 연결
			// Connection으로 db에 연결한다.
			con = DB.getConnection();

			// 3. sql
			// db데이터를 입력할 sql 쿼리문을 String sql에 입력한다.
			String sql = "INSERT INTO bookCart(cartNo, id, bookNo) "
					+ " VALUES(bookCart_seq.NEXTVAL, ?, ?) ";

			// 4. 실행객체
			// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
			pstmt = con.prepareStatement(sql);
			// 각각 데이터를 받아와 입력시킨다.
			pstmt.setString(1, vo.getId());
			pstmt.setLong(2, vo.getBookNo());

			// 5. 실행
			// executeUpdate에 sql 쿼리문을 실행해 데이터를 입력시킨다.
			result = pstmt.executeUpdate();
			System.out.println((result == 1) ? "도서가 등록 되었습니다." : "도서 등록에 실패하였습니다.");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 6. 닫기
				// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public BookCartVO addM(BookCartVO vo) throws Exception {
		int result = 0;
		// 예외처리
		try {

			// 1. 드라이버 확인, 2. 연결
			// Connection으로 db에 연결한다.
			con = DB.getConnection();

			// 3. sql
			// db데이터를 입력할 sql 쿼리문을 String sql에 입력한다.
			String sql = "INSERT INTO bookCart(cartNo, bookNo) "
					+ " VALUES(bookCart_seq.NEXTVAL, ?) ";

			// 4. 실행객체
			// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
			pstmt = con.prepareStatement(sql);
			// 각각 데이터를 받아와 입력시킨다.
			pstmt.setLong(1, vo.getBookNo());

			// 5. 실행
			// executeUpdate에 sql 쿼리문을 실행해 데이터를 입력시킨다.
			result = pstmt.executeUpdate();
			System.out.println((result == 1) ? "도서가 등록 되었습니다." : "도서 등록에 실패하였습니다.");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 6. 닫기
				// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
	
	public BookCartVO view(long bookNo) throws Exception {
		// TODO Auto-generated method stub
		BookCartVO vo = null;
		
		// 예외처리
		try {
		
		// 1. 드라이버 확인, 2. 연결
		// Connection으로 db에 연결한다.
		con = DB.getConnection();
		
		// 3. sql
		// db데이터를 불러올 sql 쿼리문을 String sql에 입력한다.
		String sql = "select bookNo, title, writer, price, cover "
				+ " from bookCart "
				+ " where bookNo = ? ";

		// 4. 실행객체
		// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, bookNo);
		
		// 5. 실행
		// executeQuery를 호출하여 sql 쿼리문을 실행한다.
		rs = pstmt.executeQuery();
		
		// 6. 표시 또는 담기
		// ResultSet에 저장된 결과값이 null이 아닐시 db에 저장된 데이터를 출력한다.
		if(rs != null && rs.next()) {
			// rs에 데이터를 한 행씩 받아온다.
			vo = new BookCartVO();
			vo.setBookNo(rs.getLong("bookNo"));
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setPrice(rs.getLong("price"));
			vo.setCover(rs.getString("cover"));
	}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. 닫기
				// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
				DB.close(con, pstmt, rs);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//		System.out.println("BookBoardDAO.view().vo - " + vo);
		return vo;
	}
	
	public int checkBooks(BookCartVO vo) {
		int result = 0;

		try{
			con = DB.getConnection();
			//sql : id, gno, size, color 모두 만족하는 대상 검색
			String sql ="select * from bookCart "
					+ " where id = ? and bookNo = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setLong(2, vo.getBookNo());
			rs = pstmt.executeQuery();
			if(rs.next()){ //중복상품인 경우
				result = 1;
				//구매수량 수정
			}
			System.out.println("기존의 상품 확인 결과: "+(result==1? "중복상품이 있다":"중복상품이 없다"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int checkBooksM(BookCartVO vo) {
		int result = 0;

		try{
			con = DB.getConnection();
			//sql : id, gno, size, color 모두 만족하는 대상 검색
			String sql ="select * from bookCart "
					+ " where bookNo = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookNo());
			rs = pstmt.executeQuery();
			if(rs.next()){ //중복상품인 경우
				result = 1;
				//구매수량 수정
			}
			System.out.println("기존의 상품 확인 결과: "+(result==1? "중복상품이 있다":"중복상품이 없다"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public BookCartVO countBooks(BookCartVO vo) {
		
		try{
			con = DB.getConnection();
			//sql : id, gno, size, color 모두 만족하는 대상 검색
			String sql ="update bookCart set quantity = quantity + ? "
					+ " where id = ? and bookNo = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookCount());
			pstmt.setString(2, vo.getId());
			pstmt.setLong(3, vo.getBookNo());
			rs = pstmt.executeQuery();
			System.out.println("기존의 상품에 수량 변경완료");
			if(rs.next()){ //중복상품인 경우
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public BookCartVO countBooksM(BookCartVO vo) {
		
		try{
			con = DB.getConnection();
			//sql : id, gno, size, color 모두 만족하는 대상 검색
			String sql ="update bookCart set quantity = quantity + ? "
					+ " where cartNo = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getBookCount());
			pstmt.setLong(2, vo.getCartNo());
			rs = pstmt.executeQuery();
			System.out.println("기존의 상품에 수량 변경완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public int delete(long cartNo) {
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
			try {
				
				// 1. 드라이버 확인, 2. 연결
				// Connection으로 db에 연결한다.
				con = DB.getConnection();
				
				// 3. sql
				// db데이터를 삭제할 sql 쿼리문을 String sql에 입력한다.
				String sql = "delete from bookCart where cartNo = ?";
				
				// 4. 실행객체
				// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, cartNo);
				
				// 5. 실행
				// executeUpdate에 sql 쿼리문을 실행해 데이터를 삭제시킨다.
				result = pstmt.executeUpdate();
				
				if(result >= 1) System.out.println("장바구니에서 해당 상품이 삭제되었습니다.");
				else System.out.println("삭제 실패");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					// 6. 닫기
					// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
					DB.close(con, pstmt);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		return result;
	}
	
	public int deleteCart(BookCartVO vo) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
			try {
				
				// 1. 드라이버 확인, 2. 연결
				// Connection으로 db에 연결한다.
				con = DB.getConnection();
				
				// 3. sql
				// db데이터를 삭제할 sql 쿼리문을 String sql에 입력한다.
				String sql = "delete from bookCart where id = ? ";
				
				// 4. 실행객체
				// Connection으로 sql을 prepareStatement에 전달해 실행객체를 생성한다.
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				
				// 5. 실행
				// executeUpdate에 sql 쿼리문을 실행해 데이터를 삭제시킨다.
				result = pstmt.executeUpdate();
				
				if(result >= 1) System.out.println("결제가 완료되어 장바구니가 삭제되었습니다.");
				else System.out.println("삭제 실패");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					// 6. 닫기
					// 각각 객체에 더 이상 전달될 데이터가 존재하지 않을때 객체를 닫는다.
					DB.close(con, pstmt);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		return result;
	}
}
