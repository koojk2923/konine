<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default" id="shadow2">
	<div class="panel-heading" style="color:white;">
		<h3><b class="glyphicon glyphicon-bullhorn" style="text-shadow: 0px 0 1px #000, 0 3px 1px #000, 1px 0 1px #000, 0 -1px 1px #000;"></b>
		<b><span style="font-size: 25px; text-shadow: 0px 0 1px #000, 0 3px 1px #000, 1px 0 1px #000, 0 -1px 1px #000;">공지사항</span></b>
		<a href="/notice/list.jsp" class="btn btn-info" style="float: right;">더보기&nbsp;>></a></h3>
	</div>
	<div class="panel-body">
		<table class="table">
			<c:forEach items="${noticeList }" var="vo">
				<tr class="noticeDataRow" data-no="${vo.no }">
					<!-- 				제목을 클릭하면 해당글로 페이지 이동	-->
					<td>&nbsp;${vo.no}&nbsp;</td>
					<td>&nbsp;${vo.title}&nbsp;</td>
					<td>&nbsp;${vo.startDate}&nbsp;</td>
					<td>&nbsp;${vo.endDate}&nbsp;</td>
					<td>&nbsp;${vo.updateDate}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
	</div>
</div>


