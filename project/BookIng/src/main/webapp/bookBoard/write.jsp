<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardWriteService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 서버에서 저장할 주소에서 localhost 뒤에 붙는 위치가 된다.
String path = "/upload/cover/";
String realPath = request.getServletContext().getRealPath(path);
System.out.println(realPath);
// 저장용량 제한
int size = 10 * 1024 * 1024;
// 실제적으로 파일 업로드 하는 처리문 (request, 저장위치, 제한용량, 엔코딩, 중복파일명 처리 객체)
MultipartRequest multi 
= new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());

// multi에서 데이터를 받아 스트링 데이터로 선언한다.
String title = multi.getParameter("title");
String writer = multi.getParameter("writer");
String genre = multi.getParameter("genre");
String publisher = multi.getParameter("publisher");
String pubDate = multi.getParameter("pubDate");
String price = multi.getParameter("price");
String summary = multi.getParameter("summary");
String cover = multi.getFilesystemName("cover");
System.out.println("write.jsp - cover : " + cover);

// writeForm에서 데이터를 받아 vo에 입력시킨다.
BookBoardVO vo = new BookBoardVO();
vo.setTitle(title);
vo.setWriter(writer);
vo.setGenre(genre);
vo.setPublisher(publisher);
vo.setPubDate(pubDate);
vo.setPrice(price);
vo.setSummary(summary);
vo.setCover(path + cover);

System.out.println("도서 등록 : " + vo);

//DB에 데이터를 입력한다. 
BookBoardWriteService service = new BookBoardWriteService();
service.service(vo);

//list.jsp으로 돌아간다.
response.sendRedirect("list.jsp");
%>
