<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.qna.service.QnaAnswerService"%>
<%@page import="com.BookIng.qna.vo.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
// 여기는 자바 입니다.

// 1.넘어오는 데이터 받기
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);
String title = request.getParameter("title");
String content = request.getParameter("content");

String strRefNo = request.getParameter("refNo");
long refNo = Long.parseLong(strRefNo);
String strOrdNo = request.getParameter("ordNo");
int ordNo = Integer.parseInt(strOrdNo);
String strLevNo = request.getParameter("levNo");
int levNo = Integer.parseInt(strLevNo);

//아이디는  session에서 꺼낸다.
String id =((LoginVO)session.getAttribute("login")).getId();

// SQL - insert 처리 번호는 시퀸즈 사용
// Controller -> Service -> DAO : QnaVO 객체를 만들어서 전달한다.
QnaVO vo = new QnaVO();
vo.setTitle(title);
vo.setContent(content);
vo.setId(id); 
vo.setRefNo(refNo); // 1차 정렬을 refNo로 하기 떄문에 한곳에 모여야 한다. 질문과 답변은 같은 번호를 가지고 있어야 한다.
vo.setOrdNo(ordNo + 1); // 보고 있는 글 아래로 위치 하도록 +1 한다. 데이터 중에서 +1한 값과 같거나 큰 데이터는 +1을 해야한다.
vo.setLevNo(levNo + 1); // 보고 있는 글 아래로 위치 하도록 +1 한다. 데이터 중에서 +1한 값과 같거나 큰 데이터는 +1을 해야한다.
vo.setParentNo(no); // 보고 있었던 글번호가 부모 글번호가 되기 때문에. 글번호는 시퀸즈 사용 

System.out.println("답변하기  - " + vo);
//QnaAnswerService -> QnaDAO.answer()
QnaAnswerService service = new QnaAnswerService();
service.service(vo);

//게시판 리스트로 자동 이동시킨다.
// response.sendRedirect("list.jsp");
response.sendRedirect("list.jsp?no=" + no + "&inc=0" + "&key=" + request.getParameter("key") + "&word=" + request.getParameter("word"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>