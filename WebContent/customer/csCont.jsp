<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">


<c:if test="${id != board.id && id != 'admin1234'}">
	<script type="text/javascript">
		alert("본인 게시글만 입장 가능합니다.");
		history.back();
	</script>
</c:if>


<style type="text/css">

div.out{ width:100%;
		 text-align: center;
		}
		
div.in{ width:50%;
		display:inline-block;
		text-align: left;}

hr{ border-bottom:0px; text-align:center;}

td.comm_cnt{color:#2E8B57; font-size:30px; font-weight:bold; vertical-align:baseline; }
td.comm_cnt1{vertical-align:baseline; }

input.button{ width: 40px; height:20px; font-family: 'SCDream4'; color:white; border:#AA9172; background-color:#AA9172; border-radius: 5px;}
img.img{width:13px; height:13px; object-fit:cover; vertical-align:middle;}
a{text-decoration: none; color: black;}
a:visited{text-decoration: none; color: black;}
a:hover{text-decoration: none; color: #AA9172;}
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
     
     
      	<!-- 게시물 view -->
	<div class="out" style="padding-bottom:100px;">
	<div class="in"  style="font-size:2.0em; font-weight:bold; margin-top:50px;">${board.bd_title}</div><p>
	<div class="in" style="font-size:12px; color:gray; margin:1px;">${board.bd_date} 
	<a href="myPage.do?board_id=${board.id}"><img class="img" alt="image" src="css_img/id.png"> ${board.id}</a>&nbsp;&nbsp;<img style="width:14px; height:14px; object-fit:cover; vertical-align:middle;" alt="image" src="css_img/view.png"> ${board.bd_view}<hr></div>
	<p><p><p>
	
	<c:if test="${board.bd_pic != null}">
	<div> <img alt="image" style="width:250px; height:250px; object-fit:cover; align:center;" src="${context}/${board.bd_pic}"></div>
	</c:if>
	<!-- 내용 -->
	<div><pre>${board.bd_cont}</pre></div> 
	<div class="in"><hr></div>
	
	
	
	<!-- 아이디가 일치하면 -->
	
	<div class="out">
		<c:if test="${board.id == id || id == 'admin1234' }">
			<input class="button" type="button" value="삭제" onclick="location.href='csDelete.do?pageNum=${pageNum}&bd_num=${bd_num}'" >
				<c:if test="${board.id == id}">
			<input class="button" type="button" value="수정" onclick="location.href='csUpdate.do?pageNum=${pageNum}&bd_num=${bd_num}&type_num=${type_num }'" >
			</c:if>
		</c:if>
			<input class="button" type="button" value="목록" onclick="location.href='csList.do?pageNum=${pageNum}&id=${id}'" >

	</div>
	
 
 		<!-- 댓글 부분 -->
		<div class="in"><table><tr><td class="comm_cnt">${commTotCnt}</td><td class="comm_cnt1">개의 댓글</td></tr></table></div>	
		
		
		
		<!-- 아이디가 빈칸이 아니고 null이 아닌경우 댓글 작성가능 -->
		<div class="in">
			<c:if test="${id != null }">
		<form action="csCommWritePro.do?pageNum=${pageNum}&bd_num=${bd_num}&board_id=${board.id }&type_num=${type_num }">
			<input type="hidden" name="type_num" value="${type_num }">
			<input type="hidden" name="bd_num" value="${bd_num }">
			<input type="hidden" name="board_id" value="${board.id }">
			<input type="hidden" name="pageNum" value="${pageNum }">
			<input type="hidden" name="commPageNum" value="${commPageNum }">
			
			
	
			<textarea rows="3" cols="101" name="comm_cont" placeholder="댓글을 작성하세요" required="required" style="margin-bottom:0.5%;    font-family: 'SCDream4';"></textarea><p style="margin-bottom: 0.5%; margin-top: 0.5%;">
         <div style="width:101%; text-align: right;">
         <input type="submit" value="댓글달기" style="border-radius:5px; width: 8%; height: 35px; border:#2E8B57; margin-right:5px; background-color:#2E8B57; color: white; font-family:'SCDream4';">
         </div>
				
			
		</form>
		</c:if>
		</div>
		<div class="al2">
		<c:if test="${id != null && commTotCnt == 0 }">
			답변이 아직 작성되지 않았습니다.<p>
		</c:if>		
		</div><p>
		
		
		
		<!-- 댓글이 있는 경우 -->
		<c:if test="${commTotCnt>0 }">
			<c:forEach var="commList" items="${commList }">
				<div class="in">
					<div style="width:750px;">
					<div style="width:500px; text-align:left; font-size:15px; vertical-align:baseline; display:inline-block;"><a href="myPage.do?comm_id=${commList.comm_id}"><img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/id.png"> ${commList.comm_id}</a><div style="font-size:11px; color:gray; vertical-align:baseline; display:inline-block; text-align:left;">&nbsp;&nbsp;${commList.comm_date}</div></div>
																																																
					</div>	
				</div>
				
				<div class="in">
					<table>
					<tr><td><pre style="font-family: 'SCDream4';">${commList.comm_cont }</pre></td></tr>
					</table>
				</div>
				
				<div class="in">
				

							
				<c:if test="${commList.comm_id == id || id == 'admin1234'}">
						<input class="button" type="button" value="삭제" onclick="location.href='csCommDelete.do?bd_num=${bd_num}&pageNum=${pageNum }&type_num=${type_num }&comm_num=${commList.comm_num }'" >			
						<c:if test="${commList.comm_id == id}">					
						<input class="button" type="button" value="수정" onclick="location.href='csCommUpdate.do?bd_num=${bd_num}&pageNum=${pageNum }&type_num=${type_num }&comm_num=${commList.comm_num }'">
						</c:if>
				</c:if>
				
				
				<c:set var="commStartNum" value="${commStartNum -1 }" />
				<hr>			
				</div>
				
			</c:forEach>
		</c:if>	<p>
	
	</p>
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