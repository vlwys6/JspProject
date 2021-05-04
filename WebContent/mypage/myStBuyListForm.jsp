<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">
<style type="text/css">
.star > img {  width:100px;" height:20px;}

}
		
	td {border: 1px solid #E4E4E4;
		height:30px;}
	
	a { text-decoration:none; color: black;} 
	a:visited {text-decoration: none; color: black;}
	a:hover {text-decoration: none; color:#AA9172; font-weight:bold;}
	
	.buylist {font-size:13px;}



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
     
<div style="padding-bottom:200px; width:100%; text-align:center; ">
 <div style="font-size:1.0em; margin-top:100px;">스토어</div>
	<div style="font-size:2.0em; font-weight:bold; margin-bottom:20px;">주문상품정보</div>
     
     
     <div style="margin-left:130px; margin-bottom:10px; text-align:left;height:24px;padding: 0px 30px;">
			<span>총 주문상품 :  ${totCnt}</span>
	 </div>
	
     <table style="border:1px solid #E4E4E4; width: 80%; text-align: center; margin-left: auto; 	margin-right: auto;">
		<tr class="buytitle">
			<td style="width:200px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">주문일자</td>
			<td style="width:100px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">상품번호</td>
			<td style="width:100px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">수량</td>
			<td style="width:200px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">총 구매금액</td>
			<td style="width:450px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">배송주소 </td>
			<td style="width:200px; background-color:rgba( 170, 145, 114, 0.9 ); color:white;">나의 구매평점</td>		
		</tr>
	

		<!-- 게시물 -->
		<c:if test="${totCnt == 0}">
            <tr><td colspan="6" style="height:140px;">주문내역이 없습니다.</td></tr>
     	 </c:if>
     	 <!-- 0보다 크면 -->
     	 <c:if test="${totCnt > 0}">
		<c:forEach var="buyList" items="${buyList}">
				<tr class="buylist">
				<td>${buyList.buy_date}</td>
				<td>${buyList.bd_num}</td>
				<td>${buyList.s_cnt}</td>
				<td><fmt:formatNumber value="${buyList.buy_payprice}" pattern="#,###" />원</td>
				<td>${buyList.buy_address }</td>
				<td>
				   <c:if test="${buyList.s_score == 0 && id != 'admin1234'}">
                
                 		 
                 		 <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=0.5">
                         <img src="css_img/store_leftstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=1">
                         <img src="css_img/store_rightstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=1.5">
                         <img src="css_img/store_leftstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=2">
                         <img src="css_img/store_rightstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=2.5">
                         <img src="css_img/store_leftstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=3">
                         <img src="css_img/store_rightstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=3.5">
                         <img src="css_img/store_leftstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=4">
                         <img src="css_img/store_rightstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=4.5">
                         <img src="css_img/store_leftstar.png" style="width:10px; height:20px;"></a>
                         
                         <a href="myStBuyScoreUpdate.do?bd_num=${buyList.bd_num}&sbuy_num=${buyList.sbuy_num}&type_num=300&s_score=5">
                         <img src="css_img/store_rightstar.png" style="width:10px; height:20px;"></a>
           </c:if>
         
           <div class="star">
	            <c:if test="${buyList.s_score != 0 }">
	                
	                <c:if test="${buyList.s_score >= 0.0 &&  buyList.s_score < 0.5}">
		                    <img src="css_img/store_0-0_5.png">
		                </c:if>
		            <c:if test="${buyList.s_score >= 0.5 && buyList.s_score < 1.0 }">
		                    <img src="css_img/store_0.5-0_99.png">
		            </c:if>
		            <c:if test="${buyList.s_score >= 1.0 && buyList.s_score < 1.5}">
		                    <img src="css_img/store_1-1_5.png">
		            </c:if>
		            <c:if test="${buyList.s_score >= 1.5 && buyList.s_score < 2.0}">
		                    <img src="css_img/store_1_5-2_0.png">
		            </c:if>
		            <c:if test="${buyList.s_score >= 2.0 && buyList.s_score < 2.5}">
		                    <img src="css_img/store_2_0-2_5.png">
		            </c:if>
		            <c:if test="${buyList.s_score >= 2.5 && buyList.s_score < 3.0}">
		                    <img src="css_img/store_2.5_3.png" >
		            </c:if> 
		            <c:if test="${buyList.s_score >= 3.0 && buyList.s_score < 3.5}">
		                    <img src="css_img/store_3-3_5.png">
		            </c:if> 
		            <c:if test="${buyList.s_score >= 3.5 && buyList.s_score < 4.0}">
		                    <img src="css_img/store_3_5-4.png">
		            </c:if> 
		            <c:if test="${buyList.s_score >= 4.0 && buyList.s_score < 4.5}">
		                    <img src="css_img/store_4-4_5.png">
		            </c:if> 
		            <c:if test="${buyList.s_score >= 4.5 && buyList.s_score < 5.0}">
		                    <img src="css_img/store_4_5-5.png" >
		            </c:if> 
		            <c:if test="${buyList.s_score == 5}">
		                    <img src="css_img/store_star_5.png">
		            </c:if>
	            </c:if> 
	            </div>
	            <c:if test="${buyList.s_score == 0 && id == 'admin1234' }">
	            회원이 평점을 안남겼습니다.
	            </c:if>
				</td>
				</tr>
		</c:forEach>
		</c:if>
	</table>
 
    
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