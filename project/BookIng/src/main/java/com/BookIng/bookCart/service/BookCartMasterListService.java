package com.BookIng.bookCart.service;


import java.util.List;

import com.BookIng.bookCart.dao.BookCartDAO;
import com.BookIng.bookCart.vo.BookCartVO;

// 도서 목록을 불러오는 서비스
public class BookCartMasterListService {
	
	public  List<BookCartVO> service(BookCartVO vo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookCartDAO dao = new BookCartDAO();
		return dao.masterList(vo);
		
	}

}
