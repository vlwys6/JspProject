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
  .out{
    width: 1300px;
    text-align: center;
    margin: 0 auto;
}

.in:hover, .best:hover{
	background-color: #EED9BE;
}
.in{ 
	display:inline-block;
	vertical-align: middle;
}
.in img {
	width: 300px;
	height: 300px;
}

hr{ border-bottom:0px; text-align:center; width:90%;}
a{text-decoration: none; color: black;}
a:visited{text-decoration: none; color: black;}
a:hover{text-decoration: none; color: #AA9172;}

body{ margin: 0 auto;
      width: 1500px;}


/* 리셋 CSS */
* {margin:0;padding:0;box-sizing:border-box;}
ul, li {list-style:none;}

/* 슬라이드 Style */
[name="slide"] {display:none;}
.slidebox {max-width:1500px;width:100%;margin:0 auto;text-align:center;}
.slidebox img {width:300px; height:300px;}
.slidebox .slidelist {
	white-space:nowrap;			/* 인라인형식으로 변경 */
	overflow:hidden;
}
.slidebox .slideitem {
	position:relative;
	display:inline-block;
	vertical-align:middle;
	width:100%;
	transition:all .35s;
}
.slidebox .slideitem label {
	position:absolute;
	z-index:1;
	top:42%;
	transform:translateY(-50%);
	padding:20px;
	border-radius:50%;
	cursor:pointer;
}

/* 좌우클릭 */
label.left {
	left:20px;
	background-color:#2E8B57;
	background-image:url('css_img/left-arrow.png');
	background-position:center center;
	background-size:50%;
	background-repeat:no-repeat;
}
label.right {
	right:20px;
	background-color:#2E8B57;
	background-image:url('css_img/right-arrow.png');
	background-position:center center;
	background-size:50%;
	background-repeat:no-repeat;
}

/* 첫번째 input이 체크가되면 ~input밑으로 slidelist 클래스 slideitem 클래스*/ /* transform 화면전환시 사용 */
[id="slide01"]:checked ~ .slidelist .slideitem {transform:translateX(0);animation:slide01 20s infinite;}
[id="slide02"]:checked ~ .slidelist .slideitem {transform:translateX(-100%);animation:slide02 20s infinite;}



@keyframes slide01 {
	0% {left:0%;}
	23% {left:0%;}
	25% {left:-100%;}
	48% {left:-100%;}
	100% {left:0%;}
}
@keyframes slide02 {
	
	0% {left:0%;}
	23% {left:0%;}
	75% {left:100%;}
	98% {left:100%;}
	100% {left:0%;}
}
    

.star > img { width:65px; height:13px;}

</style>
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
     
     
     
     
     
     <div style="width: 1500px;">
	
	<div style=" width:100%; text-align: center;">	
	
	
	<!-- BEST LIST -->
	
	<div style="margin:40px;">
	<div style="font-size:1.0em; text-align:center; " >실시간 베스트 상품</div>
	<div style="font-size:2.0em; text-align:center; font-weight:bold; " >BEST PRODUCT</div>
	</div>
		
	<div class="slidebox">
	<input type="radio" name="slide" id="slide01" checked>
	<input type="radio" name="slide" id="slide02">
	
	<ul class="slidelist">
		<li class="slideitem">
			<div>
				<label for="slide02" class="left"></label>
				
					<c:forEach var="bestlist" items="${bestStoreList}" begin="0" end="3">
		
						<div class="best" style="font-size:1.0em; color:gray; text-align:center; display:inline-block;">
						<a href='storeContent.do?bd_num=${bestlist.bd_num}&pageNum=${currentPage}&type_num=${bestlist.type_num}&commPageNum=${commPageNum}'>
						<img class="image" style="object-fit:cover; margin:10px; " src="${context}/${bestlist.bd_pic}"></a><p>
						<div style="font-size:1.1em; font-weight:bold; color:black;"><img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/best.gif"> ${bestlist.bd_title}</div>
						<fmt:formatNumber value="${bestlist.s_price}" pattern="#,###" />원
						
						
					<div class="star">
					<c:if test="${bestlist.s_score >= 0.0 &&  bestlist.s_score < 0.5}">
                    <img src="css_img/store_0-0_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 0.5 && bestlist.s_score < 1.0 }">
		                    <img src="css_img/store_0.5-0_99.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 1.0 && bestlist.s_score < 1.5}">
		            
		                    <img src="css_img/store_1-1_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 1.5 && bestlist.s_score < 2.0}">
		                    <img src="css_img/store_1_5-2_0.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 2.0 && bestlist.s_score < 2.5}">
		            
		                    <img src="css_img/store_2_0-2_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 2.5 && bestlist.s_score < 3.0}">
		                    <img src="css_img/store_2.5_3.png">
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 3.0 && bestlist.s_score < 3.5}">
		                    <img src="css_img/store_3-3_5.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 3.5 && bestlist.s_score < 4.0}">
		                    <img src="css_img/store_3_5-4.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 4.0 && bestlist.s_score < 4.5}">
		                    <img src="css_img/store_4-4_5.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 4.5 && bestlist.s_score < 5.0}">
		                    <img src="css_img/store_4_5-5.png">
		            </c:if>           
		            <c:if test="${bestlist.s_score == 5}">
		                    <img src="css_img/store_star_5.png" >
		            </c:if> ${bestlist.s_score} &nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${bestlist.comments_count} 
		            </div>
		            </div>		
						
					</c:forEach>
				<label for="slide02" class="right"></label>
			</div>
		</li>
		<li class="slideitem">
			<div>
				<label for="slide01" class="left"></label>
				
					<c:forEach var="bestlist" items="${bestStoreList}" begin="4" end="7">
		
						<div class="best" style="font-size:1.0em; color:gray; text-align:center; display:inline-block;">
						<a href='storeContent.do?bd_num=${bestlist.bd_num}&pageNum=${currentPage}&type_num=${bestlist.type_num}'>
						<img class="image" style="object-fit:cover; margin:10px; " src="${context}/${bestlist.bd_pic}"></a><p>
						<div style="font-size:1.1em; font-weight:bold; color:black;"><img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/best.gif"> ${bestlist.bd_title}</div>
						<fmt:formatNumber value="${bestlist.s_price}" pattern="#,###" />원

					
					<div class="star">
					<c:if test="${bestlist.s_score >= 0.0 &&  bestlist.s_score < 0.5}">
                    <img src="css_img/store_0-0_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 0.5 && bestlist.s_score < 1.0 }">
		                    <img src="css_img/store_0.5-0_99.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 1.0 && bestlist.s_score < 1.5}">
		            
		                    <img src="css_img/store_1-1_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 1.5 && bestlist.s_score < 2.0}">
		                    <img src="css_img/store_1_5-2_0.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 2.0 && bestlist.s_score < 2.5}">
		            
		                    <img src="css_img/store_2_0-2_5.png" >
		            </c:if>
		            <c:if test="${bestlist.s_score >= 2.5 && bestlist.s_score < 3.0}">
		                    <img src="css_img/store_2.5_3.png">
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 3.0 && bestlist.s_score < 3.5}">
		                    <img src="css_img/store_3-3_5.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 3.5 && bestlist.s_score < 4.0}">
		                    <img src="css_img/store_3_5-4.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 4.0 && bestlist.s_score < 4.5}">
		                    <img src="css_img/store_4-4_5.png" >
		            </c:if> 
		            <c:if test="${bestlist.s_score >= 4.5 && bestlist.s_score < 5.0}">
		                    <img src="css_img/store_4_5-5.png">
		            </c:if>           
		            <c:if test="${bestlist.s_score == 5}">
		                    <img src="css_img/store_star_5.png" >
		            </c:if> ${bestlist.s_score} &nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${bestlist.comments_count} 
		            </div>
		            </div>			
					
					</c:forEach>
				<label for="slide01" class="right"></label>
			</div>
		</li>
	
	</ul>
</div>

     
     
	<!-- 구분선 -->
	<div class="out"><div style="margin:50px; width:100%"><hr></div></div> 
	
	<!-- 일반게시글 LIST -->
	<div class="out">
		<div style="margin:40px;">
			<div style="font-size:1.0em; text-align:center; ">반려동물을 위한 스토어 제품</div>
			<div style="font-size:2.0em; text-align:center; font-weight:bold; " >STORE PRODUCT</div>
		</div>
		
	
		<div style="text-align:left;height:24px;padding: 0px 30px;">
			<span style="">총 상품 :  ${totCnt}</span>
			
			<!-- 관리자 아이디일때만 글쓰기가 나옴 -->
        	<c:if test="${id.equals('admin1234')}">
         		<span style="float:right;"><a href="storeWrite.do" style=" background-color:#2E8B57; color:white; text-decoration:none; border-radius:5px; font-size:18px;">&nbsp;글쓰기&nbsp;</a></span>
			</c:if>
		</div>
		
		
		
		<div style="display:flex; flex-wrap:wrap; width:100%; justify-content: center;">
			<!-- 사진을 4개씩 끊기위해 newLine 넣기 -->
			<c:set var="newLine" value="0"></c:set>
			<c:if test="${totCnt > 0}">
			<c:forEach var="list" items="${list}">
				<c:if test="${newLine == 4}">
					<c:set var="newLine" value="0"></c:set>
				</c:if>
				
				<div class="in">
					<div style="font-size:1.0em; color:gray; text-align:center; margin:10px;"><a href='storeContent.do?bd_num=${list.bd_num}&pageNum=${currentPage}&type_num=${list.type_num}'>
						
						<!-- 사진이 있으면..(생략고민) -->
						<c:choose>
						<c:when test="${list.bd_pic eq null || list.bd_pic == ''}">
						<div> </div>
						</c:when>
						<c:otherwise>
						<div style="height:auto; width:auto;"><img alt="image" style="object-fit:cover;" src="${context}/${list.bd_pic}"></div>
						</c:otherwise>
						</c:choose>
						</a>
						
						<div style="font-size:1.1em; font-weight:bold; color:black;"> ${list.bd_title}</div>
						<fmt:formatNumber value="${list.s_price}" pattern="#,###" />원
						
						<div class="star">
					<c:if test="${list.s_score >= 0.0 &&  list.s_score < 0.5}">
                    <img src="css_img/store_0-0_5.png" >
		            </c:if>
		            <c:if test="${list.s_score >= 0.5 && list.s_score < 1.0 }">
		                    <img src="css_img/store_0.5-0_99.png" >
		            </c:if>
		            <c:if test="${list.s_score >= 1.0 && list.s_score < 1.5}">
		            
		                    <img src="css_img/store_1-1_5.png" >
		            </c:if>
		            <c:if test="${list.s_score >= 1.5 && list.s_score < 2.0}">
		                    <img src="css_img/store_1_5-2_0.png" >
		            </c:if>
		            <c:if test="${list.s_score >= 2.0 && list.s_score < 2.5}">
		            
		                    <img src="css_img/store_2_0-2_5.png" >
		            </c:if>
		            <c:if test="${list.s_score >= 2.5 && list.s_score < 3.0}">
		                    <img src="css_img/store_2.5_3.png">
		            </c:if> 
		            <c:if test="${list.s_score >= 3.0 && list.s_score < 3.5}">
		                    <img src="css_img/store_3-3_5.png" >
		            </c:if> 
		            <c:if test="${list.s_score >= 3.5 && list.s_score < 4.0}">
		                    <img src="css_img/store_3_5-4.png" >
		            </c:if> 
		            <c:if test="${list.s_score >= 4.0 && list.s_score < 4.5}">
		                    <img src="css_img/store_4-4_5.png" >
		            </c:if> 
		            <c:if test="${list.s_score >= 4.5 && list.s_score < 5.0}">
		                    <img src="css_img/store_4_5-5.png">
		            </c:if>           
		            <c:if test="${list.s_score == 5}">
		                    <img src="css_img/store_star_5.png" >
		            </c:if> ${list.s_score} &nbsp;&nbsp;<img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${list.comments_count} 
					</div>	
				</div>	
						
					</div>
				 <c:set var="newLine" value="${newLine + 1}"></c:set>
			</c:forEach>
			</c:if>
		</div>
	
	</div>
	
	
	
	
	<!-- 리스트 없으면 -->
	<c:if test="${totCnt == 0 }">
		<div> 데이터가 없습니다. </div>
	</c:if>	
	
	<!-- 리스트 페이지 번호 -->
	  <div style="text-align: center;">
       	<c:if test="${searchkeyWord == null}">
		<c:if test="${startPage > blockSize }"> <!-- 1,11,21값을 주기위해 -->
			<a href="storeList.do?pageNum=${startPage - blockSize }" style="font-size:13px;">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="storeList.do?pageNum=${i }" style="font-size:13px;">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }"><!-- 10,20,30값을 주기위해 -->
			<a href="storeList.do?pageNum=${startPage+blockSize }" style="font-size:13px;">[다음]</a>
		</c:if>
		</c:if>
	</div>
	
	<div style="text-align: center;">
		<c:if test="${searchkeyWord != null}">
		<c:if test="${startPage > blockSize }"> <!-- 1,11,21값을 주기위해 -->
			<a href="storeList.do?pageNum=${startPage - blockSize }" style="font-size:13px;">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="storeList.do?pageNum=${i }&searchkeyWord=${searchkeyWord}&searchValue=${searchValue}" style="font-size:13px;">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }"><!-- 10,20,30값을 주기위해 -->
			<a href="storeList.do?pageNum=${startPage+blockSize }" style="font-size:13px;">[다음]</a>
		</c:if>
		</c:if>	
	</div>
	
	
    <!-- 검색 -->
  	<div class="out" style="margin-top:20px; margin-bottom:50px;">
	<form action="storeList.do" method="get">
                  <select id="searchValue" name="searchValue" style="width: 60px;; height:30px; font-family: 'SCDream4'; border: 1px solid #AA9172;border-radius:5px;">
                     <option value="BD_TITLE">제목</option>
                     <option value="BD_CONT">내용</option>               
                    
                  </select>
                  <input id="searchkeyWord" name="searchkeyWord" type="text" style="width: 300px; height:30px; font-family: 'SCDream4'; border: 1px solid #AA9172; border-radius:5px;">
                  <input type="submit" value="검색" style="width: 60px; height:30px; font-family: 'SCDream4'; color:white; border:#AA9172; background-color:#AA9172; border-radius:5px;">
      </form>
     </div>
     </div>   <!-- out div -->
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