package com.BookIng.bookCart.service;

import com.BookIng.bookCart.dao.BookCartDAO;
import com.BookIng.bookCart.vo.BookCartVO;

// 도서 정보를 열람하는 서비스
public class BookCartViewService {
	
	public BookCartVO service(long bookNo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookCartDAO dao = new BookCartDAO();
		return dao.view(bookNo);
	}

}
