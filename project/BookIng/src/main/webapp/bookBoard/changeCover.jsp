<%@page import="com.BookIng.bookBoard.service.CoverChangeService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
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

// bookNo의 스트링 데이터를 받아온다.
String strBookNo = multi.getParameter("bookNo");
// 받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo); 
String cover = multi.getFilesystemName("cover");
// db변경이 되면 삭제할 파일
String oldCover = multi.getParameter("oldCover");
System.out.println("bookNo :" + bookNo + " oldCover : " + oldCover);

// DB에서 데이터를 가져온다. 
BookBoardVO vo = new BookBoardVO ();
vo.setBookNo(bookNo);
vo.setCover(path + cover);

System.out.println("이미지 변경 : " + vo);

CoverChangeService service = new CoverChangeService();
int result = service.service(vo);

// result 가 0보다 크면 변경이 되었으므로 이전파일은 삭제한다.
// 이전 파일명으로 realPath를 구하고 file객체로 만들어서 delete() 호출한 뒤 삭제한다.
File oldFile = new File (request.getServletContext().getRealPath(oldCover));
//  oldFile.exists() -> 파일이나 폴더가 존재합니까? (true)
if(result > 0 && oldFile.exists())
	oldFile.delete();

response.sendRedirect("updateForm.jsp?bookNo=" + vo.getBookNo());
%>
