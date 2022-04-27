package com.BookIng.bookBoard.service;

import java.util.List;

import com.BookIng.bookBoard.dao.BookBoardDAO;
import com.BookIng.bookBoard.vo.BookBoardVO;
import com.webjjang.util.PageObject;

// 도서 목록을 불러오는 서비스
public class BookBoardListService {
	
	public List<BookBoardVO> service(PageObject pageObject) throws Exception {
		// 생성하고 호출한 결과를 리턴한다.
		BookBoardDAO dao = new BookBoardDAO();
		// 페이지의 정보를 계산하는 처리문이 담겨있는 메소드를 호출해서 전체 데이터 갯수를 넣는다.
		// 전체 데이터를 세는 처리를 하지 않으면 데이터를 가져오지 않는다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
		
	}

}
