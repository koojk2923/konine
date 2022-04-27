<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.bookCart.service.BookCartCountService"%>
<%@page import="com.BookIng.bookCart.service.BookCartAddService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookCart.dao.BookCartDAO"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 넘어오는 데이터 받기
//bookNo 데이터를 받아 스트링 데이터로 선언한다.
String strBookNo = request.getParameter("bookNo");
//받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo); 
String strBookCount = request.getParameter("bookCount");
long bookCount = Long.parseLong(strBookCount);

System.out.println("책번호 : " + bookNo);

BookCartDAO dao = new BookCartDAO();
BookCartVO vo = new BookCartVO();
LoginVO loginVO = (LoginVO) session.getAttribute("login");
String id = loginVO.getId();

vo.setBookNo(bookNo);
vo.setBookCount(bookCount);
vo.setId(id);

//해당상품(+옵션까지)이 장바구니에 동일한게 있는지 체크
int result = dao.checkBooks(vo);

if(result != 1){ //장바구니에 해당상품이 없다
	BookCartAddService service = new BookCartAddService();
	service.service(vo);
} else { // 장바구니에 해당 상품이 있으면 수량 증가
	BookCartCountService service = new BookCartCountService();
	service.service(vo);
}


System.out.println("장바구니 등록 : " + vo);
// 

response.sendRedirect("/bookCart/cartList.jsp");
%>