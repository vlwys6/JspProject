<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/JavaScript" src="http://code.jquery.com/jquery-1.8.3.min.js" ></script>
<script type="text/javascript">
function getAddr(){
	// 적용예 (api 호출 전에 검색어 체크) 	
	if (!checkSearchedWord(document.form.keyword)) {
		return ;
	}

	$.ajax({
		 url :"https://www.juso.go.kr/addrlink/addrLinkApiJsonp.do"  //인터넷망
		,type:"post"
		,data:$("#form").serialize()
		,dataType:"jsonp"
		,crossDomain:true
		,success:function(jsonStr){
			$("#list").html("");
			var errCode = jsonStr.results.common.errorCode;
			var errDesc = jsonStr.results.common.errorMessage;
			if(errCode != "0"){
				alert(errCode+"="+errDesc);
			}else{
				if(jsonStr != null){
					makeListJson(jsonStr);
				}
			}
		}
	    ,error: function(xhr,status, error){
	    	alert("에러발생");
	    }
	});
}

function makeListJson(jsonStr){
	var htmlStr = "";
	// alert('jsonStr->'+jsonStr);
	htmlStr += "<a style='font-size:15px;  margin-left:70px;'>주소 : </a> <select name='buy_address'>";
	$(jsonStr.results.juso).each(function(){
		//alert('this.roadAddr->'+this.roadAddr);
		var optStr = "<option value='"+this.roadAddr+"'>";
		// FM대로 ''안에 ""로 감싸서해라 이거때문에 개망할번
		htmlStr += optStr;
		//htmlStr += '<option value='+this.roadAddr;
		//htmlStr += '>';
		htmlStr += this.roadAddr;		
		htmlStr += '</option>';
	});
	htmlStr += "</select>";
	htmlStr += "<p><a style='font-size:15px;  margin-left:70px;'>상세주소입력 : </a><input type='text' name='buy_detailaddr'>";
	/* htmlStr += "<hr>"; */
	htmlStr += "&nbsp;&nbsp;<input type='submit' value='주문하기'  style='background-color:#2E8B57;  border-color:#2E8B57; color:white;'>"
	$("#list").html(htmlStr);
}

//특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
function checkSearchedWord(obj){
	if(obj.value.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(obj.value) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			obj.value = obj.value.split(expText).join(""); 
			return false;
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
             		 "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);
		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(obj.value) ) {
			    alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				obj.value =obj.value.replace(regex, "");
				return false;
			}
		}
	}
	return true ;
}

function enterSearch() {
	var evt_code = (window.netscape) ? ev.which : event.keyCode;
	if (evt_code == 13) {    
		event.keyCode = 0;  
		getAddr(); 
	} 
}
</script>




<link rel="stylesheet" type="text/css" href="mainstyle.css">

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




<!-- 게시물 -->







<div style="padding-bottom:80px; width:100%; text-align:center; ">
	
	<div style="font-size:1.0em; margin-top:50px;">스토어</div>
	<div style="font-size:2.0em; font-weight:bold; margin-bottom:40px;">상품주문/결제</div>
	

	<div style="width:50%; margin-left:375px; text-align:center; border:1px solid #999999; background-color:white;">
	<div style="font-size:1.5em; background-color:#F4F4F4;">주문상품정보</div>
	
	
	<div style="text-align: center; font-weight:bold;">
	   		<div style=" display: inline-block;  width: 300px; height: 300px; text-align: center; font-weight:bold;">
	   			<div ><img src="${context }/${board.bd_pic}"  style="width:300px; height:300px; margin-top:20px; margin-bottom:20px;"></div>
		 	</div>
     
	     <!-- 오른쪽 내용 설명 -->
	     <div style=" display: inline-block; vertical-align: top; width: 300px; height: 100px; margin-top:90px; " >
	         <div style="font-size:15px; margin-bottom:2px;">상품번호: ${bd_num}</div>
	         <div style=" font-size: 1.5em; margin-bottom:30px; color:#DEB98B; "> ${board.bd_title} </div>
		         <div style="font-size: 1.5em; font-weight:bold; color:#48484D; margin-top:60px; margin-bottom:60px; color:black; font-style:italic;">    
				<fmt:formatNumber value="${board.s_price}" pattern="#,###" />원
	   			</div>
	  		</div>
       </div>

	
	
	     <div style="text-align: left;">
     
      <form action="storeBuyWritePro.do" method="post">
           <input type="hidden" name="board_id" value="${board.id}">
           <input type="hidden" name="bd_num" value="${bd_num}">
           <input type="hidden" name="type_num" value="${type_num}">
           <input type="hidden" name="pageNum" value="${pageNum}">
           <input type="hidden" name="s_price" value="${board.s_price}">
           <a style="font-size:15px;  margin-left:70px; ">수량선택:</a> <input type="number" name="s_cnt" style="width:40px; height:15px;">
           
           <p>
           <a style="font-size:15px;  margin-left:70px;">요청사항:</a> <select name="buy_info">
							                         <option value="부재중일때 전화주세요.">부재중일때 전화주세요.</option>
							                         <option value="부재중일때 경비실에 맡겨주세요.">부재중일때 경비실에 맡겨주세요.</option>
							                         <option value="부재중일때 문앞에 놓고가주세요.">부재중일때 문앞에 놓고가주세요.</option>
							                       </select>
							                       <hr style="width:80%; text-align:center; border-bottom:0px;">
                       
                   
           <div id="list"></div>
      </form><p>
      <!-- 주소를 공공시설API로 사용해서 적용하고싶다... -->
     
       <form name="form" id="form" method="post">
       	   <a style="font-size:15px;  margin-left:70px;">주소검색:</a> 
           <input type="hidden" name="currentPage" value="1"/> <!-- 현재페이지 -->
           <input type="hidden" name="countPerPage" value="100"/> <!-- 페이지당 출력갯수 -->
           <input type="hidden" name="resultType" value="json"/> <!-- 검색결과 형식 -->
           <input type="hidden" name="confmKey" value="devU01TX0FVVEgyMDIxMDEwODE0MDAzODExMDY0NDE="/> <!-- 승인키 -->
           <input type="text" name="keyword" onkeydown="enterSearch();" style="width:250px; height:15px;" /> <!-- 키워드 -->
           <input type="button" onclick="getAddr();" value="검색" style="background-color:#2E8B57;  border-color:#2E8B57; color:white;"/>
           
           
       </form>
       <a style="font-size:11px; color:gray;  margin-left:70px;">예: 도로명+건물번호(예:테헤란로5) | 읍/면/동/리+지번(예:서린동154)</a><p>
       
       </div>
	
	
	</div>
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