package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;

public class MemberWriteService {
	public void service(MemberVO vo) throws Exception{
		//생성 호출
		MemberDAO dao = new MemberDAO();
		dao.write(vo);
	}
		

}