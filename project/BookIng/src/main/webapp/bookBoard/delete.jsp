<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardDeleteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//스트링 데이터를 받아온다.
String strBookNo = request.getParameter("bookNo");
//받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo); 
String oldCover = request.getParameter("oldCover");
System.out.println("bookNo :" + bookNo + " oldCover : " + oldCover);

//DB에서 데이터를 가져온다. 
BookBoardDeleteService service = new BookBoardDeleteService();
BookBoardVO vo = new BookBoardVO();
vo.setBookNo(bookNo);

// 결과값를 result에 저장한다.
int result = service.service(bookNo);

//result 가 0보다 크면 변경이 되었으므로 이전파일은 삭제한다.
//이전 파일명으로 realPath를 구하고 file객체로 만들어서 delete() 호출한 뒤 삭제한다.
File oldFile = new File (request.getServletContext().getRealPath(oldCover));
//oldFile.exists() -> 파일이나 폴더가 존재합니까? (true)
if(result > 0 && oldFile.exists())
	oldFile.delete();

// list.jsp으로 돌아간다.
response.sendRedirect("list.jsp");
%>
