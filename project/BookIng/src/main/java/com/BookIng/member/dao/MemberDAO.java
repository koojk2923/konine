package com.BookIng.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.BookIng.member.util.db.DB;
import com.BookIng.member.vo.LoginVO;
import com.BookIng.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public class MemberDAO {

	Connection con; //= null;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public List<MemberVO> list(PageObject pageObject) throws Exception{
		//System.out.println("MemberDAO.list()");
		List<MemberVO> list = null;
		//예외 처리
				try {
					//1.2
					con = DB.getConnection();
					//3.sql 원본 데이터
					String sql = "SELECT m.id, m.name, m.birth, m.tel, m.gradeNo, g.gradeName, "
							+ " to_char(m.conDate, 'yyyy-mm-dd') conDate, m.photo "
							+ " FROM member m, grade g "
							+ " WHERE m.gradeNo = g.gradeNo "
							+ " ORDER BY m.id ";
					//3-2 순서번호
					sql = " SELECT rownum rnum, id, name, birth, tel, gradeNo, gradeName, "
							+ " conDate, photo  FROM ( " + sql + " )";
					//3-3. 페이지에 해당되는 10개 데이터
					sql = " SELECT rnum, id, name, birth, tel, gradeNo, gradeName, "
							+ " conDate, photo  FROM ( " + sql + " ) "
							+ " where rnum between ? and ? " ;
					
					//4. 실행객체
					pstmt = con.prepareStatement(sql);
					pstmt.setLong(1, pageObject.getStartRow());
					pstmt.setLong(2, pageObject.getEndRow());
					
					
					//5. 실행 (select는 executeQuery)
					rs = pstmt.executeQuery();
					
					//6. 표시 또는 담기
					if (rs != null) {
						while(rs.next()) {
							//만약에 데이터를 담을 list가 null이면 못담기 때문에 한번은 생성해야 한다.
							if(list == null) list = new ArrayList<MemberVO>();
							//실제적인 데이터를 담을 객체를 생성
							MemberVO vo = new MemberVO();
							//Setter를 이용해서 데이터 담기
							vo.setId(rs.getString("id"));
							vo.setName(rs.getString("name"));
							vo.setBirth(rs.getString("birth"));
							vo.setTel(rs.getString("tel"));
							vo.setGradeNo(rs.getInt("gradeNo"));
							vo.setGradeName(rs.getString("gradeName"));
							vo.setConDate(rs.getString("conDate"));
							vo.setPhoto(rs.getString("photo"));
							
							//vo를 list에 담기
							list.add(vo);
						}				
							
					}//end of if (rs != null)
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} //end of try
				
				System.out.println("MemberDAO.list().list -" + list);
				
				return list;
				
			}

	public long getTotalRow(PageObject pageObject) {
		// TODO Auto-generated method stub
		long totalRow = 0;
		
		try {
			// 1. 드라이버가 있는지 확인하고 클래스의 정보를 로딩(static)해야만 한다. 
			//-> 서버가 돌아갈때 맨처음 한번만 하면 된다.			
			// 2. 서버 연결 - 서버, 사용자 정보
			con = DB.getConnection();
			// 3. 실행할 SQL을 작성
			// ?g 한개는 데이터 한개를 의미한 실행전에 데이터와 바꿔치기를 한다. -> 데이터 세팅
			String sql = "select count(*) from member";
			//System.out.println("3. 실행할 SQL 문장 - " +sql);

			// 4. 작성된 쿼리를 실행하기 위한 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
						 
			// 5. 실행 - select: executeQuery() 
			// insert, update, delete: executeUpdate()
			rs = pstmt.executeQuery();
		    //6. 표시 또는 데이터 담기
		    if (rs != null && rs.next()) {
		    	totalRow = rs.getLong(1);
						
		    }} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					} finally {
						try {
							//7. 사용한 객체 닫기
							DB.close(con, pstmt, rs);
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
		
	
		
		
		return totalRow;
	
	
}

	
	
	public MemberVO view(String id) {
		System.out.println(id);
		// TODO Auto-generated method stub
		MemberVO vo = null;
		
		try {
			// 1. 드라이버가 있는지 확인하고 클래스의 정보를 로딩(static)해야만 한다. 
			//-> 서버가 돌아갈때 맨처음 한번만 하면 된다.			
			// 2. 서버 연결 - 서버, 사용자 정보
			con = DB.getConnection();
			// 3. 실행할 SQL을 작성
			// ?g 한개는 데이터 한개를 의미한 실행전에 데이터와 바꿔치기를 한다. -> 데이터 세팅
			String sql = "SELECT m.id, m.name, m.gender, m.birth, m.tel, m.email, m.regDate, "
					+ " m.conDate, m.status, m.gradeNo, g.gradeName, m.photo "
					+ " FROM member m, grade g "
					+ " WHERE (id = ?) AND (m.gradeNo = g.gradeNo) ";
			//System.out.println("3. 실행할 SQL 문장 - " +sql);

			// 4. 작성된 쿼리를 실행하기 위한 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			//set 데이터 타입(?의 순서번호, 데이터 타입의 데이터)
			pstmt.setString(1, id); //다른 글 번호 바꿀때 사용. 1에서 2번으로 글 바꿈
			//System.out.println("4. 실행 객체 설정 완료: " + pstmt);
						 
			// 5. 실행 - select: executeQuery() 
			// insert, update, delete: executeUpdate()
			rs = pstmt.executeQuery();
		    //System.out.println("5. 실행 완료: " + rs);
						 
		    //6. 표시 또는 데이터 담기
			
		    if (rs != null && rs.next()) {
		    	vo = new MemberVO();
		    	vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoto(rs.getString("photo"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				//rs.get데이터타입("select의 데이터 이름")
						
		    }} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					} finally {
						try {
							//7. 사용한 객체 닫기
							DB.close(con, pstmt, rs);
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
		
	
		System.out.println(vo);
		
		return vo;
	
	
}

	public int write(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		//예외 처리
		try {
			//1.2
			con = DB.getConnection();
			//3
			String sql = "INSERT INTO member (id, pw, name, gender, birth, tel, email, photo) "
					+ " VALUES (?, ?, ? ,?, ?, ?, ?, ?)";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			//5
			result = pstmt.executeUpdate();
			//6
			System.out.println("회원가입 완료");			
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7. 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		return result;
		
		
		
		
	}
	
	public int update(MemberVO vo) throws Exception{
		System.out.println("MemberDAO.update().vo - " + vo);
		int result = 0;
		// TODO Auto-generated method stub
		
		try {
			//1.2. 연결
			con = DB.getConnection();
			//3. sql
			String sql = "UPDATE member set name = ?, gender = ?, "
					+ " birth = ?, tel = ?, "
					+ " email = ? "
					+ " where id = ? AND pw = ? ";
			//4. 실행객체&데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getBirth());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getId());
			pstmt.setString(7, vo.getPw());
			
			//5. 실행
			// result: 1 - 수정 완료, result: 0 - 아이디나 비밀번호가 틀림.
			result = pstmt.executeUpdate();
			
			//6.표시 저장
			if (result == 1)
				System.out.println("MemberDAO.update() - 회원정보 수정 완료");
			else  
				System.out.println("MemberDAO.update() - 회원정보 수정 실패: 아이디나 비밀번호 틀림");
				//throw new Exception("아디나 비밀번호를 확인해 주세요.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e);
		}	finally {
			try {
				
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		
		
		return result;
		
	}

	public int delete(MemberVO vo ) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			//1.2.
			con = DB.getConnection();
			//3.
			String sql = "DELETE FROM member "
					+ " WHERE id = ? AND pw = ? AND tel = ?";
			//4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getTel());
			//5.
			result = pstmt.executeUpdate();
			//6.
			if (result == 1) System.out.println("회원 탈퇴/삭제 성공");
			else System.out.println("삭제 오류 - 글번호 없음 id= " + vo.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		try {
			//7.
			DB.close(con, pstmt);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		}
		
		//
		return result;
		
		
		
	}
	//조회수 1 증가: list에서 -> view
	public void increase(long no) throws Exception {
		// TODO Auto-generated method stub
		try {
			//1.2.
			con = DB.getConnection();
			//3.
			String sql = "update member set hit = hit + 1 where no = ?";
			//4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,no);
			//5.
			pstmt.executeUpdate();
			//6.
			System.out.println(no + "번 글의 조회수가 1증가 되었습니다.");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		
		
	}
	public LoginVO login(LoginVO inVO) throws Exception {
		// TODO Auto-generated method stub
		LoginVO vo = null;
		
		try {
			// 1. 드라이버가 있는지 확인하고 클래스의 정보를 로딩(static)해야만 한다. 
			//-> 서버가 돌아갈때 맨처음 한번만 하면 된다.			
			// 2. 서버 연결 - 서버, 사용자 정보
			con = DB.getConnection();
			// 3. 실행할 SQL을 작성
			// ?g 한개는 데이터 한개를 의미한 실행전에 데이터와 바꿔치기를 한다. -> 데이터 세팅
			String sql = "SELECT m.id, m.name, m.gradeNo, g.gradeName, m.photo "
					+ " FROM member m, grade g "
					+ " WHERE (id = ? AND pw = ? ) AND (m.gradeNo = g.gradeNo) ";
			//System.out.println("3. 실행할 SQL 문장 - " +sql);

			// 4. 작성된 쿼리를 실행하기 위한 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			//set 데이터 타입(?의 순서번호, 데이터 타입의 데이터)
			pstmt.setString(1, inVO.getId()); //다른 글 번호 바꿀때 사용. 1에서 2번으로 글 바꿈
			pstmt.setString(2, inVO.getPw()); 
			//System.out.println("4. 실행 객체 설정 완료: " + pstmt);
						 
			// 5. 실행 - select: executeQuery() 
			// insert, update, delete: executeUpdate()
			rs = pstmt.executeQuery();
		    //System.out.println("5. 실행 완료: " + rs);
						 
		    //6. 표시 또는 데이터 담기
			
		    if (rs != null && rs.next()) {
		    	vo = new LoginVO();
		    	vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setPhoto(rs.getString("photo"));
				//rs.get데이터타입("select의 데이터 이름")
						
		    }} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					} finally {
						try {
							//7. 사용한 객체 닫기
							DB.close(con, pstmt, rs);
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
		
	
		System.out.println(vo);
		
		return vo;
	
	
}
	
	public int grade(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
	int result = 0;
	
	try {
		con = DB.getConnection();
		String sql = "update member set gradeNo = ? "
				+ " where id = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vo.getGradeNo());
		pstmt.setString(2, vo.getId());
		
		result = pstmt.executeUpdate();
		if (result == 1) System.out.println("수정 완료.");
		else {
			System.out.println("수정불가 - ID나 PW가 틀림.");
			throw new Exception("아이디나 비밀번호를 확인해주십시오.");
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
			throw new Exception("아이디를 확인하십시오." + e);
		}
	}return result;
}

	public int changePhoto(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			//1.2.
			con = DB.getConnection();
			//3.
			String sql = "update member set photo = ? where id = ?";
			//4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,vo.getPhoto());
			pstmt.setString(2,vo.getId());
			//5.
			result = pstmt.executeUpdate();
			//6.
			System.out.println("회원님의 사진 정보가 변경었습니다.");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		
		return result;
	}

}//end of MemberDAO class