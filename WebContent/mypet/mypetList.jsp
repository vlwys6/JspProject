<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
   String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>반려, 세상 모든 집사들의 커뮤니티</title>



<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" 
integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" 
crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" 
crossorigin="anonymous"></script>

<script type="text/javascript">



</script>

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


.btn-primary, 
.btn-primary:hover, 
.btn-primary:focus, 
.btn-primary:active, 
.btn-primary.active, 
.btn-primary.focus, 
.btn-primary:active, 
.btn-primary:focus, 
.btn-primary:hover, 
.open>.dropdown-toggle.btn-primary {
    background-color: #3bc0c3;
    border: 1px solid #3bc0c3;
}
.p-t-10 {
    padding-top: 10px !important;
}
.media-main a.pull-left {
    width: 100px;
}
.thumb-lg {
    height: 84px;
    width: 84px;
}
.media-main .info {
    overflow: hidden;
    color: #000;
}
.media-main .info h4 {
    padding-top: 10px;
    margin-bottom: 5px;
}
.social-links li a {
    background: #EFF0F4;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    display: inline-block;
    border-radius: 50%;
    -webkit-border-radius: 50%;
    color: #7A7676;
}
---------------------------------------------------------------------------------------------------------------------------------------------------------------
</style>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="mainstyle.css">
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
      <div style="width:600px; text-align:center; color:black; font-size:13px; "><a href="myPage.do" style="text-decoration: none; "><img src="${context}/${userInfo.userpic}" style="width:30px; height:30px; border-radius: 15px; vertical-align:bottom;"></a></div>
      <div style="width:600px; text-align:center; color:black; font-size:13px; margin-bottom:5px;">${userInfo.id}님 반갑습니다!</div> 
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
     
     
     
     
    <div style="padding-bottom:150px; text-align:center; background-color:#F6F6F6;">
	

	<div style="font-size:1.0em; width:100%; margin-top:50px;">마이페이지</div>
	<div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:90px;">반려동물</div>
	
	
      <c:if test = "${pet_list  == '[]'}">
   
      <div style="text-align: center; margin-top:140px; ">
      <img  style="width: 100px" height="100px" src="css_img/box.png" >
      <h3 style="margin-top:20px; margin-bottom:20px;">반려동물을 등록해주세요.</h3>
    
      <button class="btn btn-outline-primary" id = "upd" onclick = "location.href = 'mypetInsertForm.do' ">반려등록</button>
      </div>
      
     
      
      </c:if>

<div class="container bootstrap snippets bootdey" >
     
    <div class="row">
     <c:choose>
        <c:when test="${board_id == '' && comm_id == ''}">
           <c:forEach var="pet_list" items="${pet_list}" >
              <div class="col-sm-4">
                <div class="panel">
                   <div class="panel-body p-t-10">
                       <div class="media-main" style="background-color:white;">
                           <a class="pull-left" href="#">
                               <img  style="width: 120px; height:120px; border-radius: 60px;" src="<%=context  %>/${pet_list.p_pic}" >
                           </a>
                          
                           <div class="info">
                           
                           <div style="text-align: right; ">
                           <h4 >${pet_list.p_name}</h4>    
                               <h6> 특기 :  ${pet_list.p_hobby}</h6>
                               <h6 align="right">나이 : ${pet_list.p_age}</h6>
                               <h6 align="right">종 : <c:choose>
											                             <c:when test ="${pet_list.type_num == '401' }"> 강아지 </c:when>
											                             <c:when test ="${pet_list.type_num == '402' }"> 고양이 </c:when>
											                                <c:otherwise> 그 외의 종 </c:otherwise>
											                             </c:choose></h6>
                        </div>
                           </div>
                       </div>
                       <div class="clearfix"></div>
                       <hr>
                      
                   </div>
               </div>
           </div>
      
          </c:forEach>
         </c:when>
         
         <c:when test="${board_id != id && comm_id == ''}">
           <c:forEach var="pet_list_board" items="${pet_list_board}" >
              <div class="col-sm-4">
                <div class="panel">
                   <div class="panel-body p-t-10">
                       <div class="media-main">
                           <a class="pull-left" >
                               <img    style="width: 100px" height="130px" src="<%=context  %>/${pet_list_board.p_pic}" >
                           </a>
                          
                           <div class="info">
                           <div style="text-align: right;">
                               <h4>${pet_list_board.p_name}</h4>
                               <h6>특기 :  ${pet_list_board.p_hobby}</h6>
                               <h6 align="right">나이 : ${pet_list_board.p_age}</h6>
                               <h6 align="right">종 : <c:choose>
                                                                                 <c:when test ="${pet_list_board.type_num == '401' }"> 강아지 </c:when>
                                                                                 <c:when test ="${pet_list_board.type_num == '402' }"> 고양이 </c:when>
                                                                                    <c:otherwise> 그 외의 종 </c:otherwise>
                                                                                 </c:choose></h6>
                           </div>
                       </div>
                       </div>
                       <div class="clearfix"></div>
                       <hr>
                      
                   </div>
               </div>
           </div>
      
          </c:forEach>
         </c:when>
         
         
         <c:otherwise>
           <c:forEach var="pet_list_comm" items="${pet_list_comm}" >
              <div class="col-sm-4">
                <div class="panel">
                   <div class="panel-body p-t-10">
                       <div class="media-main">
                           <a class="pull-left" href="#">
                               <img    style="width: 100px" height="130px" src="<%=context  %>/${pet_list_comm.p_pic}" >
                           </a>
                          
                           <div class="info">
                           <div style="text-align: right;">
                               <h4>${pet_list_comm.p_name}</h4>
                               <h6>특기 :  ${pet_list_comm.p_hobby}</h6>
                               <h6 align="right">나이 : ${pet_list_comm.p_age}</h6>
                               <h6 align="right">종 : <c:choose>
                                                                                 <c:when test ="${pet_list_comm.type_num == '401' }"> 강아지 </c:when>
                                                                                 <c:when test ="${pet_list_comm.type_num == '402' }"> 고양이 </c:when>
                                                                                    <c:otherwise> 그 외의 종 </c:otherwise>
                                                                                 </c:choose></h6>
                           </div>
                       </div>
                       </div>
                       <div class="clearfix"></div>
                       <hr>
                      
                   </div>
               </div>
           </div>
      
          </c:forEach>
         </c:otherwise>
         
         
         
         
         
    </c:choose>
  </div> 
</div>
</div>
<footer style="width:100%; height:80px; background-color:#2E8B57; vertical-align:top; text-align:left;">
	<div style="display:inline-block; vertical-align:bottom;"><img id="mainlogo_footer" src="css_img/main-logo.png"></div>
	<div class="footer" style="display:inline-block; vertical-align:middle;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div>
	
</footer>

</body>

</html>