package com.BookIng.grade.service;

import com.BookIng.grade.dao.GradeDAO;
import com.BookIng.grade.vo.GradeVO;

public class GradeWrtieService {
public int service(GradeVO vo) throws Exception{
	GradeDAO dao = new GradeDAO();
	return dao.write(vo);
	
} 
}
