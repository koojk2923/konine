package com.BookIng.bookCart.service;

import com.BookIng.bookCart.dao.BookCartDAO;
import com.BookIng.bookCart.vo.BookCartVO;

//도서 정보를 삭제하는 서비스
public class BookCartDeleteCartService {
	public int service(BookCartVO vo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookCartDAO dao = new BookCartDAO();
		return dao.deleteCart(vo);
	}

}
