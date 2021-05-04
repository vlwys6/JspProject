<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">
<style type="text/css">
	@font-face{
		font-family: "SCDream4";
		src: url("fonts/SCDream4.otf") format("opentype");
		font-style: normal;
		font-weight: normal;
	
	}
	body{font-family: 'SCDream4';}
	input.bt{width:80px; height:40px; 
		  vertical-align:middle;
		  text-align:center;
		  background-color:#2E8B57;
		  border:#2E8B57;
		  color:white;
		  border-radius:5px;
		  margin:20px;
		  display:inline-block;
			  }
	
</style>
</head>
<body>
<div>
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
     <p><p><p> </div>
<div class="out" style="padding-bottom:144px;">

	<div id="warning" style="width:100%; text-align:center;">

	<div><img alt="warning" src="css_img/warning.png" style="width:100px; height:100px; margin-top:130px;"></div>
	<div style="font-size:18px; margin:10px;">댓글을 삭제하시겠습니까?</div>


	<div>
		<input class="bt" type="button" value="예" onclick="location.href='tipCommDeletePro.do?pageNum=${pageNum}&bd_num=${bd_num }&board_id=${board_id }&comm_num=${comm_num }&id=${id }&commPageNum=${commPageNum }&type_num=${type_num}'">
		<input class="bt" type="button" value="아니오" onclick="history.back()">
	</div>
	
	</div>
	
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