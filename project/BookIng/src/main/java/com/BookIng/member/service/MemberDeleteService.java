package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;

public class MemberDeleteService  {
	public int service(MemberVO vo) throws Exception {
		//생성하고 호출한다
		MemberDAO dao = new MemberDAO();
		return dao.delete(vo);
	}

}
