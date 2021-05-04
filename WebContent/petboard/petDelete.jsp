<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">
<style type="text/css">


input.bt,div.button {width:80px; height:40px; 
			  vertical-align:middle;
			  text-align:center;
			  background-color:#2E8B57;
			  border:#2E8B57;
			  color:white;
			  border-radius:5px;
			  margin:20px;
			  display:inline-block;
			  }
input.pw1 {width:200px; height:30px;
			  vertical-align:middle;
			  text-align:center;
			  border: 1px solid #2E8B57;

			  border-radius:2px;
			  }
			  
input.pw2 {width:50px; height:34px; 
			  vertical-align:middle;
			  text-align:center;
			  background-color:#2E8B57;
			  border:#2E8B57;
			  color:white;
			  border-radius:2px;

			  }
			  
hr{ border-bottom:0px;
	text-align:center; 
	width:30%;}
			  
div.out {width:100%; text-align:center; }


/* id가 delete인 버튼 숨기기 */
#delete{display:none;}

</style>

<!-- jquery 이용, yesbutton 클릭시 delete 버튼 보이기 -->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script>
	$(function () {
		$("#yesButton").click(function (e) {
			$("#delete").show();
			$("#warning").hide();
		});
	});
	

	</script>

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
     <p><p><p> </div>
	
<div class="out" style="padding-bottom:100px;">

	<div id="warning">

	<div><img alt="warning" src="css_img/warning.png" style="width:100px; height:100px; margin-top:100px;"></div>
	<div style="font-size:20px; font-weight:bold; margin:10px; color:#FF7761;">" ${board.bd_title} "</div>
	<div style="font-size:18px; margin:10px;">게시물을 정말로 삭제하시겠습니까?</div>
	<div style="font-size:18px;  margin:10px;">지금 게시물을 삭제하시면 다시 복구할 수 없습니다.</div>


	<div>
		<input class="bt" type="button" value="예" id="yesButton">
		<input class="bt" type="button" value="아니오" onclick="history.back()">
	</div>
	
	</div>

	
	<!-- 숨겨졌다 클릭시 보여야 하는 암호란 -->
	<div id="delete" style="margin-top:145px; margin-bottom:50px;"> 
		<form action="petDeletePro.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bd_num" value="${board.bd_num}">
			<input type="hidden" name="deletefile" value="/${board.bd_pic}">
			
			<img alt="password" src="css_img/password.png" style="width:100px; height:100px; ">
			<div style="font-size:25px; text-align:center;"></div>
			<div style="font-size:18px; color:#FF7761; margin:30px;">삭제하시려면 비밀번호를 입력해주세요.</div>
			<div style="display:inline-block;">
			<input class="pw1" type="password" name="pw" placeholder="비밀번호를 입력하세요">
			<input class="pw2" type="submit" value="확인"></div>
		</form>
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