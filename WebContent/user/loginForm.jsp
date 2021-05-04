<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>


<script type="text/javascript">
</script>
</head>
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
	
	
	
  .container {
            width: 500px;
            margin: 40px auto;
            line-height: 16px;
        }
          h5 {
            text-align: center;
        }
        h5 span {
            color: teal;
        }
        .btn{
        float: right;
        }
        .idpw{
       text-align: center;
       text-decoration:none;
       color:black;
       font-size:13px;
        }
        .ipw{
        text-align: center; 
        font-size:13px;
        }



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
										 transition:all .35s;}
										 
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
<link rel="stylesheet" type="text/css" href="mainstyle.css">
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







<c:if test="${id != null }">
		<script>
			alert("이미 로그인 중입니다.");
			location.href = "home.do";
		</script>
		
	</c:if>
	
	

<c:if test="${result == -1 || result == 0 }">
		<script>
			alert("아이디 혹은 비밀번호가 틀렸습니다.");
			location.href = "loginForm.do";
		</script>
		
	</c:if>
       <div class="container" style="margin-top:110px; padding-bottom:110px;">
       
        <h5><span>로그인</span> 페이지입니다.</h5>
        <hr /><br />
        <form action="loginPro.do" method="post" >
            <input type="text" placeholder=" &nbsp;아이디" name="id" required style="height:30px; width: 495px" ><br /><br />
            
       	    <input type="password" placeholder=" &nbsp;비밀번호" name="pw" required style="height:30px; width: 495px" /><br /><br />
       	    <input type="submit" class="btn" value="로그인">
       	    
       	   <br/><br/>
       	   <div class = "ipw"><a href="finfo.do" class="idpw">아이디 찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp;
       	    <a href="finfopw.do" class="idpw">비밀번호 찾기</a></div><br/>
   </form>  
    <hr/>
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