<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
$(function(){
	
		// id중복체크 변수
		// nickName중복체크 변수, 비밀번호와 비밀범호 확인이 같은지 체크 변수 -> 전역 변수 선언
		var idCheck = false;
		var nickNameCheck = false;

//--------------------------------------------------------------------------------------------------------------------
	// datepicker 클래스 이벤트 - 적정한 옵션을 넣어서 초기화 시켜 준다. 기본 datepicker()로 사용 가능
	$(".datepicker").datepicker(
			{
				changeMonth : true,
				changeYear : true,
				dateFormat : "yy-mm-dd",
				dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ],
				monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월",
						"7월", "8월", "9월", "10월", "11월", "12월" ]
			});

	var now = new Date();
	var startYear = now.getFullYear();
	var yearRange = (startYear - 100) + ":" + startYear;
	// datepicker - 초기값으로 셋팅하는 방법을 사용하면 2번째는 무시 당한다.
	//원래 있던 datepicker에 option을 추가하는 방법이다.
	$(".datepicker").datepicker("option", {
		"maxDate" : new Date(),
		"yearRange" : yearRange
	});

	// $( ".datepicker" ).datepicker();
//--------------------------------------------------------------------------------------------------------------------
		// 1-1.아이디 체크 이벤트
		$("#id").keyup(function() {

			idCheck = false;

			var id = $("#id").val();
			// 공백문자 처리
			id = $.trim(id);
			$("#id").val(id);
			// alert("입력한 아이디 : " + id);

			// 4자 미만 처리
			if (id.length < 4) {
				$("#idCheckDiv").removeClass("alert-success");
				$("#idCheckDiv").addClass("alert-danger");
				$("#idCheckDiv").text("아이디는 4자 이상 영숫자이여야 합니다.");
				return;
			}

			// 20자 초과
			if (id.length > 20) {
				$("#idCheckDiv").removeClass("alert-success");
				$("#idCheckDiv").addClass("alert-danger");
				$("#idCheckDiv").text("아이디는 20자 이내 영숫자이여야 합니다.");
				return;
			}

			// 4~20 사이 아이디인 경우 - 중복 체크하러 간다. -> 서버로 간다. URL필요 -> 화면에 다른 데이터는 변하지 않으면서 일부 처리에 필요한 데이터만 변경 URL은 변경이 없다. ->Ajax
			// 중복안된 경우(아이디가 null인 경우) - 사용가능한 아이디 입니다., 중복이된 경우(아이디가 null이 아닌 경우) - 중복된 아이디입니다. -> 서버에서 처리
			// /member/idCheck -> *.do:sitemesh 위 아래 메뉴와 카피라이트가 붙는다.
			// result -> 서버에서 전달해 주는 데이터
			$("#idCheckDiv").load("/member/idCheck?id=" + id, function(result) {
				// 결과에 따른 배경색 처리
				// alert(result);
				// 클래스 다 지우기
				$("#idCheckDiv").removeClass("alert-success alert-danger");
				if (result.indexOf("가능한") == -1) {
					// 중복된 아이디인 경우 배경은 빨간색
					$("#idCheckDiv").addClass("alert-danger");
					idCheck = false;
				} else {
					// 사용가능한 아이디인 경우 배경은 파란색
					$("#idCheckDiv").addClass("alert-success");
					idCheck = true;
				}
			});

		}); //$("#id").keyup() 이벤트의 끝
//--------------------------------------------------------------------------------------------------------------------
		// 1-2.닉네임 체크 이벤트
		$("#nickName").keyup(function() {

			nickNameCheck = false;

			var nickName = $("#nickName").val();
			// 공백문자 처리
			nickName = $.trim(nickName);
			$("#nickName").val(nickName);
			// alert("입력한 아이디 : " + id);

			// 4자 미만 처리
			if (id.length < 4) {
				$("#nickNameCheckDiv").removeClass("alert-success");
				$("#nickNameCheckDiv").addClass("alert-danger");
				$("#nickNameCheckDiv").text("아이디는 4자 이상 영숫자이여야 합니다.");
				return;
			}

			// 20자 초과
			if (id.length > 20) {
				$("#nickNameCheckDiv").removeClass("alert-success");
				$("#nickNameCheckDiv").addClass("alert-danger");
				$("#nickNameCheckDiv").text("아이디는 20자 이내 영숫자이여야 합니다.");
				return;
			}

			// 4~20 사이 아이디인 경우 - 중복 체크하러 간다. -> 서버로 간다. URL필요 -> 화면에 다른 데이터는 변하지 않으면서 일부 처리에 필요한 데이터만 변경 URL은 변경이 없다. ->Ajax
			// 중복안된 경우(아이디가 null인 경우) - 사용가능한 아이디 입니다., 중복이된 경우(아이디가 null이 아닌 경우) - 중복된 아이디입니다. -> 서버에서 처리
			// /member/idCheck -> *.do:sitemesh 위 아래 메뉴와 카피라이트가 붙는다.
			// result -> 서버에서 전달해 주는 데이터
			$("#nickNameCheckDiv").load("/member/nickNameCheck?nickName=" + nickName, function(result) {
				// 결과에 따른 배경색 처리
				// alert(result);
				// 클래스 다 지우기
				$("#nickNameCheckDiv").removeClass("alert-success alert-danger");
				if (result.indexOf("가능한") == -1) {
					// 중복된 아이디인 경우 배경은 빨간색
					$("#nickNameCheckDiv").addClass("alert-danger");
					nickNameCheck = false;
				} else {
					// 사용가능한 아이디인 경우 배경은 파란색
					$("#nickNameCheckDiv").addClass("alert-success");
					nickNameCheck = true;
				}
			});

		}); //$("#id").keyup() 이벤트의 끝
//--------------------------------------------------------------------------------------------------------------------
		// 2-1. 비밀번호 처리 이벤트
		$("#pw").keyup(function() {
			pwCheck = false;
			// $(this) == $("#pw")
			var pw = $(this).val();
			//alert(pw.length);
			// 4자 미만 처리
			if (pw.length < 4) {
				$("#pwCheckDiv").removeClass("alert-success");
				$("#pwCheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호는 4자 이상이여야 합니다.");
				return;
			}

			// 20자 초과 처리
			if (pw.length > 20) {
				$("#pwCheckDiv").removeClass("alert-success");
				$("#pwCheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호는 20자 이내이여야 합니다.");
				return;
			}

			// 4~20 사이 pw2와 같은지 체크
			var pw2 = $("#pw2").val();
			if (pw == pw2) {
				// 비밀번호와 비밀번호 확인이 같은 경우
				$("#pwCheckDiv, #pw2CheckDiv").removeClass("alert-danger");
				$("#pwCheckDiv, #pw2CheckDiv").addClass("alert-success");
				$("#pwCheckDiv, #pw2CheckDiv").text("적당한 비밀번호입니다.");
				pwCheck = true;
			} else {
				// 비밀번호와 비밀번호 확인이 같지 않은 경우
				$("#pwCheckDiv, #pw2CheckDiv").removeClass("alert-success");
				$("#pwCheckDiv, #pw2CheckDiv").addClass("alert-danger");
				$("#pwCheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
				if (pw2.length < 4)
					$("#pw2CheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				else if (pw2.length > 20)
					$("#pw2CheckDiv").text("비밀번호 확인은 20자 이내이여야 합니다.");
				else
					$("#pw2CheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
			}

		});
//--------------------------------------------------------------------------------------------------------------------		
		// 2-2. 비밀번호 확인 처리 이벤트
		$("#pw2").keyup(function() {
			pwCheck = false;

			// $(this) == $("#pw2")
			var pw2 = $(this).val();
			//alert(pw2.length);
			// 4자 미만 처리
			if (pw2.length < 4) {
				$("#pw2CheckDiv").removeClass("alert-success");
				$("#pw2CheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				return;
			}

			// 20자 초과 처리
			if (pw2.length > 20) {
				$("#pw2CheckDiv").removeClass("alert-success");
				$("#pw2CheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호 확인은 20자 이내이여야 합니다.");
				return;
			}

			// 4~20 사이 pw와 같은지 체크
			var pw = $("#pw").val();
			if (pw == pw2) {
				// 비밀번호와 비밀번호 확인이 같은 경우
				$("#pw2CheckDiv, #pwCheckDiv").removeClass("alert-danger");
				$("#pw2CheckDiv, #pwCheckDiv").addClass("alert-success");
				$("#pw2CheckDiv, #pwCheckDiv").text("적당한 비밀번호입니다.");
				pwCheck = true;
			} else {
				// 비밀번호와 비밀번호 확인이 같지 않은 경우
				$("#pwCheckDiv, #pw2CheckDiv").removeClass("alert-success");
				$("#pwCheckDiv, #pw2CheckDiv").addClass("alert-danger");
				$("#pw2CheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
				if (pw.length < 4)
					$("#pwCheckDiv").text("비밀번호확인은 4자 이상이여야 합니다.");
				else if (pw.length > 20)
					$("#pwCheckDiv").text("비밀번호 확인은 20자 이내이여야 합니다.");
				else
					$("#pwCheckDiv").text("비밀번호와 비밀번호 확인은 같아야 합니다.");
			}

		});
		// 비밀번호 처리 이벤트의 끝
//--------------------------------------------------------------------------------------------------------------------
		// 3. 회원 가입 이벤트
		$("#writeForm").submit(function() {

			// alert("아이디 체크 : " + idCheck + "\n비밀번호 체크 : " + pwCheck);

			// 아이디 중복체크 - 사용 가능한 아이디 인지 확인
			if (!idCheck) {
				alert("중복이 되지 않는 적당한 형식의 아이디를 사용하셔야만 합니다.");
				$("#id").focus();
				// form 전송을 무시시킨다.
				return false;
			}
// 			// 비밀번호와 비밀번호 확인
// 			if (!pwCheck) {
// 				alert("비밀번호와 비밀번호 확인의 길이가 4~20이여야 하고 같아야 합니다.");
// 				$("#pw").focus();
// 				// form 전송을 무시시킨다.
// 				return false;
// 			}

			// form 전송을 무시시킨다. -> 나중에 꼭 주석처리해야만 한다.
			// return false;
		});
}); // $(function(){}) 의 끝
</script>
</head>
<body>
<div class="container" style="width: 30%; float:none; margin:0 auto">
<h2 class="text-center">회원 가입</h2>
<div class="well">
	<form action="write.do" method="post" class="form-horizontal" id="writeForm">
		<div class="form-group">
			<label for="id" class="control-label col-sm-4">아이디</label> 
			<div class="col-sm-7">
				<input type="text" id="id" name="id" class="form-control" required="required" 
			 	pattern="[A-Za-z0-9]{4,20}" placeholder="아이디 입력" autocomplete="off">
			 	<div class="alert alert-danger" id="idCheckDiv" style="font-size: 11px">
			 	아이디는 4자 이상의 영숫자를 입력하셔야 합니다.</div>
			</div>	
		</div>
		
		<div class="form-group">
			<label for="nickName" class="control-label col-sm-4">닉네임</label> 
			<div class="col-sm-7">
				<input type="text" id="nickName" name="nickName" class="form-control" required="required" 
				 pattern="[A-Za-z0-9]{4,20}" placeholder="닉네임 입력" autocomplete="off">
				 <div class="alert alert-danger" id="nickNameCheckDiv" style="font-size: 11px">
			 	닉네임은 4자 이상의 영숫자를 입력하셔야 합니다.</div>
			 </div>
		</div>
		
		<div class="form-group">
			<label for="pw" class="control-label col-sm-4">비밀번호</label> 
			<div class="col-sm-7">
				<input type="password" id="pw" name="pw" class="form-control" required="required" 
				 pattern=".{4,20}" placeholder="비밀번호 입력" autocomplete="off">
				 <div class="alert alert-danger" id="pwCheckDiv" style="font-size: 11px">
				 비밀번호는 4자 이상이여야합니다.</div>
			 </div>
		</div>
		
		<div class="form-group">
			<label for="pw2" class="control-label col-sm-4">비밀번호 확인</label> 
			<div class="col-sm-7">
				<input type="password" id="pw2" name="pw2" class="form-control" required="required" 
				 pattern=".{4,20}" placeholder="비밀번호 확인 입력" autocomplete="off">
				  <div class="alert alert-danger" id="pw2CheckDiv" style="font-size: 11px">
				 비밀번호는 4자 이상이여야합니다.</div>
			 </div>
		</div>
		
		<div class="form-group">
			<label for="name" class="control-label col-sm-4">이름</label> 
			<div class="col-sm-7">
				<input type="text" id="name" name="name" class="form-control" required="required" 
				 pattern="[가-힣]{2,10}" placeholder="이름 입력" autocomplete="off">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="gender" class="control-label col-sm-4">성별</label> 
			<div class="col-sm-7">
				<label class="radio-inline">
					<input type="radio" id="gender" name="gender" value="남자" checked="checked">
					남자</label>
				<label class="radio-inline">
					<input type="radio" name="gender" value="여자">여자
				</label>
			 </div>
		</div>
		
		<div class="form-group">
			<label for="birth" class="control-label col-sm-4">생년월일</label> 
			<div class="col-sm-7">
				<input id="birth" name="birth" class="form-control datepicker" required="required" 
				placeholder="yyyy-mm-dd" autocomplete="off">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="tel" class="control-label col-sm-4">연락처</label> 
			<div class="col-sm-7">
				<input id="tel" name="tel" class="form-control" required="required" 
				placeholder="연락처 입력" autocomplete="off">
			 </div>
		</div>
		
		<div class="form-group">
			<label for="email" class="control-label col-sm-4">이메일</label> 
			<div class="col-sm-7">
				<input id="email" name="email" class="form-control" required="required" 
				placeholder="이메일 입력" autocomplete="off">
			 </div>
		</div>
		<div class="col-md-offest-3 text-center">
			<button class="btn btn-default">가입</button>
		</div>	
	</form>
</div>
</div>
</body>
</html>