<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>반려, 세상 모든 집사들의 커뮤니티</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="mainstyle.css">

<style type="text/css">

  table {
        width: 100%;
        border: 1px solid #333333;
      }
      td {
        padding: 10px;
        border: 1px solid #333333;
        background-color: #FDF8F2;
        text-align: center;
      }
	.a{
		border-spacing: 30px;
	}
	
	
	
	
	
/* 메인 베스트 이미지 마우스오버 효과 */
.gallerylist {max-width:460px;
			  width:100%;
			  margin-top:30px; 
			  text-align:left;}

.gallerylist > ul {font-size:0;}

.gallerylist > ul > li {display:inline-block;
						vertical-align:top;
						width:50%;}
						
.gallerylist > ul > li > a {display:block;
							width:auto;
							text-decoration: none; 
							margin:5px;}
							
.gallerylist > ul > li > a .screen {position:relative;
									overflow:hidden;}
																									
.gallerylist > ul > li > a .screen .top {position:absolute;
										 bottom:150%;
										 left:20px;
										 align: center;
										 z-index:2;
										 color:#fff;
										 font-size:20px;
										 font-weight:900;
										 /* 자연스러운 애니메이션효과 */
										 transition:all .35s;
										 width:160px;
										 overflow:hidden; /* 오버되면 숨겨라 */
										 text-overflow:ellipsis; /* 텍스트가 오버되면  ...으로 변경해라 */
										 white-space:nowrap;}/* 공백이 있으면 다음줄로 넘기지 말고 한줄로 연결해라 */
										 
.gallerylist > ul > li > a .screen .bottom {position:absolute;
											top:150%;
											left:20px;
											z-index:2;
											color:#fff;
											font-size:13px;
											transition:all .35s;}
											
.gallerylist > ul > li > a .screen img {width:100%;}
											
											/* 가상요소 필수값 */
.gallerylist > ul > li > a .screen::after {content:'';
										   display:block;
										   position:absolute;
										   top:0;
										   left:0;
										   width:200px;
										   height:200px;
										   /*적색,녹색,청색,투명도*/
										   background:rgba(0,0,0,0.5);
										   /* 배치순서 속성. position 속성이 정해진 요소에서만 가능. */
										   z-index:0;
										   opacity:0;
										   transition:all .35s;}
.gallerylist > ul > li > a:hover .top {bottom:65%;}
.gallerylist > ul > li > a:hover .bottom {top:55%;}
.gallerylist > ul > li > a:hover .screen::after {opacity:1;}


.search{display: inline-block;}	
				
#tiptitle:hover {font-weight:bold;}






</style>

</head>
<body>

<div>
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
     <p><p><p> </div>
      
      
<!--    메인검색창  -->


		<form action="searchList.do" method="get" style="text-align:center; margin-top:40px; margin-bottom:40px;">
		    <div class="search"><input type="text" name="searchkeyWord" required="required" placeholder="검색어를 입력하세요" style="width: 335px; height: 36px; border: 1px solid #AA9172;"></div>
		    <div class="search"><input type="submit" value="✔" style="width: 60px; height:38px; border: 0;background: #AA9172; color: white;"></div>
     	</form>



 	
<!-- 본문 베스트 소개 --> 	
<section style="background-color:#F6F6F6; text-align:center; height:700px;">
<!-- 반려동물자랑 베스트 -->
 	<div class="gallerylist" style="display: inline-block; vertical-align: top;">
 		<div style="font-size:15px; width:410px; margin-left:45px; text-align:center; margin-top:40px;">반려동물자랑</div>
 		<div style="font-size:35px; width:410px; margin-left:45px; text-align:center; font-weight:bold;">BEST</div>
	<ul>
		<c:forEach var="bestListPet" items="${bestListPet}" begin="0" end="3">
		<li>
			<a href="petContent.do?bd_num=${bestListPet.bd_num}&pageNum=${pageNum}&type_num=100">
				<div class="screen">
					<div class="top">${bestListPet.bd_title}</div>	
					<div class="bottom"><img style="width:30px; height:30px; border-radius:20px; " src="${context}/${bestListPet.userpic}">${bestListPet.id}<p><p><img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/like.png"> ${bestListPet.bd_like}&nbsp;&nbsp;<img alt="image" style="width:15px; height:15px; object-fit:cover;" src="css_img/view.png"> ${bestListPet.bd_view}&nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${bestListPet.comments_count}</div>
					<img style="width:200px; height:200px; " src="${context}/${bestListPet.bd_pic}">
				</div>
				
			</a>
		</li>
		</c:forEach>
	</ul>
</div>

<!-- 팁앤노하우 베스트 -->
	<div class="gallerylist" style="display: inline-block; vertical-align: top;"> 
         <div style="font-size:15px; width:410px; margin-left:45px; text-align:center; margin-top:40px;">팁&노하우</div>
 		 <div style="font-size:35px; width:410px; margin-left:45px; text-align:center; font-weight:bold;">BEST</div>
 		 
 		 <div style="width:500px; height:410px; background-color:white; border-radius:10px; border: 1px solid #E4E4E4; margin-top:4px; vertical-align:middle;">
	        <c:forEach var="bestListTip" items="${bestListTip}" begin="0" end="5">
	            <div style="text-align: center; width:460px; margin-left:20px; margin-top:20px; margin-bottom:20px; ">
	            <p>
	            <div style="width:45%; text-align:left; display:inline-block; font-size:15px;"><a id="tiptitle" href="tipContent.do?bd_num=${bestListTip.bd_num}&pageNum=${pageNum}&type_num=200" style="text-decoration:none; color:black;">${bestListTip.bd_title}</a></div>
	            <div style="width:50%; text-align:right; display:inline-block; font-size:13px;"><img style="width:20px; height:20px; border-radius:20px; vertical-align:bottom;" src="${context}/${bestListTip.userpic}"> ${bestListTip.id}&nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/like.png"> ${bestListTip.bd_like}&nbsp;&nbsp;<img alt="image" style="width:15px; height:15px; object-fit:cover;" src="css_img/view.png"> ${bestListTip.bd_view}&nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${bestListTip.comments_count}</div>
	            
	            </div>
	            <hr style="border-bottom:0px; text-align:left; width:92%; border-top: 1px solid #E4E4E4;">
	        </c:forEach>
        </div>
    </div>
    
<!-- 스토어 베스트 -->
 	<div class="gallerylist" style="display: inline-block; vertical-align: top;">
 		<div style="font-size:15px; width:410px; margin-left:45px; text-align:center; margin-top:40px;">스토어</div>
 		<div style="font-size:35px; width:410px; margin-left:45px; text-align:center; font-weight:bold;">BEST</div>
	<ul>
		<c:forEach var="bestListStore" items="${bestListStore}" begin="0" end="3">
		<li>
			<a href="storeContent.do?bd_num=${bestListStore.bd_num}&pageNum=${pageNum}&type_num=300">
				<div class="screen">
					<div class="top">${bestListStore.bd_title}</div>	
					<div class="bottom" style="font-size:15px;" ><img style="width:20px; height:20px; border-radius:20px; vertical-align:bottom; " src="css_img/star.png">${bestListStore.s_score}
					&nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${bestListStore.comments_count}</div>
					
					<img style="width:200px; height:200px; " src="${context}/${bestListStore.bd_pic}">
				</div>
				
			</a>
		</li>
		</c:forEach>
	</ul>
</div>

</section>


<footer style="width:100%; height:80px; background-color:#2E8B57; vertical-align:top;">
	<div style="display:inline-block; vertical-align:center;"><img id="mainlogo_footer" src="css_img/main-logo.png"></div>
	<div class="footer" style="display:inline-block;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div>
	
</footer>




</body>

</html>