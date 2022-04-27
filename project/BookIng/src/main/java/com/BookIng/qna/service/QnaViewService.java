package com.BookIng.qna.service;

import com.BookIng.qna.dao.QnaDAO;
import com.BookIng.qna.vo.QnaVO;

public class QnaViewService {

	public QnaVO service(long no, int inc) throws Exception {
		QnaDAO dao = new QnaDAO();
		// inc = 1이면 조회수 1 증가, inc = 0이면(그렇지 않으면) 증가시키지 않는다.(아무일도 안한다.)
		// 조회수 1증가 - 원래의 값을 +1해서 다시 데이터를 넣는다. : update
		if (inc == 1)
			dao.increase(no);
		return dao.view(no);
	}
}
