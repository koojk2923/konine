package com.BookIng.grade.service;

import com.BookIng.grade.dao.GradeDAO;

public class GradeDeleteService {
	public int service(int gradeNo) throws Exception{
		GradeDAO dao = new GradeDAO();
		return dao.delete(gradeNo);
		
	} 
	}

