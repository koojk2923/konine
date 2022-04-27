package com.BookIng.notice.service;


import com.BookIng.notice.dao.NoticeDAO;
import com.BookIng.notice.vo.NoticeVO;

public class NoticeUpdateService {

	public int service(NoticeVO vo) throws Exception {
		NoticeDAO dao = new NoticeDAO();
		return dao.update(vo);
	}
}
