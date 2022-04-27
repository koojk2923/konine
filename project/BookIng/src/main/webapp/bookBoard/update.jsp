<%@page import="com.BookIng.bookBoard.service.BookBoardUpdateService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//bookNo 데이터를 받아 스트링 데이터로 선언한다.
String strBookNo = request.getParameter("bookNo");
//받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo); 

// request에서 데이터를 받아 스트링 데이터로 선언한다.
String title = request.getParameter("title");
String writer = request.getParameter("writer");
String genre = request.getParameter("genre");
String publisher = request.getParameter("publisher");
String pubDate = request.getParameter("pubDate");
String price = request.getParameter("price");
String summary = request.getParameter("summary");

// controller(jsp) - service - dao : bookBoardVO 객체를 만들어서 전달
// updateForm에서 데이터를 받아 vo에 입력시킨다.
BookBoardVO vo = new BookBoardVO();
vo.setBookNo(bookNo);
vo.setTitle(title);
vo.setWriter(writer);
vo.setGenre(genre);
vo.setPublisher(publisher);
vo.setPubDate(pubDate);
vo.setPrice(price);
vo.setSummary(summary);

System.out.println("도서 정보수정 : " + vo);

//DB에 데이터를 입력한다. 
BookBoardUpdateService service = new BookBoardUpdateService();
service.service(vo);

//해당 글번호의 view.jsp으로 돌아간다.
response.sendRedirect("view.jsp?bookNo=" + bookNo);
%>