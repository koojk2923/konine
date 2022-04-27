package com.BookIng.member.service;

import com.BookIng.member.dao.MemberDAO;
import com.BookIng.member.vo.LoginVO;

public class LoginService {	
	public LoginVO service(LoginVO inVO) throws Exception{
		MemberDAO dao = new MemberDAO();
		return dao.login(inVO);
	}

}
