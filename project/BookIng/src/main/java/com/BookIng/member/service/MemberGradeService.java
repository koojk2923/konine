package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;

public class MemberGradeService {
	
	public int service(MemberVO vo) throws Exception {
		MemberDAO dao = new MemberDAO();
		return dao.grade(vo);
		
	}

}
