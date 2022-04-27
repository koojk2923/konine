<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.BookIng.member.service.MemberWriteService"%>
<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//여기는 자바 입니다.

//서버에서 저장할 localhost 뒤에 붙는 위치가 된다.
String path = "/upload/member/";
String realPath = request.getServletContext().getRealPath(path);
System.out.println("write.jsp - realPath: " + realPath);
//저장 용량 제한
int size = 10 * 1024 * 1024;//=10mb

//실제적으로 파일 업로드하는 처리문
// new MultipartRequest(request, 저장위치, 용량제한, 엔코딩, 중복파일명처리 객체)
MultipartRequest multi 
= new MultipartRequest(request, realPath, size, "utf-8", new DefaultFileRenamePolicy());

//한글 처리 할필요 없음 -> 필터 만들어서
	//request.setCharacterEncoding("utf-8");
//넘어오는 데이터 받기 - 아이디, 비밀번호, 이름, 성별, 생년월일, 연락처, 이메일, 사진

String id = multi.getParameter("id");
String pw = multi.getParameter("pw");
String name = multi.getParameter("name");
String gender = multi.getParameter("gender");
String birth = multi.getParameter("birth");
String tel = multi.getParameter("tel");
String email = multi.getParameter("email");
String photo = multi.getFilesystemName("photo");
System.out.println("write.jsp - photo: " + photo);

//사진이 안들어오면 NoImage.jpg로 기본 세팅한다
if(photo == null || photo.equals("")) photo = "/upload/member/noImage.png";
System.out.println("write.jsp - photo: " + photo);

// Controller -> Service -> DAO: BoardVO 객체를 만들어서 전달한다.
MemberVO vo = new MemberVO();
vo.setId(id);
vo.setPw(pw);
vo.setName(name);
vo.setGender(gender);
vo.setBirth(birth);
vo.setTel(tel);
vo.setEmail(email);
vo.setPhoto(path + photo);

System.out.println("회원가입 vo - " + vo);
//MemberWriteSerivce -> MemberDAO.write
MemberWriteService service = new MemberWriteService();
service.service(vo);

//회원 관리 리스트로 자동 이시킨다.
response.sendRedirect("list.jsp");


%>
