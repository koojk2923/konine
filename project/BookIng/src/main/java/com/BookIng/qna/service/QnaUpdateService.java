package com.BookIng.qna.service;

import com.BookIng.qna.dao.QnaDAO;
import com.BookIng.qna.vo.QnaVO;

public class QnaUpdateService {

	public int service(QnaVO vo) throws Exception {
		QnaDAO dao = new QnaDAO();
		return dao.update(vo);
	}
}
