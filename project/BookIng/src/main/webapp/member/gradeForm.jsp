<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@page import="com.BookIng.member.service.MemberViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// DB 글번호에 맞는 데이터 가져오기
String id = request.getParameter("id");
System.out.println("id : " + id);
// 데이터(글번호) 받기
MemberViewService Service = new MemberViewService();
MemberVO vo = Service.service(id);
System.out.println("vo : " + vo);
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급 권한 수정</title>
<style type="text/css">
th, td{
	border: 1px solid #888;
	padding: 5px;
}
</style>
</head>
<body>
<h2>등급 권한 수정</h2>
<!-- 사용자에게 테이터를 입력하도록 한다. : form, input, select, textarea tag  -->
<form action="grade.jsp" method="post">
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" maxlength="20" value="<%=vo.getId()%>" 
						readonly="readonly" /></td>
<!-- 						onclick="alert('아이디는 수정할 수 없습니다.')" -->
		</tr>
		<tr>
			<th>등급</th>
			<td>
				<label>
					<input type="text" name="gradeNo" maxlength="10" value="<%=vo.getGradeNo()%>"/>
				</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<!-- button이 form tag안에 있으면 데이터를 전달하는 동작을 하게됨 -->
				<button type="submit">수정</button>
				<button type="reset">다시 입력</button>
				<button type="button" onclick="history.back()">취소</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>