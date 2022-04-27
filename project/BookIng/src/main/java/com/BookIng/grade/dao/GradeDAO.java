package com.BookIng.grade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.BookIng.grade.vo.GradeVO;
import com.BookIng.util.db.DB;

import java.sql.PreparedStatement;

public class GradeDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public List<GradeVO> list() throws Exception {
		List<GradeVO> list = null;
		try {
			// 1.2.
			con = DB.getConnection();
			// 3. sql
			String sql = "select gradeNo, gradeName from grade order by gradeNo";
			// 4.
			pstmt = con.prepareStatement(sql);
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<GradeVO>();
					GradeVO vo = new GradeVO();
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));

					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int write(GradeVO vo) {
		int result = 0;
		try {
			// 1.2.
			con = DB.getConnection();
			// 3. sql
			String sql = "INSERT INTO grade(gradeNo, gradeName) VALUES(?, ?)";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getGradeName());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("GradeDAO.write() - 등급 등록완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int update(GradeVO vo) {
		int result = 0;
		try {
			// 1.2.
			con = DB.getConnection();
			// 3. sql
			String sql = "UPDATE grade set gradeName = ? where gradeNo =?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getGradeName());
			pstmt.setInt(2, vo.getGradeNo());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("GradeDAO.update() - 등급명 수정완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delete(int gradeNo) {
		int result = 0;
		try {
			con = DB.getConnection();
			String sql = "delete from grade where gradeNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gradeNo);
			result = pstmt.executeUpdate();
			System.out.println("GradeDAO.delete() - 등급 데이터가 삭제되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
