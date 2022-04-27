<%@page import="com.BookIng.bookCart.service.BookCartMasterListService"%>
<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
// DB에서 데이터를 가져온다. 
BookCartVO vo = new BookCartVO();
BookCartMasterListService Service = new BookCartMasterListService();
List<BookCartVO> list = Service.service(vo);
System.out.println(list);

// System.out.println("아이디 세팅 : " + vo);
//el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니 목록</title>
<style type="text/css">
/* .dataRow:hover { */
/*   	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */
/* 	cursor: pointer; */
/* } */
#hidden {
	display: none;
}

.input_size_20 {
	width: 20px;
	height: 20px;
}

#total {
	font-size: 30px;
	border-left: none;
	border-right: none;
	border-bottom: none;
	border-top: none;
}

</style>
</head>
<body>
	<div class="container">
		<h2>관리자용 전체 장바구니</h2>
		<table class="table">
			<tr>
				<th>아이디</th>
				<th>도서번호</th>
				<th>책표지</th>
				<th>제목</th>
				<th>작가</th>
				<th>장르</th>
				<th>상품가격</th>
				<th>수량</th>
				<th>합계</th>
				<th>상품 삭제</th>
			</tr>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">장바구니가 비어있습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="vo">
					<tr class="dataRow cart_info_td">
						<td class="id">${vo.id}</td>
						<td class="no"><span style="color:#cccccc">[&nbsp;</span> 
						${vo.bookNo}
						<span style="color:#cccccc">&nbsp;]</span></td>
						<td><img src="${vo.cover}" alt="cover"
							style="width: 100px; height: 130px;" /></td>
						<td><a href="/bookBoard/view.jsp?bookNo=${vo.bookNo}" >${vo.title}</a></td>
						<td>${vo.writer}</td>
						<td>${vo.genre}</td>
						<td><fmt:formatNumber value="${vo.price}" pattern="#,###" />원</td>
						<td id="quantity" class="quantity" >
						<span class="count count-box">
								<button type="button" class="upBtn btn-warning"
								onclick="location.href='/bookCart/addCartM.jsp?cartNo=${vo.cartNo}&bookCount=1';">+</button>
								<input type="text" class="countInput" id="quantity" name="countInput"
								value="${vo.quantity}" readonly="readonly" style="width: 20px; border: none;">
								<button type="button" class="downBtn btn-warning"
								onclick="location.href='/bookCart/addCartM.jsp?cartNo=${vo.cartNo}&bookCount=-1';">-</button>
						</span></td>
						<td><input id="result" type="hidden" class="getTotalPrice"
							value="${vo.totalPrice}" /> 
							<fmt:formatNumber value="${vo.totalPrice}" pattern="#,###" />원</td>
						<td><a href="deleteBooksM.jsp?cartNo=${vo.cartNo }" class="btn btn-danger">삭제</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<table>
			<c:set var="total" value="0" />
			<c:forEach var="result" items="${list}" varStatus="status">
				<c:set var="total" value="${total + result.totalPrice}" />
			</c:forEach>
		</table>
		<div class="text-center">
			<div>
				전체 주문금액 : <input type="hidden" readonly="readonly" value="${total}">
				<div id="total">
					<span style="color:#cccccc">[&nbsp;</span> 
					<fmt:formatNumber value="${total}" pattern="#,###" />
					원<span style="color:#cccccc">&nbsp;]</span> 
				</div>
				<br>
			</div>
			&nbsp;&nbsp;
			<a href="/bookBoard/list.jsp" class="btn btn-default">상품 목록</a>
		</div>
	</div>
</body>