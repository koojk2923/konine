package com.BookIng.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.BookIng.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.BookIng.util.db.DB;

public class NoticeDAO {

	Connection con =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		List<NoticeVO> list = null;
		
		try {
			con = DB.getConnection();
			String sql = "select no, title, "
					+ "  to_char(startDate, 'yyyy-mm-dd') startDate, "
					+ " to_char(endDate, 'yyyy-mm-dd') endDate, to_char(updateDate, 'yyyy-mm-dd hh24 : mi : ss') "
					+ " updateDate from notice ";
			// 조건에 맞는 쿼리 추가 -> 동적 쿼리 : 넘어오는 pt 변수의 겂으로정한다.
			switch (pageObject.getPeriod()) {
			case "now":
				sql += "where startDate <= sysDate and endDate >=  trunc(SYSDATE) ";

				break;
			case "old":
				sql += "where endDate <  trunc(SYSDATE) ";
				
				break;
			case "res":
				sql += "where startDate > SYSDATE ";
				
				break;
			case "all":
				sql += " ";
				
				break;

			default:
				System.out.println("잘못된 정보가 넘어왔습니다"); // 잘못된 데이터일 경우 현재로 작성한다
				sql += "where startDate <= sysDate and endDate >=  trunc(SYSDATE) ";
				break;
			}
			sql +=" order by updateDate desc, no desc ";
			// 3-1. 순서 번호
			sql = "select rownum rnum, no, title, startDate, endDate, updateDate from (" + sql + ")";
			// 3-2. 페이지에 해당하는 10개 데이터
			sql = "select rnum, no, title, startDate, endDate, updateDate from (" + sql + ")"
					+ " where rnum between ? and ? ";
			
			System.out.println("NoticeDAO,list().sql : " + sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					vo.setUpdateDate(rs.getString("updateDate"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
		return list;
	}

	public long getTotalRaw(PageObject pageObject)  throws Exception  {
		// TODO Auto-generated method stub
		long totalRow = 0;
		try {
			con = DB.getConnection();
			
			String sql = "select count(*) from notice ";

				switch (pageObject.getPeriod()) {
				case "now":
					sql += " where startDate <= sysDate and endDate >=  trunc(SYSDATE) ";

					break;
				case "old":
					sql += " where endDate <  trunc(SYSDATE) ";
					
					break;
				case "res":
					sql += " where startDate > SYSDATE ";
					
					break;
				case "all":
					sql += " ";
					
					break;

				default:
					System.out.println("잘못된 정보가 넘어왔습니다"); // 잘못된 데이터일 경우 현재로 작성한다
					sql += " where startDate <= sysDate and endDate >=  trunc(SYSDATE) ";
					break;
				}
				
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return totalRow;
	}

	public NoticeVO view(long no) throws Exception  {
		// TODO Auto-generated method stub
		NoticeVO vo = null;
		try {
			con = DB.getConnection();
			String sql = " select no, title, content, "
					+ "  to_char(startDate, 'yyyy-mm-dd')startDate, "
					+ "  to_char(endDate, 'yyyy-mm-dd')endDate, "
					+ "  to_char(updateDate, 'yyyy-mm-dd')updateDate "
					+ " from notice where no = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	public int write(NoticeVO vo) throws Exception  {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			con = DB.getConnection();
			String sql = "insert into notice(no, title, content, startDate, endDate) "
					+ " values (notice_seq.nextval, ?, ?, ?, ?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			
			result = pstmt.executeUpdate();
			
			System.out.println((result == 1)?"공지 등록이 되었습니다.":"공지 등록에 실패하셨습니다");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int update(NoticeVO vo) throws Exception  {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			con = DB.getConnection();
			String sql = " update notice set title =? , content = ?, startDate=? , endDate =? "
					+ " where no = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setLong(5, vo.getNo());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) System.out.println("수정 완료");
			else System.out.println(" 수정 안됨");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int delete(long no) throws Exception  {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			con = DB.getConnection();
			String sql = " delete from notice where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
