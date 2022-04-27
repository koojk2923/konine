package com.BookIng.notice.service;

import com.BookIng.notice.dao.NoticeDAO;
import com.BookIng.notice.vo.NoticeVO;

public class NoticeViewService {

	public NoticeVO service(long no) throws Exception {
		NoticeDAO dao = new NoticeDAO();
		return dao.view(no);
	}
}
