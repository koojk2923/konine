<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	$(".recordDataRow").click(function() {
// 		alert("click");
		var no = $(this).data("no"); 
		var url = "/";
		if($(this).hasClass("recordDataRow")) url = url + "record";
		url += "/view.do?no=" + no + "&inc=1";
		location = url;
	});
});
</script>
</head>
<body>
<div class="container">
        <div class="row" id="note">
            <div class="col-md-12">
                <jsp:include page="/WEB-INF/views/include/recordList.jsp"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
    </div>

</body>
</html>