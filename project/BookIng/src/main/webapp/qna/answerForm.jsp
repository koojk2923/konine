<%@page import="com.webjjang.util.PageObject"%>
<%@page import="com.BookIng.qna.vo.QnaVO"%>
<%@page import="com.BookIng.qna.service.QnaViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
// 여기가 자바 부분 입니다.
// 데이터 받기 - 번호, 관련글번호, 순서번호 들여쓰기번호, 제목

// 넘어오는 페이지 정보를 받아서 페이지 객체 생성
PageObject pageObject = PageObject.getInstance(request);

String strNo = request.getParameter("no");
long no = Long.parseLong(strNo);

// DB에서 QnaVO 가져 온다.
QnaViewService service = new QnaViewService();
QnaVO vo = service.service(no,0); // 뒤에 0은 조회수 1증가 하지 않는다.

//데이터 확인
System.out.println("answerForm.jsp - vo : " + vo);

//EL 이나 JSTL에서 데이터를 사용하기 위해서 기본 저장 객체에 저장해 둔다.
request.setAttribute("vo", vo);
request.setAttribute("pageObject", pageObject);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변하기</title>
<style type="text/css">
h2 { /* 문의답변보기 : 공간 */
	margin-bottom: 50px;
}
body form {
 font-family: "Trebuchet MS", Tahoma, sans-serif;
 font-size: large;
}

</style>
</head>
<body>
<div class="container">
<h2 style="text-align:center;"><b>답변</b></h2>
<!-- 사용자에게 테이터를 입력하도록 한다. : form, input, select, textarea tag  
		bootstrap - class="form-horizontal" : 한줄에 라벨과 입력을 같이둔다. 
		col-sm-2 : col-칸, sm-해상도, 2-너비(총 12까지)  -->
<form action="answer.jsp" method="post" class="form-horizontal">
	<input type="hidden" name="no" value="${vo.no }">
	<input type="hidden" name="refNo" value="${vo.refNo }">
	<input type="hidden" name="ordNo" value="${vo.ordNo }">
	<input type="hidden" name="levNo" value="${vo.levNo }">
	<!-- 넘어오는 key, word  -->
	<input name="key" type="hidden" value="${pageObject.key }">
	<input name="word" type="hidden" value="${pageObject.word }">
		
		<div class="form-group">
				<label for="no" class="control-label col-sm-2">번호</label>
				<div class="col-sm-10">
					<!-- input 데이터 입력, type : 입력형태, name : 전달이름, maxlength : 최대입력 -->
					<input type="text" name="no" value="${vo.no }" id="no"
						readonly="readonly" class="form-control" />
				</div>
			</div>
		
		<div class="form-group">
			<label for="title" class="control-label col-sm-2">제목</label>
			<!-- input 데이터 입력, type : 입력형태 name : 전달 이름, maxlength : 최대입력 -->
			<div class="col-sm-10">
				<input type="text" name="title" maxlength="10" 
						value="[답변]&nbsp;${vo.title }" id="title" class="form-control"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="content" class="control-label col-sm-2">내용</label>
			<div class="col-sm-10">
				<textarea rows="7" name="content" id="content" class="form-control"></textarea>
			</div>
		</div>
		
		<div class="text-center">
				<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
				<button type="submit" class="btn btn-default">답변</button>
				<button type="reset" class="btn btn-default">다시 입력</button>
				<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
		</div>
				
</form>
</div>
</body>