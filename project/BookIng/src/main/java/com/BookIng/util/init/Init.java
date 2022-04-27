package com.BookIng.util.init;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class init
 */
// @WebServlet("/init") -> web.xml에서 servlet으로 등록
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// URL 등급에 대한 정보를 저장하는 map 변수
	public static Map<String, Integer> authorityMap = new HashMap<String, Integer>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Init() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	// 서버가 실행될 때 초기화되는 작업
	public void init(ServletConfig config) throws ServletException {
		// 서버가 실행될 때 실행되는지 확인
		System.out.println("Init.init() 실행하고 있다 ------------");
		// 1. DB 드라이버 확인 -> DB.class확인을 한다
		try {
			Class.forName("com.BookIng.util.db.DB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2. 실행하려는 객체를 생성하고 조립해 둔다 -> 생략한다, 대신 필요할 때 new 하는 것으로 한다
		// 3. 페이지 권한을 등록한다
		// 9 : 관리자, 1 일반회원
		// 회원관리 권한
		authorityMap.put("/member/list.jsp", 9);
		authorityMap.put("/member/view.jsp", 1);
		authorityMap.put("/member/updateForm.jsp", 1);
		authorityMap.put("/member/upadte.jsp", 1);
		authorityMap.put("/member/delete.jsp", 1);

		// 공지사항 권한
		authorityMap.put("/notice/write.jsp", 9);
		authorityMap.put("/notice/writeForm.jsp", 9);
		authorityMap.put("/notice/updateForm.jsp", 9);
		authorityMap.put("/notice/update.jsp", 9);
		authorityMap.put("/notice/delete.jsp", 9);

		// 질문답변 권한 
		authorityMap.put("/qna/writeForm.jsp", 1);
		authorityMap.put("/qna/write.jsp", 1);
		authorityMap.put("/qna/answerForm.jsp", 1);
		authorityMap.put("/qna/answer.jsp", 1);
		authorityMap.put("/qna/updateForm.jsp", 1);
		authorityMap.put("/qna/update.jsp", 1);
		authorityMap.put("/qna/delete.jsp", 1);

		// bookBoard 권한 
		authorityMap.put("/bookBoard/writeForm.jsp", 1);
		authorityMap.put("/bookBoard/write.jsp", 1);
		authorityMap.put("/bookBoard/updateForm.jsp", 1);
		authorityMap.put("/bookBoard/update.jsp", 1);
		authorityMap.put("/bookBoard/delete.jsp", 1);
		authorityMap.put("/bookBoard/changeCover.jsp", 1);

		// bookCart 권한 
		authorityMap.put("/bookCart/addCart.jsp", 1);
		authorityMap.put("/bookCart/cart.jsp", 1);
		authorityMap.put("/bookCart/cartList.jsp", 1);
		authorityMap.put("/bookCart/deleteBooks.jsp", 1);
		authorityMap.put("/bookCart/deleteCart.jsp", 1);
		authorityMap.put("/bookCart/purchase.jsp", 1);
		authorityMap.put("/bookCart/removeBook.jsp", 1);
		authorityMap.put("/bookCart/cartMasterList.jsp", 9);
		authorityMap.put("/bookCart/addCartM.jsp", 9);
		authorityMap.put("/bookCart/deleteBooksM.jsp", 9);

		// 등급관리 권한
	    authorityMap.put("/grade/list.jsp", 9);
	    authorityMap.put("/grade/write.jsp", 9);
	    authorityMap.put("/grade/writeForm.jsp", 9);
	    authorityMap.put("/grade/updateForm.jsp", 9);
	    authorityMap.put("/grade/update.jsp", 9);
	    authorityMap.put("/grade/delete.jsp", 9);
	}

}

/*
 * authorityMap.put("/member/list.jsp", 9); authorityMap.put("/member/view.jsp",
 * 1); authorityMap.put("/member/updateForm.jsp", 1);
 * authorityMap.put("/member/upadte.jsp", 1);
 * authorityMap.put("/member/delete.jsp", 1);
 * 
 * // 공지사항 권한 authorityMap.put("/notice/writeForm.jsp", 9);
 * authorityMap.put("/notice/write.jsp", 9);
 * authorityMap.put("/notice/updateForm.jsp", 9);
 * authorityMap.put("/notice/update.jsp", 9);
 * authorityMap.put("/notice/delete.jsp", 9);
 * 
 * // 질문답변 권한 authorityMap.put("/qna/writeForm.jsp", 1);
 * authorityMap.put("/qna/write.jsp", 1);
 * authorityMap.put("/qna/answerForm.jsp", 1);
 * authorityMap.put("/qna/answer.jsp", 1);
 * authorityMap.put("/qna/updateForm.jsp", 1);
 * authorityMap.put("/qna/update.jsp", 1); authorityMap.put("/qna/delete.jsp",
 * 1);
 * 
 * // bookBoard 권한 authorityMap.put("/bookBoard/list.jsp", 1);
 * authorityMap.put("/bookBoard/writeForm.jsp", 1);
 * authorityMap.put("/bookBoard/write.jsp", 1);
 * authorityMap.put("/bookBoard/updateForm.jsp", 1);
 * authorityMap.put("/bookBoard/update.jsp", 1);
 * authorityMap.put("/bookBoard/delete.jsp", 1);
 * authorityMap.put("/bookBoard/changeCover.jsp", 1);
 * 
 * // bookCart 권한 authorityMap.put("/bookCart/addCart.jsp", 1);
 * authorityMap.put("/bookCart/cart.jsp", 1);
 * authorityMap.put("/bookCart/cartList.jsp", 1);
 * authorityMap.put("/bookCart/deleteBooks.jsp", 1);
 * authorityMap.put("/bookCart/deleteCart.jsp", 1);
 * authorityMap.put("/bookCart/purchase.jsp", 1);
 * authorityMap.put("/bookCart/removeBook.jsp", 1);
 */