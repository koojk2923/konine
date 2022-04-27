package com.BookIng.bookBoard.service;

import com.BookIng.bookBoard.dao.BookBoardDAO;
import com.BookIng.bookBoard.vo.BookBoardVO;

// 도서 정보를 열람하는 서비스
public class BookBoardViewService {
	
	public BookBoardVO service(long bookNo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookBoardDAO dao = new BookBoardDAO();
		return dao.view(bookNo);
	}

}
