<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.BookIng.bookCart.vo.BookCartVO"%>
<%@page import="java.util.List"%>
<%@page import="com.BookIng.bookCart.service.BookCartListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
BookCartVO vo = new BookCartVO();
LoginVO loginVO = (LoginVO) session.getAttribute("login");
String id = loginVO.getId();
vo.setId(id);
BookCartListService Service = new BookCartListService();
List<BookCartVO> list = Service.service(vo);
System.out.println(list);

//el 객체는 getter를 사용해서 데이터를 꺼낸다.
request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결제 페이지</title>
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
<script type="text/javascript">
	function purchase() {
		// 3자리 마다 콤마를 찍어주는 표현식
		var total = document.getElementById("total").value;
		var totalC = total.toString()
		  .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		
		if (confirm(totalC +('원이 결제됩니다.'))) {  // confirm() : 사용자가 선택할 때 이용(확인, 취소) 
			alert("결제가 완료 되었습니다");
			document.purchaseForm.submit();
		} else {
			document.purchaseForm.reset();
		}
	}
	
</script>
</head>
<body>
	<div class="container">
		<h2>장바구니</h2>
		<table class="table">
			<tr>
				<th>책표지</th>
				<th>제목</th>
				<th>작가</th>
				<th>상품가격</th>
				<th>수량</th>
				<th>합계</th>
			</tr>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">상품이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="vo">
					<tr class="dataRow cart_info_td">
						<td class="no" id="hidden">${vo.bookNo}</td>
						<td class="id" id="hidden">${vo.id}</td>
						<td><img src="${vo.cover}" alt="cover"
							style="width: 100px; height: 130px;" /></td>
						<td><a href="/bookBoard/view.jsp?bookNo=${vo.bookNo}" >${vo.title}</a></td>
						<td>${vo.writer}</td>
						<td><fmt:formatNumber value="${vo.price}" pattern="#,###" />원</td>
						<td id="quantity" class="quantity">${vo.quantity}</td>
						<td><input id="result" type="hidden" class="getTotalPrice"
							value="${vo.totalPrice}" /> 
							<fmt:formatNumber value="${vo.totalPrice}" pattern="#,###" />원</td>
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
				전체 주문금액 : <input type="hidden" readonly="readonly" value="${total}" id="total" pattern="#,###" >
				<div id="total">
					<span style="color:#cccccc">[&nbsp;</span> 
					<fmt:formatNumber value="${total}" pattern="#,###" />
					원<span style="color:#cccccc">&nbsp;]</span> 
				</div>
				<br>
			</div>
			&nbsp;&nbsp;
			      	<form name="purchaseForm" action="/bookCart/deleteCart.jsp" >
		<div class="text-center">
			<a href="#" class="btn btn-info" onclick="purchase()" style="width: 100px; height:50px;"><span style="font-size: 25px">결제</span></a>
			<a href="/bookCart/cartList.jsp" class="btn btn-warning" style="width: 150px; height:50px;"><span style="font-size: 25px">주문 수정</span></a> 
		</div>
	</form>
		</div>
	</div>
</body>