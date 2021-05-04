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
    <%
	String context = request.getContextPath();
%>

    $(document).ready(function() {

    	$('#btn_w').click(function() {

    		$(location).attr('href','write.php'); // 이용약관 

    	});

    });
    
/*     <script type="text/javascript">
    function chk(){
    	if(!frm.title.value){alert("제목 ?");
    	frm.title.focus(); return false;}
    	if(!frm.writer.value){alert("작성자 ?");
    	frm.writer.focus(); return false;}
    	if(!frm.content.value){alert("내용 ?");
    	frm.content.focus(); return false;}
    }

   */
    function chk(){
    	//var frm = document.userInfo;
/*     	alert("chk Start....")
    	alert("frm.id.value->"+frm.id.value );
    	
 */    	if(!frm.id.value){
       		alert("아이디를 입력하세요.")
     		frm.id.focus();	
    		return false;
    	}
    	if(frm.idDuplication.value != "check"){
    		alert("아이디 중복체크를 해주세요")
    		return false;
    	}
    	if(!frm.pw.value){
    		alert("비밀번호를 입력하세요.");
    		return false;
    	}
    	//비밀번호와 비밀번호 확인에 일겨된 값이 동일한지 확인
    	if(frm.pw.value != frm.pw2.value) {
    		alert ("비밀번호를 동일하게 입력하세요.");
    		return false;
    	}
    		
    }
    //아이디 닫아도 다시 중복체크 
    function inputIdChk(){
    	document.userInfo.idDuplication.value ="idUncheck";
    	
    }
     
	/* Controller */
	function openIdChk(){
	 	//alert("frm.id.value->"+frm.id.value );
	  	var var_id = frm.id.value;
		//alert("var_id->"+var_id);
		
		$.ajax({
			url:"<%=context%>/ajaxTest1.do",  
			data:{id : var_id},
			dataType:'text',
			success:function(data){
			//	alert(".ajax Data -> "+data);
				if (data == '0'){ 
					$('#msg').html("등록 가능한 아이디입니다."); 
					frm.idDuplication.value = "check";
					
				}
				else $('#msg').html("이미 등록된 아이디입니다.");
				  
				// $('#msg').html(data);         /* span  id Tag */
				/* $('#deptName').val("이미 사용중인 아이디."); */
			}
		});
	}
	   function goFirstForm() {
           location.href="main.do";
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
        #signup1 {
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
		<div style="width:600px; text-align:center; color:black; font-size:13px; margin-bottom:5px;">${userinfo.name}님 반갑습니다!</div> 
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
      
      
    <div class="container">
       
        <h5><span>회원 가입</span> 페이지입니다.</h5>
        <hr /><br />
        <form action="joinPro.do" method="post"  name="frm"  enctype="multipart/form-data" onsubmit="return chk()"  >
        
            <input type="text" placeholder=" &nbsp;아이디" name="id" required style="height:30px; width: 400px"  onkeydown="inputIdChk()">
          
            <input type="button" value="중복"  onclick="openIdChk()" name="check" required style="margin-left : 30px; width: 50px">
            <p>
            <span id="msg"></span>
            <p>
            
            <!-- 아이디 중복체크를 했는지 판단하기 위한 부분 그래서 히든 -->
			<input type="hidden" name="idDuplication" value = "idUncheck" ><br />
            
            <input type="password" placeholder=" &nbsp;비밀번호" name="pw" required style="height:30px; width: 495px" /><br /><br />
            <input type="password" placeholder=" &nbsp;비밀번호 확인" name="pw2" required style="height:30px; width: 495px" /><br /><br />
            <input type="text" placeholder=" &nbsp;이름" name="name" required style="height:30px; width: 495px" /><br /><br />
              &nbsp;  남<input type="radio" name="gender" value="man"> &nbsp;  &nbsp;  &nbsp; 여<input type="radio" name="gender" value="woman"><br /><br />


            
            <input type="email" placeholder=" &nbsp;이메일 주소" name="email" required style="height:30px; width: 495px" /><br />
            <p>
            프로필 사진 : <input type="file"  name="userpic"  required="required"/><br />
            
            
            <p>
            <input type="button" value="취소"    id="signup" onclick="goFirstForm()" > 
            <input type="submit" value="가입"     id="signup1" /><br /><br />
            </p>
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