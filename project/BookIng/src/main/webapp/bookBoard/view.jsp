<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="com.BookIng.bookCart.service.BookCartViewService"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@page import="com.BookIng.bookCart.service.BookCartAddService"%>
<%@page import="com.BookIng.bookBoard.vo.BookBoardVO"%>
<%@page import="com.BookIng.bookBoard.service.BookBoardViewService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
// bookNo 데이터를 받아 스트링 데이터로 선언한다.
String strBookNo = request.getParameter("bookNo");
// 받아온 스트링 데이터를 parseLong으로 캐스팅 해서 bookNo에 넣는다
long bookNo = Long.parseLong(strBookNo);
// DB에서 데이터를 가져온다. 
BookBoardViewService Service = new BookBoardViewService();
BookBoardVO vo = Service.service(bookNo);
LoginVO loginVO = (LoginVO) session.getAttribute("login");
System.out.println("view.jsp- vo : " + vo);
// 내용은 엔터를 적용시키기 위해서 줄바꿈 데이터(\n) 를 <br> 태그로 바꿔준다.
vo.setSummary(vo.getSummary().replace("\n", "<br>"));
// el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보 보기</title>
<script type="text/javascript">
	function addToCart() {
		if (confirm('해당 상품을 장바구니에 추가하겠습니까?')) { /* confirm() : 사용자가 선택할 때 이용(확인, 취소) */
			document.addForm.submit();
		} else {
			document.addForm.reset();
		}
	}
	function deleteBook() {
		if (confirm('해당 상품을 삭제 하시겠습니까?')) { /* confirm() : 사용자가 선택할 때 이용(확인, 취소) */
			document.addDelete.submit();
		} else {
			document.addDelete.reset();
		}
	}
</script>
<style type="text/css">
/* 이미지 스타일과 사이즈를 설정한다 */

#hidden {
	display: none;
}
/* 클래스 information,detail,photo 크기조정 */
th {
	width: 25%;
}

#bookNo {
	font-size: 12px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}
#title {
	font-size: 30px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

#price {
	font-size: 25px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

.table {
	font-size: 17px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

.detail {
	font-size: 25px;
}

#cover {
	width: 400px;
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.5), 0 10px 20px 0
		rgba(0, 0, 0, 0.5);
	margin: 20px;
}
</style>
</head>
<body>
	<!-- bootstrap을 이용해 보기좋게 만들어준다. -->
	<!--  view에서 출력할 데이터들을 받아온다. -->

	<div class="container-fluid text-left"></div>
	<h3><br></h3>
	<!-- 화면의 일부(7)할당. 사진 사진변경, 상세정보 표시 -->
	<div class="row">
		<div class="col-sm-2 text-left"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<label for="cover" class="control-label col-sm-2"></label> 
				<img alt="${vo.title}" src="${vo.cover}" class="img-rounded" id="cover">
				<!--  view에서 출력할 데이터들을 받아온다. -->
			</div>
			<div class="form-group">
				<label for="summary" class="control-label col-sm-2">줄거리</label>
				<div class="col-sm-10" id="summary">
					<p>${vo.summary}</p>
				</div>
			</div>
	</div>
      <!-- 화면의 일부(5)할당. 상품의 정보 표시-->
      <div class="col-sm-5">
         <div class="table">
         <div><br></div>
         <div><br></div>
         <div><br></div>
         	<div class="form-group">
			<label for="bookNo" class="control-label col-sm-2" id="bookNo">등록번호</label>
			<div class="col-sm-10" id="bookNo">
				<p>[${vo.bookNo}]</p>
			</div>
		</div>
         <div class="form-group">
			<br><label for="title" class="control-label col-sm-2">제목</label>
			<div class="col-sm-10" id="title">
				<p>${vo.title}</p>
			</div>
		</div>
		<!--  view에서 출력할 데이터들을 받아온다. -->
		<div class="form-group">
			<label for="writer" class="control-label col-sm-2">작가</label>
			<div class="col-sm-10" id="writer">
				<p>${vo.writer}</p>
			</div>
		</div>
		<!--  view에서 출력할 데이터들을 받아온다. -->
		<div class="form-group">
			<label for="genre" class="control-label col-sm-2">장르</label>
			<div class="col-sm-10" id="genre">
				<p>${vo.genre}</p>
			</div>
		</div>
		<!--  view에서 출력할 데이터들을 받아온다. -->
		<div class="form-group">
			<label for="publisher" class="control-label col-sm-2">출판사</label>
			<div class="col-sm-10" id="publisher">
				<p>${vo.publisher}</p>
			</div>
		</div>
		<!--  view에서 출력할 데이터들을 받아온다. -->
		<div class="form-group">
			<label for="pubDate" class="control-label col-sm-2">출간일</label>
			<div class="col-sm-10" id="pubDate">
				<p>${vo.pubDate}</p>
			</div>
		</div>
		<!--  view에서 출력할 데이터들을 받아온다. -->
		<div class="form-group">
			<br><label for="price" class="control-label col-sm-2">가격</label>
			<div class="col-sm-10" id="price">
				<p><fmt:formatNumber value="${vo.price}" pattern="#,###" />원</p>
				<br>
			</div>
		</div>
            </div> 

         	<form name="addForm" action="/bookCart/addCart.jsp?bookNo=${vo.bookNo}&bookCount=1"
		method="post">
		<div class="text-left">
			<a href="#" class="btn btn-info" onclick="addToCart()">주문하기</a>
			<!-- 장바구니 버튼 추가, 클릭시 /cart.jsp로 이동함 -->
			<a href="/bookCart/cartList.jsp" class="btn btn-warning">장바구니</a> 
			<a href="list.jsp" class="btn btn-default">상품 목록</a>
		</div>
	</form>
      </div>
   </div>
	<!-- 	수정과 삭제를 위한 버튼을 만든다. -->
	<c:if test="${!empty login && login.gradeNo == 9 }">	
	<form name="addDelete" action="delete.jsp?bookNo=${vo.bookNo}&oldCover=${vo.cover}" method="post">
		<div class="text-center">
				<a href="updateForm.jsp?bookNo=${vo.bookNo}" class="btn btn-default">수정</a>
				<a href="#" class="btn btn-default" onclick="deleteBook()">삭제</a>
		</div>
	</form>
	</c:if>
</body>
</html>