package com.BookIng.bookBoard.service;

import com.BookIng.bookBoard.dao.BookBoardDAO;
import com.BookIng.bookBoard.vo.BookBoardVO;

// 책표지를 수정하는 서비스
public class CoverChangeService {
	
	public int service(BookBoardVO vo) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookBoardDAO dao = new BookBoardDAO();
		return dao.coverChange(vo);
		
	}

}
