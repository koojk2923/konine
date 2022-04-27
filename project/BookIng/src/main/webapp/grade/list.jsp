<%@page import="com.BookIng.grade.service.GradeListService"%>
<%@page import="com.BookIng.grade.vo.GradeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 데이터 수집할거 없다
// DB에서 데이터 가져오기
GradeListService service = new GradeListService();
/* List<GradeVO> list = service.service(); */
List<GradeVO> list = service.service();
System.out.println("list.jsp - list : " + list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>등급 리스트</title>
<style type="text/css">
th, td {
	/* border: 1px solid #555;
	padding: 5px; */
	
}
</style>
</head>
<body>
	<div class="container">
		<!-- jsp전체를 부트스트랩으로 덮기 -->
		<h2>등급 리스트</h2>
		<div class="form-group">
			<!-- 입력 레이아웃을 부트스트랩 형식으로 -->
			<form action="write.jsp" method="post">
				<!-- <input name="gradeNo" placeholder="번호" maxlength="2"
				pattern="\d{1,2}" required="required" size="3"> 부트스트랩 미적용-->
				<div class=" col-lg-1">
					<!-- 칸 크기 조절 -->
					<input type="text" name="gradeNo" required="required"
						placeholder="번호" class="form-control" id="gradeNo" size="3"
						pattern="\d{1,2}">
					<!-- 등급번호 작성 칸 size 속성은 위아래, 위의 class 속성은 너비-->
				</div>
				<div class=" col-lg-2">
					<input type="text" name="gradeName" required="required"
						placeholder="등급명" class="form-control" id="gradeName" size="3"
						pattern="[가-힣]{1,6}">
					<!-- 등급명 작성 칸 -->
				</div>
				<!--  <input
				name="gradeName" placeholder="등급명" pattern="[가-힣]{1,6}"
				required="required" size="8"> 부트스트랩 미적용-->
				<button class="btn btn-default">추가</button>
			</form>
		</div>



		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>등급명</th>
					<th>수정/삭제</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (GradeVO vo : list) {
				%>
				<div class="form-group">
					<form action="update.jsp" method="post">
						<tr>
							<td>
								<%-- <input value="<%=vo.getGradeNo()%>" name="gradeNo"
								readonly="readonly" size="3"> 부트스트랩 미적용--%>
								<div class=" col-lg-3">
									<input type="text" name="gradeNo" value="<%=vo.getGradeNo()%>"
										placeholder="번호" class="form-control" id="gradeNo" size="3"
										pattern="\d{1,2}">
									<!-- 등급번호 수정 칸 -->
								</div>
							</td>
							<td>
								<%-- <input value="<%=vo.getGradeName()%>" name="gradeName"
								size="10"> 부트스트랩 미적용 --%>
								<div class=" col-lg-6">
									<input type="text" name="gradeName"
										value="<%=vo.getGradeName()%>" placeholder="등급명"
										class="form-control" id="gradeName" size="3"
										pattern="[가-힣]{1,6}">
									<!-- 등급명 수정 칸 -->
								</div>
							</td>
							<td>
								<%-- <a href="updateForm.jsp?gradeNo=<%=vo.getGradeNo()%>"
								class="btn btn-default">수정</a> --%>
								<button class="btn btn-default">수정</button> 
								<a href="delete.jsp?gradeNo=<%=vo.getGradeNo()%>"
								class="btn btn-default">삭제</a>
							</td>
						</tr>
						</form>
			</div>
			</tbody>
			
			<%
			}
			%>
			<tr>
				<td colspan="3"><a href="writeForm.jsp" class="btn btn-default">등록</a></td>
			</tr>
		</table>
	</div>
</body>
</html>