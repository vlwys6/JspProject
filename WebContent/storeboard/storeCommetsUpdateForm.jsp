<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">

</head>
<body>


<!-- 로고 -->
<header ><a href="main.do"><img id="mainlogo" src="css_img/main-logo.png" ></a></header>


<!-- 로그인 / 아웃 -->
<c:choose>
<c:when test="${ id == null || id == ''}">
<nav id="loginbefore">
	<div style="width:650px; text-align:left; color:black; font-size:13px; display:inline-block; vertical-align:bottom;">
		<div style="width:600px; "></div>
		<div style="width:600px; margin-bottom:5px;"></div> 
	</div>

	<div class="loginbefore">
		<div><a href="loginForm.do">로그인</a></div>
		<div><a href="join.do">회원가입</a></div>
	</div>
</nav>
</c:when>

<c:otherwise>
<nav id="loginafter" style="text-align: left;">
	<div style="width:650px; text-align:left; color:black; font-size:13px; display:inline-block; vertical-align:bottom;">
		<div style="width:600px; text-align:center; color:black; font-size:13px; "><a href="myPage.do" style="text-decoration: none; "><img src="${context}/${userinfo.userpic}" style="width:30px; height:30px; border-radius: 15px; vertical-align:bottom;"></a></div>
		<div style="width:600px; text-align:center; color:black; font-size:13px; margin-bottom:5px;">${userinfo.id}님 반갑습니다!</div> 
	</div>
	
	<div class="loginafter" style="width:650px; color:black; display:inline-block; text-align:right; ">		
		<div style="color:black; font-size:13px; display:inline-block; "><a href="csList.do">고객센터</a></div>
		<div style="color:black; font-size:13px; display:inline-block; "><a href="logout.do">로그아웃</a></div>
	</div>
	
</nav>
</c:otherwise>

</c:choose>

   
   <!-- 메인메뉴 베스트 이미지 마우스오버 효과 -->
<nav id="menu">
	
	<ul >
		<li><a href="petList.do">반려동물자랑</a></li>
		
		<li><a href="tipList.do">팁&노하우</a></li>
	
  	 	<li><a href="storeList.do">스토어</a></li>
    
   	    
 	</ul>
 </nav>
     <p><p><p>
     
     
  
  
  
     
<div style="width: 100%; padding-bottom:160px; text-align: center;">
	<div style="font-size:1.0em; width:100%; margin-top:115px;">스토어</div>
	<div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:40px;">댓글수정</div>
	

<form action="storeCommetsUpdatePro.do" method="post">
	<input type="hidden" name="bd_num" value="${comments.bd_num}">
    <input type="hidden" name="type_num" value="${comments.type_num}">
    <input type="hidden" name="comm_num" value="${comments.comm_num}">
    <input type="hidden" name="pageNum" value="${pageNum}">
    <input type="hidden" name="commPageNum" value="${commPageNum}">
	<textarea rows="3" cols="70" name="comm_cont" placeholder="댓글을 작성하세요" required="required" style="font-family: 'SCDream4';">${comments.comm_cont }</textarea><p>
	<input type="submit" value="댓글수정" style="border-radius:5px; border:#2E8B57; background-color:#2E8B57; width:5%; height:40px;  color: white; font-family:'SCDream4';"> 
	<input type="button" value="수정취소" onclick="history.back();" style="border-radius:5px; border:#2E8B57; background-color:#2E8B57; width:5%; height:40px; color: white; font-family:'SCDream4';">


</form>
</div>   
     




<footer style="width:100%; height:80px; background-color:#2E8B57; vertical-align:top;">
	<div style="display:inline-block; vertical-align:center;"><img id="mainlogo_footer" src="css_img/main-logo.png"></div>
	<div class="footer" style="display:inline-block;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div>
	
</footer>
     
</body>
</html>