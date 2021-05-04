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

div.out{ width:100%;
		 text-align: center;
		}
		
div.in{ width:50%;
		display:inline-block;
		text-align: left;}
		
td.comm_cnt{color:#2E8B57; font-size:30px; font-weight:bold; vertical-align:baseline; }
td.comm_cnt1{vertical-align:baseline; }

hr{ border-bottom:0px; text-align:center;}

input.button{ width: 40px; height:20px; font-family: 'SCDream4'; color:white; border:#AA9172; background-color:#AA9172; border-radius: 5px; }

a{text-decoration: none; color: black;}
a:visited{text-decoration: none; color: black;}
a:hover{text-decoration: none; color: #AA9172;}

.star > img {width:80px; height:16px; margin-top:70px;}

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
	<div class="out" style="padding-bottom:100px; margin-top:100px; "> 
	
		<div class="in" style="background-color:#FDF8F2; width:910px; height:400px;">
	    <div style="text-align: center; font-weight:bold;">
	   		<div style=" display: inline-block;  width: 400px; height: 450px; text-align: center; font-weight:bold;">
	   			<div ><img src="${context }/${board.bd_pic}" width="400px;" height="400px;"></div>
		 	</div>
     
	     <!-- 오른쪽 내용 설명 -->
	     <div style=" display: inline-block; vertical-align: top; width: 500px; height: 400px; " >
	    
	    
	         <div class="star">
	           <c:if test="${board.s_score >= 0.0 &&  board.s_score < 0.5}">
	                    <img src="css_img/store_0-0_5.png">
	            </c:if>
	            <c:if test="${board.s_score >= 0.5 && board.s_score < 1.0 }">
	                    <img src="css_img/store_0.5-0_99.png">
	            </c:if>
	            <c:if test="${board.s_score >= 1.0 && board.s_score < 1.5}">
	            
	                    <img src="css_img/store_1-1_5.png">
	            </c:if>
	            <c:if test="${board.s_score >= 1.5 && board.s_score < 2.0}">
	                    <img src="css_img/store_1_5-2_0.png">
	            </c:if>
	            <c:if test="${board.s_score >= 2.0 && board.s_score < 2.5}">
	            
	                    <img src="css_img/store_2_0-2_5.png">
	            </c:if>
	            <c:if test="${board.s_score >= 2.5 && board.s_score < 3.0}">
	                    <img src="css_img/store_2.5_3.png" >
	            </c:if> 
	            <c:if test="${board.s_score >= 3.0 && board.s_score < 3.5}">
	                    <img src="css_img/store_3-3_5.png" >
	            </c:if> 
	            <c:if test="${board.s_score >= 3.5 && board.s_score < 4.0}">
	                    <img src="css_img/store_3_5-4.png" >
	            </c:if> 
	            <c:if test="${board.s_score >= 4.0 && board.s_score < 4.5}">
	                    <img src="css_img/store_4-4_5.png" >
	            </c:if> 
	            <c:if test="${board.s_score >= 4.5 && board.s_score < 5.0}">
	                    <img src="css_img/store_4_5-5.png">
	            </c:if>           
	            <c:if test="${board.s_score == 5}">
	                    <img src="css_img/store_star_5.png" >
	            </c:if>
	            ${board.s_score}
	          </div>
	          
	         <div style=" font-size: 2.0em; margin-bottom:30px; color:#DEB98B; "> ${board.bd_title} </div>
	         <!--  <hr style="width:90%;"> -->
	       	 <p>
	            
	         <div style="font-size: 1.8em; font-weight:bold; color:#48484D; margin-top:60px; margin-bottom:60px; color:black; font-style:italic;">    
	         <fmt:formatNumber value="${board.s_price}" pattern="#,###" />원 </div>
	            
	            <c:choose>
		            <c:when test="${id != null && id != ''}">
			            <form action="storeBuyWrite.do?pageNum=${pageNum}&bd_num=${board.bd_num}&type_num=${board.type_num}" method="post">
				            <input type="submit" value="구 매 하 기" style="margin-top:20px; width: 50%; height:30px; font-size:15px; color:white; border:#AA9172; background-color:#AA9172; border-radius: 5px;">         
				        </form>
		            </c:when>
		            <c:otherwise>
		                 <form action="loginForm.do">
				            <input type="submit" value="로 그 인" style="margin-top:20px; width: 50%; height:30px; font-size:15px; color:white; border:#AA9172; background-color:#AA9172; border-radius: 5px;">         
				        </form>
		            </c:otherwise>
	            </c:choose>
	   			</div>
	  		</div>
       </div>
     
     
      <hr style="width:60%;">
     <div style=" font-size: 1.5em; margin-top:80px;  margin-bottom:40px;"> ${board.bd_title} 상세설명 </div>
          
          <div style="margin-bottom:80px;"><pre>${board.bd_cont}</pre></div>
     
     
     <!-- 구분선 -->
     <hr style="width:60%;">
      
       <div style="margin: 30px;"></div>
       <div style="text-align: center;">
       <div class="in">
  
	    <!-- 아이디가 일치하면 -->
		<c:choose>
		<c:when test="${id.equals(board.id)}">
		<div class="out">
			<form style="display:inline-block;">
			<input class="button" type="button" value="수정" onclick="location.href='storeUpdate.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}'">
			</form>
			<form action="storeDeletePro.do" method="post" style="display:inline-block;">
                <input class="button" type="hidden" name="bd_num" value="${bd_num}">
                <input class="button" type="hidden" name="pageNum" value="${pageNum}">
                <input class="button" type="hidden" name="type_num" value="${type_num}">
                <input class="button" type="hidden" name="deletefile" value="/${board.bd_pic}"> 
                <input class="button" type="submit" value="삭제">             
         	</form>
         	<form style="display:inline-block;">
			<input class="button" type="button" value="목록" onclick="location.href='storeList.do'">	
			</form>
			
		</div>
		</c:when>
		<c:otherwise>		
		
		<div class="out"><input class="button" type="button" value="목록" onclick="location.href='storeList.do'"></div>
		</c:otherwise></c:choose>
		

       
       <!-- 댓글 부분 -->
		<div class="in"><table><tr><td class="comm_cnt">${commTotCnt}</td><td class="comm_cnt1">개의 상품후기</td></tr></table></div>	
       
       <!-- 댓글 작성 방법 3가지.. -->
		<c:choose>
			<c:when test="${id != null && stbuy.s_score > 0 }">
	        
		       <form action="storeCommetsWritePro.do?pageNum=${pageNum}" method="post">
		          
		         <input type="hidden" name="board_id" value="${board.id}">
		         <input type="hidden" name="bd_num"value="${bd_num}">
		         <input type="hidden" name="type_num" value="${type_num}">
		         	<input type="text" name="comm_cont" placeholder=" 상품후기를 작성하세요" required="required" style="width:88%;height:50px;font-size:15px; border: 1px solid #2E8B57; font-family: 'SCDream4'; border-radius: 5px;">
			<input type="submit" value="댓글달기" style="width: 10%; height:55px; font-family: 'SCDream4'; color:white; border:#2E8B57; background-color:#2E8B57; border-radius: 5px;">
		    
		       </form>  
	         </c:when>
	            
	         <c:when test="${id !=null && stbuy.s_score == 0.0}">
	            <div class="out" style="color:gray; margin:20px;">구매 후 평점을 남겨야만 댓글을 달수 있습니다.</div>
	            
	         </c:when>
	         <c:otherwise>
	            <div class="out" style="color:gray; margin:20px;">로그인 후에 댓글작성이 가능합니다 .</div>
	         </c:otherwise>
    	 </c:choose>
       
            
            </div>
            

       
       <p><p><p>
       <!-- 댓글이 있는 경우 -->
		<c:if test="${commTotCnt>0 }">
			<c:forEach var="commList" items="${commList }">
				<div class="in">
					<div style="width:750px;">
					<div style="width:500px; text-align:left; font-size:15px; vertical-align:baseline; display:inline-block;"><a href="myPage.do?comm_id=${commList.comm_id}"><img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/id.png"> ${commList.comm_id}</a><div style="font-size:11px; color:gray; vertical-align:baseline; display:inline-block; text-align:left;">&nbsp;&nbsp;${commList.comm_date}</div></div>
					<div style="width:200px; text-align:right; vertical-align:baseline; display:inline-block;"><font size="2px"><a href="storeCommetsLike.do?bd_num=${bd_num}&board_id=${board.id }&comm_id=${commList.comm_id }&pageNum=${pageNum }&type_num=300&comm_num=${commList.comm_num }&commPageNum=${commPageNum }"><img alt="image" style="width:12px; height:12px; object-fit:cover;" src="css_img/like.png"></a> ${commList.comm_like }</font></div>																																											
					</div>	
				</div>
				
				<div class="in">
					<table>
					<tr><td><pre style="font-family: 'SCDream4';">${commList.comm_cont }</pre></td></tr>
					</table>
				</div>
				
				<div class="in">
				
				<c:choose>
				<c:when test="${commList.comm_id == id }">	
						<div style="width:750px; display:inline-block;">
						<input class="button" type="button" value="수정" onclick="location.href='storeCommetsUpdate.do?bd_num=${commList.bd_num}&pageNum=${currentPage}&type_num=${commList.type_num}&comm_num=${commList.comm_num}&commPageNum=${commCurrentPage}'">
						<input class="button" type="button" value="삭제" onclick="location.href='storeCommetsDeletePro.do?bd_num=${commList.bd_num}&pageNum=${currentPage}&type_num=${commList.type_num}&comm_num=${commList.comm_num}&commPageNum=${commCurrentPage}'">
						<hr><!-- 구분선 -->
						</div>
				</c:when>
				<c:otherwise>
						<div style="width:750px; display:inline-block;">
						<hr><!-- 구분선 -->
						</div>
				</c:otherwise>
				</c:choose>
				<c:set var="commStartNum" value="${commStartNum -1 }" />			
				</div>
				
			</c:forEach>
		</c:if>	<p>
       
       
	<!-- 댓글이 없는 경우 -->
     <c:if test="${commTotCnt == 0}">
              댓글 데이터가 없습니다. <p>
      </c:if>
       
 
      <div style="margin: 10px;"></div>
      <div style="text-align: center;">
             <c:if test="${commStartPage > commBlockSize}">
                  <a href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}&commPageNum=${commStartPage-commBlockSize} ' style="font-size:13px;">[이전]</a>
             </c:if>
             <c:forEach var="i" begin="${commStartPage }" end="${commEndPage}">
                   <a href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}&commPageNum=${i}' style="font-size:13px;">[${i}]</a>
             </c:forEach>
             
             <c:if test="${commEndPage < commPageCnt}">
                <a href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}&commPageNum=${commStartPage+commBlockSize}' style="font-size:13px;">[다음]</a>
             </c:if>
         </div>  
         
         </div>
     </div>
         
    <footer style="width:100%; height:80px; background-color:#2E8B57; vertical-align:top;">
	<div style="display:inline-block; vertical-align:center;"><img id="mainlogo_footer" src="css_img/main-logo.png"></div>
	<div class="footer" style="display:inline-block;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div></footer>
	
</body>
</html>