<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 이영환 -->
<!-- 작성일 : 2020-06-30 -->
<%@page import="com.BookIng.member.vo.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
   uri="http://www.opensymphony.com/sitemesh/decorator"%>
   
<%

LoginVO loginVO = (LoginVO) session.getAttribute("login");

%>   
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>도서 관리:<decorator:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Qna 게시판 아이콘 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style type="text/css">


 header { 
     background: AntiqueWhite;  
/*     position: fixed ;  */
/*     top: 0px;  */
/*       width: 0%   */
/* /*         left: 300px;  */ */
/*       right: 0;  */
 } 
footer {
   background: AntiqueWhite;
   padding: 10px;
}
body {
  padding-top: 140px;
  padding-bottom: 540px;
  /* 생략 */
}
pre {
   background: white;
   border: 100px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
   margin-bottom: 0;
   border:0;
   border-radius: 0;
   background: #ffbf80;
   box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
   }


/* Add a gray background color and some padding to the footer */
footer {
   background-color: AntiqueWhite;
   padding: 10px;
   border:0;
   color: #5BC8DA;
   box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}


.carousel-inner img {
   width: 100%; /* Set width to 100% */
   margin: auto;
   min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
   .carousel-caption {
      display: none;
   }
}

article {
   min-height: 400px;
   margin-top: 80px;
   margin-bottom: 120px;
}

#welcome {
   color: grey;
   margin: 0 auto;
}
#viewImg2 {
	width: 20px;
	box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.2), 0 2px 7px 0 rgba(0, 0, 0, 0.19);
}

#font { 
	color: white;
	font-size: 14px;
    font-weight: bold;
	text-shadow: 0px 1px 2px black;  
 }
</style>

<script type="text/javascript">
   $(document).ready(function() {
   });
</script>
<decorator:head/>
</head>
<body>
   <header>
      <nav class="navbar navbar-inverse navbar-fixed-top">
       <div ><a href="/main/main.jsp"><img src="/upload/siteImage/banner.png" style="width: 100%; height: 50%;" class="banner"/></a></div> 
       
         <div class="container-fluid">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse"
                  data-target="#myNavbar">
                  <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                     class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="/main/main.jsp" ><img src="/upload/siteImage/book.png" style="width: 130%; height: 130%;" /></a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
               <ul class="nav navbar-nav" >
                 <li><a href="/bookBoard/list.jsp" id="font"
                  class="glyphicon glyphicon-book"><strong>도서목록</strong></a></li>
                  <li><a href="/notice/list.jsp" id="font"
                  class="glyphicon glyphicon-bullhorn"><strong>공지사항</strong></a></li>
                  <li><a href="/qna/list.jsp" id="font"
                  class="glyphicon glyphicon-question-sign"><strong>Q&A</strong></a></li>
                  <% if(loginVO != null && loginVO.getGradeNo() == 9) { %>
                     <li><a href="/member/list.jsp" id="font"
                     class="glyphicon glyphicon-user"><strong>회원관리</strong></a></li>
                     <li><a href="/grade/list.jsp" id="font""
                     class="glyphicon glyphicon-education"><strong>등급관리</strong></a></li>
                  <% } %>
               </ul>
               
               
               
               
               
               
                 
                 <ul class="nav navbar-nav navbar-right">
                 <% if(loginVO == null){ %>
                     <li><a href="/member/loginForm.jsp" id="font"><span class="glyphicon glyphicon-log-in">로그인</span></a></li>
    				  <li><a href="/member/writeForm.jsp" id="font"><span class="glyphicon glyphicon-user">회원가입</span></a></li>
                  <% } else { %>
	<li><a href="/member/view.jsp?id=<%= loginVO.getId()%>" id="font">
     					&nbsp; <img src=" <%= loginVO.getPhoto() %>" id="viewImg2" class="img-rounded">
     					&nbsp;<%= loginVO.getName() %>(<%=loginVO.getGradeName()%>)
     					&nbsp;&nbsp;[ID:&nbsp;<%= loginVO.getId()%>]</a></li>
                  <li><a href="/bookCart/cartList.jsp"><span class="glyphicon glyphicon-shopping-cart" id="font">장바구니</span></a></li>
                   <li><a href="/member/logout.jsp"><span class="glyphicon glyphicon-log-out" id="font">로그아웃</span></a></li>
                 <% } %>
                </ul>
                 
                 
                 
                 
                 
                 
                 
                 
                 
            </div>
         </div>
      </nav>
    </header>

   <body>


   <article>
      <decorator:body />
   </article>
   <footer class="container-fluid text-center navbar navbar-inverse navbar-fixed-bottom">
      <p>Booking.com</p>
   </footer>
</body>
</html>