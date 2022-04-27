package com.BookIng.grade.service;

import java.util.List;

import com.BookIng.grade.dao.GradeDAO;
import com.BookIng.grade.vo.GradeVO;

public class GradeListService {
	public List<GradeVO> service() throws Exception {
		GradeDAO dao = new GradeDAO();
		return dao.list();
	}
}
