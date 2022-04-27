package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;

public class MemberUpdateService {
	public int service(MemberVO vo) throws Exception{
		//생성하고 호출
		MemberDAO dao = new MemberDAO();
		return dao.update(vo);
	}
}
