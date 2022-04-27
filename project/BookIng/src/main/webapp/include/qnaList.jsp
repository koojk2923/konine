<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default" id="shadow2">
	<div class="panel-heading" style="color:white;">
		<h3><b class="glyphicon glyphicon-question-sign" id="font2"></b>
		<b><span style="font-size: 25px;" id="font2" >Q&A</span></b>
		<a href="/qna/list.jsp" class="btn btn-info" style="float: right;">더보기&nbsp;>></a></h3>
	</div>
	<div class="panel-body">
		<table class="table">
			<c:forEach items="${qnaList }" var="vo">
				<tr class="qnaDataRow" data-no="${vo.no }">
					<td class="no">${vo.no}</td>
					<td>${vo.title}</td>
					<td>${vo.name }(${vo.id })</td>
					<td>${vo.writeDate }</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
	</div>
</div>
