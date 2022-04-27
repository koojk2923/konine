package com.BookIng.notice.service;


import com.BookIng.notice.dao.NoticeDAO;

public class NoticeDeleteService {

	public int service(long no) throws Exception {
		NoticeDAO dao = new NoticeDAO();
		return dao.delete(no);
	}
}
