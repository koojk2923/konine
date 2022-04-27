package com.BookIng.member.service;

import java.util.List;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public class MemberListService {
	public List<MemberVO> service(PageObject pageObject) throws Exception {
	//생성하고 호출한 결과를 리턴한다. - Controller - Service - DAO
			MemberDAO dao = new MemberDAO();
			//전체 데이터 세팅 -> 페이지 정보 계산
			pageObject.setTotalRow(dao.getTotalRow(pageObject));
			return dao.list(pageObject);
	}	
}
