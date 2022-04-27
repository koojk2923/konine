package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;

public class MemberViewService {
		public MemberVO service(String id) throws Exception{
		//생성 호출: Controller - [Service] - DAO
		MemberDAO dao = new MemberDAO();
		//list -> view 일떄만 조회수 1증가 시킨다.
		return dao.view(id);

					

		

	}
}
