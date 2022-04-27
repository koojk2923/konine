<%@page import="com.BookIng.notice.vo.NoticeVO"%>
<%@page import="com.BookIng.notice.service.NoticeViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

NoticeViewService service = new NoticeViewService();
NoticeVO vo = service.service(no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<body>
<div class="container">
	<h2>공지사항 수정</h2>
	<form action="update.jsp" method="post" id="updateForm" class="form-horizontal">
		<div class="form-group">
			<label for="no" class="control-label col-sm-2">번호</label>
		<div class="col-sm-10">
				<input type="text" name="no" value="<%=vo.getNo()%>"
				 	style="background: #eee" id="no" readonly="readonly" class="form-control">
		</div>
		</div>
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">제목</label>
		<div class="col-sm-10">
				<input type="text" name="title" maxlength="100"
					value="<%=vo.getTitle()%>" id="title" class="form-control">
		</div>
		</div>
		<div class="form-group">
			<label for="content" class="control-label col-sm-2">내용</label>				
		<div class="col-sm-10">
			<textarea rows="7" cols="80" name="content" id="content" class="form-control"><%=vo.getTitle()%></textarea>
		</div>
		</div>
		<div class="form-group">
			<label for="startDate" class="control-label col-sm-2">시작일</label>				
		<div class="col-sm-10">
			<input type="date" name="startDate" maxlength="10"
					value="<%=vo.getStartDate()%>" id="startDate" class="form-control" />
		</div>
		</div>
		<div class="form-group">
			<label for="endDate" class="control-label col-sm-2">종료일</label>				
		<div class="col-sm-10">
			<input type="date" name="endDate" maxlength="10"
					value="<%=vo.getEndDate()%>" id="endDate" class="form-control" />
		</div>
		</div>
		<div class="text-center">
				<button type="submit" class="btn btn-default">수정</button>
				<button type="reset" class="btn btn-default">다시입력</button>
				<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
</form>
</div>
</body>
</html>