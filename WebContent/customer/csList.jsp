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

		table{
		width: 80%;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
	}
	
	
	.cstitle> td{ 
		background-color:rgba( 170, 145, 114, 0.9 );
		/* background-color:#AA9172; */
		color:white;
		width:100px;
		
	}
	td {border: 1px solid #E4E4E4;
		height:30px;}
	
	a { text-decoration:none; color: black;} 
	a:visited {text-decoration: none; color: black;}
	a:hover {text-decoration: none; color:#AA9172; font-weight:bold;}
	
	.cslist {font-size:13px;}


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
     
 <div style="width: 100%; text-align: center;">    

	
</div>	

											<!--  footer height  -->
<div style="width: 100%; text-align: center; padding-bottom:100px; background-color:#F6F6F6;">
	
	<div style="font-size:2.0em; width:100%; font-weight:bold; margin-top:50px;">고객센터</div>
	<div style="font-size:1.0em; width:100%; margin-bottom:20px;">Q&A</div>
	
	<!-- 글쓰기 -->
	<c:if test="${id != null }">
	
	<div >
		<div class="write" style="text-align:right; width:90%;">
			<a href="csWrite.do" style=" background-color:#2E8B57; color:white; text-decoration:none; border-radius:5px; font-size:18px;">&nbsp;글쓰기&nbsp;</a>
		</div><p>
	</div>
	</c:if>
	
	
<div>
	<table style="border:1px solid #E4E4E4;">
	<tr class="cstitle">
		<td>게시물 번호</td>
		<td style="width:300px;">제목</td>
		<td>작성자</td>
		<td style="width:200px;">작성일</td>
		<td>조회수</td>
		<td>답변여부</td>		
	</tr>
	<c:if test="${totCnt > 0 }">
		<!-- 게시물 -->
		<c:forEach var="csBoard" items="${list }">
				<tr class="cslist" >
				<td>${startNum }</td>
				<c:if test="${csBoard.comments_count>0  && csBoard.bd_pic != null}">
				<td><a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400">${csBoard.bd_title }</a>&nbsp;<img src="css_img/picture.png">
				<a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400" style="font-size:5px;"><img style="width:13px; height:10px;"src="css_img/comment.png">&nbsp;${csBoard.comments_count }</a></td>
				</c:if>
				<c:if test="${csBoard.comments_count>0 && csBoard.bd_pic == null}">
				<td><a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400">${csBoard.bd_title }</a>&nbsp;
				<a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400" style="font-size:5px;"><img style="width:13px; height:10px;"src="css_img/comment.png">&nbsp;${csBoard.comments_count }</a></td>
				</c:if>
				<c:if test="${csBoard.comments_count==0 &&csBoard.bd_pic != null}">
				<td><a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400">${csBoard.bd_title }</a>&nbsp;<img src="css_img/picture.png"></td>
				</c:if>
				<c:if test="${csBoard.comments_count==0 &&  csBoard.bd_pic == null}">
				<td><a href="csContent.do?bd_num=${csBoard.bd_num }&pageNum=${currentPage}&type_num=400">${csBoard.bd_title }</a></td>
				</c:if>
				<td>${csBoard.id }</td>
				<td>${csBoard.bd_date }</td>
				<td>${csBoard.bd_view }</td>
				<td>
				<c:if test="${csBoard.bd_yn == 'N' }">
				N
				</c:if>
				<c:if test="${csBoard.bd_yn == 'Y' }">
				Y
				</c:if>
				</td>
				</tr>
				<c:set var="startNum" value="${startNum -1 }" />
		</c:forEach>
	</c:if>

	<c:if test="${totCnt == 0 }">
		<tr><td colspan="6" style="height:150px;">게시물이 없습니다.</td></tr>
	</c:if>
	
	
</table>
</div><p>
	<c:if test="${searchkeyWord == null}">
	<div style="text-align: center; margin-bottom:100px;">
		<c:if test="${startPage > blockSize }"> <!-- 1,11,21값을 주기위해 -->
			<a href="csList.do?pageNum=${startPage - blockSize }" style="font-size:13px;">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="csList.do?pageNum=${i }" style="font-size:13px;">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }"><!-- 10,20,30값을 주기위해 -->
			<a href="csList.do?pageNum=${startPage+blockSize }" style="font-size:13px;">[다음]</a>
		</c:if>
	</div>
	</c:if>
	

	</div> <!-- out div -->
	
	<footer style="width:100%; height:80px; background-color:#2E8B57; vertical-align:top;">
	<div style="display:inline-block; vertical-align:center;"><img id="mainlogo_footer" src="css_img/main-logo.png"></div>
	<div class="footer" style="display:inline-block;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div>
</footer>
</body>
</html>