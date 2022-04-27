<%@page import="com.BookIng.member.vo.MemberVO"%>
<%@page import="com.BookIng.member.service.MemberViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//데이터 수집 - 아이디 
String id = request.getParameter("id");
//Controller (JSP) - MemberViewService - MemberDAO
//MemberViewService클래스 작성 생성 후 호출 
//MemberDAO 클래스에 view(long no) 작성
MemberViewService service = new MemberViewService();
MemberVO vo = service.service(id);
//System.out.println("view.jsp - vo: " + vo);
%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>회원 정보 보기</title>
<!-- JQuery 라이브러리 등록 -JS를 쉽게 처리하기 위한 함수가 들어 있다. jQuery() ==$() -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type = "text/css">
th, td{
	border: 1px solid #888;
	/* padding - 안에 여백: 데이터 1개- 사방을 적용, 데이터 2개: 위아래, 좌우  
	데이터 4개: -위 우 아래 왼쪽 (시계방향)*/
	padding: 5px}

/* id가 deleteDiv안에 label태그를 선택해서 CSS를 적용시켜라 */
#deleteDiv label{
	margin: 0 5px 0 10px;
	
	/*데이터 4개: -위 우 아래 왼쪽 (시계방향) =>padding: 0 3px 0 10px;  */	
}

#deleteDiv {
	border: 1px solid #888;
	margin: 10px 0 0 0;
	padding: 10px;
	width: 700px;
}


#id, #pw, #tel {
	width: 100px;
}

#changePhotoDiv{
	display: none;


}
h2.h2 {
  text-align: center;
  background: #ffcccc;
  color: #0000ff;
}

    #wrapper{
        width:200px;
        margin:auto;
    }
 
  th {
         text-align : center;
         vertical-align : bottom;
      }

 
 
</style>

<script type="text/javascript">
	//body(문서-document)가 다 로딩이 되면 동작이 되도록 선언한 형태	
$(function(){
	// deleteDiv id를 찾아서 숨긴다(hide)
	//deleteDiv가 보였다가 숨겨진다. -> 원래부터 안보이게 하는것은 CSS의 visiability 속성을 이용한다.
	$("#deleteDiv").hide();
});

</script>




</head>
<body>
	<div class="container ">
	
<h2 class="h2">회원 정보 보기</h2>
<table class="table">
<tbody>
<tr>
	<th>id</th>
	<td><%=vo.getId() %></td>
</tr>
<tr>
	<th>이름</th>
	<td><%=vo.getName() %></td>
</tr>
<tr>
	<th>성별</th>
	<td><%=vo.getGender() %></td>
</tr>
<tr>
	<th>생년월일</th>
	<td><%=vo.getBirth() %></td>
</tr>
<tr>
	<th>연락처</th>
	<td><%=vo.getTel() %></td>
</tr>
<tr>
	<th>이메일</th>
	<td><%=vo.getEmail() %></td>
</tr>
<tr>
	<th><br><br><br><br><br><br><br>사진</th>
	<td>
		<img src ="<%=vo.getPhoto() %>" width="150">
		<br>
		<button onclick="$('#changePhotoDiv').show()">바꾸기</button>
		<div id ="changePhotoDiv">
			<hr>
			<form action = "changePhoto.jsp" method="post" enctype="multipart/form-data" >
				<input type = "hidden" name="id" value="<%=vo.getId() %>">
				<input type = "hidden" name="oldPhoto" value="<%=vo.getPhoto() %>">
				<input type = "file" name="photo" required="required">
				<button>바꾸기</button>
			<button type="button" onclick="$('#changePhotoDiv').hide()">취소</button>
			</form>
			
			</div>
	</td>
</tr>
<tr>
	<th>회원등록일</th>
	<td><%=vo.getRegDate() %></td>
</tr>
<tr>
	<th>최근접속일</th>
	<td><%=vo.getConDate() %></td>
</tr>
<tr>
	<th>회원상태</th>
	<td><%=vo.getStatus() %></td>
</tr>
<tr>
	<th>등급</th>
	<td><%=vo.getGradeName() %>(<%= vo.getGradeNo() %>)</td>
</tr>
</tbody>
</table>


<div id="wrapper">
<a href = "updateForm.jsp?id=<%=vo.getId()%>" style="color: #000" ><button>수정</button></a>
			
<button onclick="$('#deleteDiv').show();" style="color: #000" >삭제</button>
<a href = "list.jsp" style="color: #000" ><button>리스트</button></a></div>
<div id= "deleteDiv">
	<h3>삭제에 필요한 정보 입력</h3>
	<form action="delete.jsp" method="post">
		<br> <label for="id">아이디</label><input name = 'id' id = "id">
		<label for="pw">비밀번호</label><input name = 'pw' id = "pw">
		<label for="tel">전화번호</label><input name = 'tel' id = "tel">
		<button>삭제</button>
		<button type="button" onclick = "$('#deleteDiv').hide();">취소</button>
	
	</form>
	
		</div>
		
</div>
</body>
</html>
