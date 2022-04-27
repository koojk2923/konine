/**
 * 게시판류 입력 폼의 데이터 처리를 위한 js
 */

// 데이터의 패턴 정규 표현식 객체 선언 -----------------------------------------------------

// 날짜 패턴 : 맨 앞자리는 숫자 4 + "-" + 중간숫자 2자리 : (0[1-9],|1[012]) + "-" + 뒷자리 2자리 : ([0][1-9]|[12][0-9]|3[01])
var reg_date = /^\d{4}-(0[1-9]|1[012])-([0][1-9]|[12][0-9]|3[01])$/;  
// 이메일 패턴 : 영숫자 한자리 + [-_.]? - 0 또는 한개 문자 + [0-9a-zA-Z] * 0개 이상 + 
// 			   @ + [0-9a-zA-Z] + ([-_.]?[0-9a-zA-Z])* + . + [a-zA-Z]{2,3} : 2~3자
var reg_email = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
// 핸드폰 패턴 : 
var reg_phone = /^\d{2,3}-\d{3,4}-\d{4}$/; 
// 숫자 패턴 :
var reg_num = /^[0-9]*$/;

// 정규 표현식 패턴을 검사하는 함수
// 사용법 : if(!regTest(reg_email, "#email", "영숫자.-@영숫자*.영숫자2~3의 이메일")) return false;
function regTest(reg, objStr, pattern){
	// 패턴에 맞지 않는경우의 처리
	if(!reg.test($(objStr).val())){
		alert("패턴에 맞지 않습니다. \n"+pattern+" 을(를) 입력하세요.");
		$(objStr).focus();
		return false;
	}
	return true;
}

// 필수항목 검사 함수 - 데이터가 비어있으면 true가 리턴된다.
// 필수항목 검사 사용법 : if(emptyCheck("#title", "제목")) return false;
function emptyCheck(objStr, itemName){
	// 공백 제거
	// val() - > 값을 가져오세요. getter()
	// val(값) - > 값을 넣어주세요. setter()
	$(objStr).val($(objStr).val().trim());
	// 선택한 항목 객체가 비어있으면 경고창을 띄운다.
	if(!$(objStr).val()) {
		alert(itemName + "은(는) 필수 입력 항목입니다.");
		// 커서를 입력란에 위치시킨다.
		$(objStr).focus();
		return true; // 데이터가 비어있으면 true가 리턴됨.
	}
	return false; // 데이터가 존재하면 true가 리턴됨.
}

// 길이 제한 함수
// 사용법 : if(!lengthCheck("#title", "제목", 4, 100)) return false;
function lengthCheck(objStr, itemName, minLen, maxLen){
	
	//제목은 4~100 까지 사용가능
	var len = $(objStr).val().length;
	if(!(len >= minLen && len <= maxLen)) {
		alert(itemName + "은(는) " + minLen + "~" + maxLen + "자 이내로 작성하셔야 합니다.");
		$(objStr).focus()
		return false; 
	}
	return true;
}

// 제한된 길이가 되면 자동으로 다음 입력란에 위치되는 함수 
// 사용법 : $(function() { $("#birth").keyup(function(){ autoNext("#birth", "#tel", 10); })
function autoNext(objStr, nextObjStr, len){
	$(objStr).keyup(function(){
	if($(objStr).val().length >= len) $(nextObjStr).focus();
	});
}