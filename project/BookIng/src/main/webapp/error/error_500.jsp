<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
<h2>에러 페이지</h2>
원인 : <%= exception.getMessage() %>
<p>
다시 한번 시도해 보십시오. 그래도 오류가 난다면 고객센터로 문의 부탁드립니다.
</p>
<a href="/main/main.jsp"><button>메인으로</button></a>
</body>
</html>