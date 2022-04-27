package com.BookIng.bookCart.service;

import com.BookIng.bookCart.dao.BookCartDAO;
import com.BookIng.bookCart.vo.BookCartVO;

public class BookCartCountService {
	//도서 정보를 입력하는 서비스
	public void service(BookCartVO vo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookCartDAO dao = new BookCartDAO();
		dao.countBooks(vo);
		
	}

}
