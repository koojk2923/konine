<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 22.03.28 : PageNav 사용 -->
<%@ taglib prefix="pageObject" tagdir="/WEB-INF/tags" %> 
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="text-center">old90</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<table class="table">
				<c:forEach items="${recordList }" var="vo">
					  <div class="col-md-4 recordDataRow" data-no="${vo.no}" >
	   					 <div style="margin-bottom: -20px;">
	        				<img src="${vo.albumImage }" alt="Lights" style="width:100%">
	        			</div>
	        			</div>
				</c:forEach>
        
  				
			</table>
		</div>
	</div>
</div>