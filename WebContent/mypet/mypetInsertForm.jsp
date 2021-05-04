<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="mainstyle.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SignUp</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript">
    function goFirstForm() {
        location.href="myPage.do";
    }    

</script>
    <style>
    
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
        .n {
            font-size: 13px;
        }
        #signup {
            background-color: rgb(255, 80, 90);
            color: white;
            border: 0;
            border-radius: 5px;
             float: right;
     
        }
        .bottom input {
            background-color: white;
            border: 0;
            color: teal;
            font-size: 16px;
        }
        i {
            color: lightgray;
        }
        
        input {
            border: 1px solid lightgray;
            border-radius: 70px;
            padding-left: 10px;            
        }
        .close{
        margin-left: 10px;
        }
     

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
     
     
      
    <div class="container" style="padding-bottom:80px; margin-top:80px;">
       
        <h5><span>반려 동물 등록</span> 페이지입니다.</h5>
        <hr /><br />
        <form action="mypetInsertPro.do" method="post" enctype="multipart/form-data">
            
            <input type="text" placeholder=" &nbsp; 반려이름 " name="p_name" required style="height:30px; width: 495px" /><br /><br />
            <input type="text" placeholder=" &nbsp; 0 살  " name="p_age" required style="height:30px; width: 495px" /><br /><br />
            <input type="text" placeholder=" &nbsp; 특기 " name="p_hobby" required style="height:30px; width: 495px" /><br /><br />
            <input type="radio" name="type_num" value="401" >댕댕이 <input type="radio" name="type_num" value="402"> 냥냥이 <input type="radio" name="type_num" value="403"> 그 외<br /><br /> 
          	반려 사진 : <input type="file"  name="userpic"  required="required"/><br />
    
            <input type="button" value="취소" id="signup" class = "close" onclick="goFirstForm()"> 
            <input type="submit" value="등록" id="signup" /><br /><br />
        </form>
        <hr />
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