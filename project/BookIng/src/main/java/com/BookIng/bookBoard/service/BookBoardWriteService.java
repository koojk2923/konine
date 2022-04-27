package com.BookIng.bookBoard.service;

import com.BookIng.bookBoard.dao.BookBoardDAO;
import com.BookIng.bookBoard.vo.BookBoardVO;

public class BookBoardWriteService {
	//도서 정보를 입력하는 서비스
	public void service(BookBoardVO vo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookBoardDAO dao = new BookBoardDAO();
		dao.write(vo);
		
	}

}
