<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<style type="text/css">
h2 { /* 문의답변보기 : 공간 */
	margin-bottom: 50px;
}
body form {
 font-family: "Trebuchet MS", Tahoma, sans-serif;
 font-size: large;
}

</style>
<!-- emptyCheck(objStr, itemName) 함수 js 파일을 포함 시킨다. -->
<script type="text/javascript" src="/js/formUtil.js"></script>
<script type="text/javascript">
//데이터 유효성 검사
$(function() {
	// 입력란의 배경색을 #eee(옅은 회색) 만들어 놓고, 입력하는 입력란에 배경색을 흰색으로 하자.
	// JQuery
	$("input, textarea").css("background", "#eee")
	// 선택한 객체의 focus - 커서가 들어 갔을 때 이벤트 처리
	.focus(function(){
		//alert("입력란 들어옴.");
		console.log("입력란 들어옴.");
		$(this).css("background", "#fff");
	})
	// 선택한 객체의 blur - 커서가 나갈 떄 이벤트 처리
	.blur(function(){
		console.log("입력란 나감.");
		$(this).css("background", "#eee");
	});
	
	$("#writeForm").submit(function() {
		// alert("데이터를 넘기려고 한다.");
		
		// 제목 -> 비어있으면 경고>포커스>이동막기 
		if(emptyCheck("#title", "제목")) return false;
		// 내용 -> 비어있으면 경고>포커스>이동막기 
		if(emptyCheck("#content", "내용")) return false;	
			
		// 길이 제한 검사 - JS
		// 제목은 4~100 까지 사용 가능
		if(!lengthCheck("#title", "제목", 4, 100)) return false;
		// 내용은 4~666(2000 바이트) 까지 사용 가능
		if(!lengthCheck("#content", "내용", 4, 666)) return false;	
	});
});
</script>
</head>
<body>
<div class="container">
<h2 style="text-align:center;"><b>문의하기</b></h2>
		<form action="write.jsp" method="post" class="form-horizontal" id="writeForm">
			<div class="form-group" >

				<label for="title" class="control-label col-sm-2">제목</label>
				<!-- input 데이터 입력, type : 입력형태 name : 전달 이름, maxlength : 최대입력 -->
				<div class="col-sm-10">
					<input type="text" name="title" maxlength="100" id="title"
						class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="content" class="control-label col-sm-2">내용</label>
				<div class="col-sm-10">
					<textarea rows="9" name="content" id="content"
						class="form-control"></textarea>
				</div>
			</div>
			
			<div class="text-center">	
					<!-- button이 form tag 안에 있으면 데이터를 전달하는 동작을 하게 된다. -->
					<button type="submit" class="btn btn-default">등록</button>
					<button type="reset" class="btn btn-default">다시 입력</button>
					<button type="button" onclick="history.back()" class="btn btn-default">취소</button>
			</div>	
	</form>
</div>	
</body>
</html>