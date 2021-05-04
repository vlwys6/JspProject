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

.mb-0{	 width:240px;
		 overflow:hidden; 
		 text-overflow:ellipsis; 
		 white-space:nowrap;
		 text-align:left;}

.main-body {
    padding: 15px;
}
.card {
    box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
}

.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid rgba(0,0,0,.125);
    border-radius: .25rem;
}

.card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1rem;
}

.gutters-sm {
    margin-right: -8px;
    margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
    padding-right: 8px;
    padding-left: 8px;
}
.mb-3, .my-3 {
    margin-bottom: 1rem!important;
}

.bg-gray-300 {
    background-color: #e2e8f0;
}
.h-100 {
    height: 100%!important;
}
.shadow-none {
    box-shadow: none!important;
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

#upd{
    float : right;

}
#kiwoong12{
text-align: center;
}
.kiwoong551{
align-items:   right;}
body{
 width:1500px;
 text-align: center;
}
---------------------------------------------------------------------------------------------------------------------------------------------------------------
</style>
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
      <div style="width:600px; text-align:center; color:black; font-size:13px; "><a href="myPage.do" style="text-decoration: none; "><img src="<%=context  %>/${userInfo.userpic}" style="width:30px; height:30px; border-radius: 15px; vertical-align:bottom;"></a></div>
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
     <p><p><p> 
     
    </div>
    <c:choose>
                    
                    <c:when test="${board_id == null && comm_id == null}">
                      <div style="font-size:1.0em; width:100%; margin-top:50px;">마이페이지</div>
                      <div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:20px;">내정보</div>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <div style="font-size:1.0em; width:100%; margin-top:50px;">마이페이지</div>
                     <div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:20px;">내정보</div>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                    <div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:20px;">${userBoardInfo.name } 님 페이지</div>
                     
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                     <div style="font-size:2.0em; width:100%; font-weight:bold; margin-bottom:20px;">${userCommInfo.name } 님 페이지</div>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
     
  
    
<div class="container" style="padding-bottom:100px;">
    <div class="main-body">
    <p>
    <p>
    <p>
    
    
    
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                  <c:choose>
                  <c:when test="${id eq null}">
           <script type="text/javascript">
              alert('로그인을 하셔야 합니다.');
              location.href="loginForm.do";
           </script></c:when>
                    <c:when test="${board_id == null && comm_id == null}">
                       <img src="<%=context  %>/${userInfo.userpic}" alt="프로필 사진이 없습니다" class="rounded-circle" width="150">
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <img src="<%=context  %>/${userInfo.userpic}" alt="프로필 사진이 없습니다" class="rounded-circle" width="150">
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                     <img src="<%=context  %>/${userBoardInfo.userpic}" alt="프로필 사진이 없습니다" class="rounded-circle" width="150">
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                    <img src="<%=context  %>/${userCommInfo.userpic}" alt="프로필 사진이 없습니다" class="rounded-circle" width="150">
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                 
                    <div class="mt-3"><c:choose>
                    <c:when test="${id eq null}">
           <script type="text/javascript">
              alert('로그인을 하셔야 합니다.');
              location.href="loginForm.do";
           </script>
        </c:when>
                    <c:when test="${board_id == null && comm_id == null}">
                      <h4> ${userInfo.name } </h4>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <h4>${userInfo.name } </h4>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                     <h4>${userBoardInfo.name }</h4>
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                     <h4>${userCommInfo.name }</h4>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                    
                     
                    </div>
                  </div>
                </div>
              </div>
              <div class="card mt-3">
                <ul class="list-group list-group-flush">
                  <li id = "kiwoong" class="list-group-item d-flex justify-content-between align-items-center flex-wrap" >
                  <div class="card-body">
                 <h6 class="d-flex flex-column align-items-center text-center">
                     <c:choose>
                     <c:when test="${id eq null}">
           <script type="text/javascript">
              alert('로그인을 하셔야 합니다.');
              location.href="loginForm.do";
           </script>
        </c:when>
     
                       <c:when test="${board_id == null && comm_id == null}">
                           내가 쓴 게시물입니다.
                        <!-- 내가쓴글 -->
                       </c:when>
                       <c:when test="${board_id == id || comm_id == id}">
                           내가 쓴 게시물입니다.
                        <!-- 내가쓴글 -->
                       </c:when>
                       <c:when test="${board_id != id && comm_id == null}">
                          ${userBoardInfo.name } 님이 쓰신 게시물입니다.
                     <!-- 게시판아이디로넘어온 페이지 -->
                       </c:when>
                       <c:otherwise>
                          ${userCommInfo.name } 님이 쓰신 게시물입니다.
                        <!-- 댓글로 넘어온 아이디 -->
                       </c:otherwise>
                    </c:choose>
                    
                      
                    
                    <c:forEach var="myboardList" items="${userBoardList}">
                       <c:if test="${myboardList.type_num == 100}">
                       <li style="list-style: none;">
                       <hr>
                          <h6 class="mb-0">[반려동물자랑]&nbsp;<a href = "petContent.do?bd_num=${myboardList.bd_num}&type_num=100&pageNum=${pageNum}"> ${myboardList.bd_title }</a>&nbsp; </h6>
                           <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/like.png"> ${myboardList.bd_like }&nbsp;&nbsp;
                         <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${myboardList.comments_count }&nbsp;&nbsp;
                      
                      </li>
                  </c:if>
                  <c:if test="${myboardList.type_num == 200}">
                  <li style="list-style: none;">
                  <hr>
                          <h6 class="mb-0">[팁앤노하우]&nbsp;<a href = "petContent.do?bd_num=${myboardList.bd_num}&type_num=100&pageNum=${pageNum}"> ${myboardList.bd_title }</a>&nbsp; </h6>
                           <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/like.png"> ${myboardList.bd_like }&nbsp;&nbsp;
                         <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${myboardList.comments_count }&nbsp;&nbsp;
                         
                      </li>
                  </c:if>
                  <c:if test="${myboardList.type_num == 300}">
                  <li style="list-style: none;">
                  <hr>
                          <h6 class="mb-0">[스토어]&nbsp;<a href = "petContent.do?bd_num=${myboardList.bd_num}&type_num=100&pageNum=${pageNum}"> ${myboardList.bd_title }</a>&nbsp; </h6>
                           <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/like.png"> ${myboardList.bd_like }&nbsp;&nbsp;
                         <img alt="image" style="width:13px; height:13px; object-fit:cover;" src="css_img/comment.png"> ${myboardList.comments_count }&nbsp;&nbsp;
                      
                      </li>
                  </c:if>
                  </c:forEach> 
                 
                 
                 
                </h6></div>
                <div></div>
                  
                  </li>
                
                </ul>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                  <div class="row">
                    <div class="col-sm-3">
                    
                      <h6 >아이디</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                    <c:choose>
                    <c:when test="${id eq null}">
           <script type="text/javascript">
              alert('로그인을 하셔야 합니다.');
              location.href="loginForm.do";
           </script>
        </c:when>
                    <c:when test="${board_id == null && comm_id == null}">
                      <h4> ${userInfo.id } </h4>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <h4>${userInfo.id } </h4>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                     <h4>${userBoardInfo.id}</h4>
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                     <h4>${userCommInfo.id }</h4>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                    
                     
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 >비밀번호</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <c:choose>
        

        
                       <c:when test="${board_id == null && comm_id == null}">
                             ${userInfo.pw }
                        <!-- 내가쓴글 -->
                       </c:when>
                       <c:when test="${board_id == id || comm_id == id}">
                          ${userInfo.pw }
                        <!-- 내가쓴글 -->
                       </c:when>
                       <c:when test="${board_id == id && comm_id == null}">
                             ****
                        <!-- 게시판아이디로넘어온 페이지 -->
                       </c:when>
                       <c:otherwise>
                             ****
                        <!-- 댓글로 넘어온 아이디 -->
                       </c:otherwise>
                 </c:choose> 
     
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 >이름</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                    <c:choose>
                    <c:when test="${board_id == null && comm_id == null}">
                      <h6> ${userInfo.name } </h6>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <h6>${userInfo.name } </h6>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                     <h6>${userBoardInfo.name }</h6>
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                     <h6>${userCommInfo.name }</h6>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                      
                    </div> 
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6>이메일</h6>
                    </div> 
                    <div class="col-sm-9 text-secondary">
                    <c:choose>
                    <c:when test="${board_id == null && comm_id == null}">
                      <h6> ${userInfo.email} </h6>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                     <h6>${userInfo.email } </h6>
                       <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                     <h6>${userBoardInfo.email}</h6>
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                     <h6>${userCommInfo.email }</h6>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                   
                    </div>
                  </div>
                  <hr>
                   <c:choose>
        
                    <c:when test="${board_id == null && comm_id == null}">
                         <button class="btn btn-outline-primary" id = "upd" onclick = "location.href = 'myInfoUpdateForm.do' ">수정하기</button>
                        <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                         <button class="btn btn-outline-primary" id = "upd" onclick = "location.href = 'myInfoUpdateForm.do' ">수정하기</button>
                        <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id && comm_id == null}">
           
                        <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
           
                    </c:otherwise>
     </c:choose>
                  
                  
                  
                  
                  
                  
                 
               </div>
              </div>
              <div class="row gutters-sm">
                <div class="col-sm-6 mb-3"><div>
            
                  </div>
                </div>
                <div class="col-sm-6 mb-3">
                  <div class="card h-100" align="center">
                 
                    <div class="card-body" >
                       
                      <c:if test = "${pet_list  == '[]'}">
                      <h4> 반려 등록 해주세요.</h4>
                      </c:if>
                      <c:if test = "${pet_list  != '[]'}">
                      <h4>반려 조회</h4>
                      </c:if>
                      
                      <hr/>
                       <c:choose>
                     <c:when test="${board_id == null && comm_id == null}">
                       <c:forEach var="pet_list" items="${pet_list}" >
                        <small>${pet_list.p_name}</small>
                          <div align="center"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/age.png"> ${pet_list.p_age}&nbsp;&nbsp;       <c:choose>
                                                                              <c:when test ="${pet_list.type_num == '401' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/puppy.png"></c:when>
                                                                              <c:when test ="${pet_list.type_num == '402' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/cat.png"></c:when>
                                                                                 <c:otherwise><img alt="image" style="width:24px; height:24px; object-fit:cover;" src="css_img/etc.png"></c:otherwise>
                                                                              </c:choose>
                          <hr>
                        </div>
                        </c:forEach>
                       <!-- 내가쓴글 -->
                    </c:when> 
                    <c:when test="${board_id == id || comm_id == id}">
                     <c:forEach var="pet_list" items="${pet_list}" >
                        <small>${pet_list.p_name}</small>
                          <div align="center"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/age.png"> ${pet_list.p_age}&nbsp;&nbsp;     <c:choose>
                                                                              <c:when test ="${pet_list.type_num == '401' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/puppy.png"> 강아지 </c:when>
                                                                              <c:when test ="${pet_list.type_num == '402' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/cat.png"> 고양이 </c:when>
                                                                                 <c:otherwise><img alt="image" style="width:24px; height:24px; object-fit:cover;" src="css_img/etc.png"></c:otherwise>
                                                                              </c:choose>
                          <hr>
                        </div>
                        </c:forEach>
                       <!-- 내가쓴글 -->
                       
                    </c:when>
                    <c:when test="${board_id != id && comm_id == null}">
                          <c:forEach var="pet_list_board" items="${pet_list_board}" >
                              <small>${pet_list_board.p_name}</small>
                                <div align="center"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/age.png"> ${pet_list_board.p_age}&nbsp;&nbsp;  <c:choose>
                                                                              <c:when test ="${pet_list_board.type_num == '401' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/puppy.png"> </c:when>
                                                                              <c:when test ="${pet_list_board.type_num == '402' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/cat.png"></c:when>
                                                                                 <c:otherwise><img alt="image" style="width:24px; height:24px; object-fit:cover;" src="css_img/etc.png"></c:otherwise>
                                                                              </c:choose>
                          <hr>
                        </div>
                        </c:forEach>
                    
                    <!-- 게시판아이디로넘어온 페이지 -->
                    </c:when>
                    <c:otherwise>
                    <c:forEach var="pet_list_comm" items="${pet_list_comm}" >
                              <small>${pet_list_comm.p_name}</small>
                                <div align="center"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/age.png"> ${pet_list_comm.p_age}&nbsp;&nbsp;     <c:choose>
                                                                              <c:when test ="${pet_list_comm.type_num == '401' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/puppy.png"> </c:when>
                                                                              <c:when test ="${pet_list_comm.type_num == '402' }"><img alt="image" style="width:23px; height:22px; object-fit:cover;" src="css_img/cat.png"></c:when>
                                                                                 <c:otherwise><img alt="image" style="width:24px; height:24px; object-fit:cover;" src="css_img/etc.png"></c:otherwise>
                                                                              </c:choose>
                    <hr>
                    </div></c:forEach>
                       <!-- 댓글로 넘어온 아이디 -->
                    </c:otherwise>
                    </c:choose>
                      
                      
                      
                      
                        </div>
                        
     
                        </div>
                        
                 
                <button class="btn btn-outline-primary" style="float: right; id = "upd" onclick = "location.href = 'mypetList.do?board_id=${board_id}&comm_id=${comm_id }' ">반려조회</button>
                 <c:choose>
               
        <c:when test="${board_id == null && comm_id == null}">
           <button  class="btn btn-outline-primary" style="float: right; id = "upd" onclick = "location.href = 'mypetInsertForm.do' ">반려등록</button>
         <!-- 내가쓴글 -->
        </c:when>
        <c:when test="${board_id == id || comm_id == id}">
           <button class="btn btn-outline-primary" style="float: right; id = "upd" onclick = "location.href = 'mypetInsertForm.do' ">반려등록</button>
         <!-- 내가쓴글 -->
        </c:when>
        <c:when test="${board_id != id && comm_id == null}">
           
      <!-- 게시판아이디로넘어온 페이지 -->
        </c:when>
        <c:otherwise>
           
         <!-- 댓글로 넘어온 아이디 -->
        </c:otherwise>
     </c:choose>
                <c:choose>
        
                    <c:when test="${board_id == null && comm_id == null}">
                         <button class="btn btn-outline-primary" style="float: right; id = "upd" onclick = "location.href = 'myStBuyList.do' ">스토어조회</button>
                        <!-- 내가쓴글 -->
                    </c:when>
                    <c:when test="${board_id == id || comm_id == id}">
                         <button class="btn btn-outline-primary" style="float: right; id = "upd" onclick = "location.href = 'myStBuyList.do' ">스토어조회</button>
                        <!-- 내가쓴글 -->
                    </c:when>
                
                
                </c:choose>
                </div>
                 <div>
               
                </div>
                <br/>
                <hr/>
              </div>
            </div>
          </div>
        </div>
    </div>


<footer style="width:100%; height:80px; background-color:#2E8B57; text-align:left; vetical-align:top;">
	<div style="display:inline-block; vetical-align:bottom;"><img id="mainlogo_footer" src="css_img/main-logo.png" style="vertical-align:bottom;"></div>
	<div class="footer" style="display:inline-block; vetical-align:bottom;">회사명 : (주)반려&nbsp;&nbsp;|&nbsp;&nbsp;대표 : 배기웅&nbsp;&nbsp;|&nbsp;&nbsp;주소 : 서울특별시 강남구 테헤란로7길 7
	<div class="footer">사업자등록번호 : 123-45-67890</div>
	<div class="footer">Copyright 2021. BanRyeo. All rights reserved</div>	
	</div>
	
</footer>

</body>

</html>