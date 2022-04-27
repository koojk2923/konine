<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    
<%
String message = exception.getMessage();
StackTraceElement [] elements = exception.getStackTrace();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 오류</title>
</head>
<body>
<h2>게시판 오류</h2>
<div>이유 : <%= message %></div>
<ul>
<% for(StackTraceElement ste : elements) {%>
<li><%= ste %></li>
<% } %>
</ul>
</body>
</html>