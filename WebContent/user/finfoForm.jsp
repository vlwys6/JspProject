<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="mainstyle.css">

<script type="text/javascript">

<%
String context = request.getContextPath();
%>
function chk(){
	if(!frm.pname.value){
   		alert("반려 이름을 입력하세요.")
 		frm.pname.focus();	
		return false;
	}
 	if(!frm.name.value){
   		alert("사용자 이름을 입력하세요.")
 		frm.name.focus();	
		return false;
	}
 	if(!frm.email.value){
   		alert("이메일을 작성하세요.")
 		frm.email.focus();	
		return false;
	}
}
function openInfo() {
	var pname  = frm.pname.value;
	var name  = frm.name.value;
	var email = frm.email.value;
	
	
	$.ajax({
		url:"<%=context%>/ajaxTest2.do",
		data : {pname : pname , name  : name ,email :email },
		dataType:'text',
		success:function(data){
			if( data == '0') {
				$('#msg').html("일치하는 정보가없습니다. ");
				$('#msg1').html("회원가입을 해주세요. ");
			}
			else $('#msg').html("당신의 아이디는 :  "+ data);
			
		}
		
		
		
		})
	}
	





</script>
</head>
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
        .btn{
        float: right;
        }
</style>

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
      
      


       <div class="container" style="margin-top:110px; padding-bottom:110px;">
       
        <h5><span>아이디 찾기 </span> 페이지입니다.</h5>
        
        <hr /><br />
        <form action="loginPro.do" method="post" name = "frm" onsubmit="return chk()">
            <input type="text" placeholder=" &nbsp;반려 이름" name="pname" required style="height:30px; width: 495px" ><br /><br />
            
       	    <input type="text" placeholder=" &nbsp;회원 이름" name="name" required style="height:30px; width: 495px" /><br /><br />
       	    
       	    <input type="email" placeholder=" &nbsp;이메일" name="email" required style="height:30px; width: 495px" /><br /><br />
       	    
       	    <input type="button" class="btn" value="확인" onclick="openInfo()" name="check" >
       	   
       	    <span id = "msg"></span>
       	    <span id = "msg1"></span>
       	   <br/><br/>
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