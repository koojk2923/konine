<%@page import="com.BookIng.bookCart.service.BookCartCountMService"%>
<%@page import="com.BookIng.bookCart.service.BookCartAddMService"%>
<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookCart.dao.BookCartDAO"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 넘어오는 데이터 받기
//bookNo 데이터를 받아 스트링 데이터로 선언한다.
String strCartNo = request.getParameter("cartNo");
//받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long cartNo = Long.parseLong(strCartNo); 
String strBookCount = request.getParameter("bookCount");
long bookCount = Long.parseLong(strBookCount);

System.out.println("책번호 : " + cartNo);

BookCartDAO dao = new BookCartDAO();
BookCartVO vo = new BookCartVO();

vo.setCartNo(cartNo);
vo.setBookCount(bookCount);

	BookCartCountMService service = new BookCartCountMService();
	service.service(vo);

System.out.println("장바구니 등록 : " + vo);

response.sendRedirect("/bookCart/cartMasterList.jsp");
%>