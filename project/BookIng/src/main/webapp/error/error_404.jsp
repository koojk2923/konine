<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>존재하지 않는 페이지</title>
</head>
<body>
<h2>존재하지 않는 페이지</h2>
<h3>요청 URI : <%= request.getRequestURI() %></h3>
<p>
페이지가 존재하지 않습니다.
</p>
<a href="/main/main.jsp"><button>메인으로</button></a>
</body>
</html>