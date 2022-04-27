package com.BookIng.notice.service;

import java.util.List;

import com.BookIng.notice.dao.NoticeDAO;
import com.BookIng.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

public class NoticeListService {

	// pt = pageObject.period
	public List<NoticeVO> service(PageObject pageObject) throws Exception {
		NoticeDAO dao = new NoticeDAO();
		pageObject.setTotalRow(dao.getTotalRaw(pageObject));
		return dao.list(pageObject);
	}
}
