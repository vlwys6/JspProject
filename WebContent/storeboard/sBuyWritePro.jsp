<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="mainstyle.css">
<style type="text/css">
 
		
</style>
</head>
<body>


<!-- 로고 -->
<header><a href="main.do"><img id="mainlogo" src="css_img/main-logo.png" ></a></header>


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
     
     
     
     <div style="margin-top:50px; padding-bottom:50px; background-color:#F6F6F6;">
   
	 <div style="font-size:2.0em; text-align:center; font-weight:bold; margin-bottom:20px; margin-top:20px;" >구매 완료</div>
	
     <c:if test="${result > 0}">
          <script type="text/javascript">
                 alert('결제가 완료되었습니다.'); 
          </script>
        
                
          <div style="font-size:12px; text-align: left; margin-left:580px; border: 1px solid #E4E4E4; border-radius:5px; background-color:white; width:340px; height:520px;">
          
          <div style="font-size:1.5em; text-align:center; margin-top:25px; font-weight:bold; font-style:italic;">RECEIPT</div>
          
	          <div style=" margin-top:25px; ">
	          <div style="margin-left:10px;">DATE : ${storebd.bd_date}</div>
	          <hr style="border-bottom:0px; text-align:center; width:95%; ">
	          <div style="margin-left:10px; text-align:left; margin-bottom:50px;">
	          ${stbuy.sbuy_id}님 주문해주셔서 감사합니다♥</div>
	          <div style=" text-align:center;">
	          <img src="${context}/${storebd.bd_pic}" style="width:100px; height:100px;">
	          <p style="font-weight:bold;">${storebd.bd_title}</p>
	          <p style="font-style:italic;">${storebd.s_price}원<p>
	          수량: ${stbuy.s_cnt } <p>
	          배송지 : ${stbuy.buy_address}<p> </div>
	          <hr style="border-bottom:0px; text-align:center; width:95%; margin-top:50px;">
	          <div style="font-weight:bold; font-size:1.0em; margin-left:10px;">총 구매금액: <fmt:formatNumber value="${stbuy.buy_payprice }" pattern="#,###" />원</div>
	          </div>
          </div>
        
           
         
     </c:if>
     
     <c:if test="${result == 0}">
          <script type="text/javascript">
                 alert('결제가 실패했습니다.');
                 location.href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}';
          </script>          
     </c:if>
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