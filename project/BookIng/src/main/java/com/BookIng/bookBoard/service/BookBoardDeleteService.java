package com.BookIng.bookBoard.service;

import com.BookIng.bookBoard.dao.BookBoardDAO;

//도서 정보를 삭제하는 서비스
public class BookBoardDeleteService {
	public int service(long BookNo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookBoardDAO dao = new BookBoardDAO();
		return dao.delete(BookNo);
	}

}
