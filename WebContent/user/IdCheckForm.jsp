<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		#wrap {
			width: 490px;
			text-align :center;
			margin: 0 auto 0 auto;
		}
		
		#chk{
			text-align :center;
		}
		
		#cancelBtn{
			visibility:visible;
		}
		
		#useBtn{
			 visibility:visible;
		}

	</style>
	
	<script type="text/javascript">
		
		// 아이디 중복체크
		function idCheck(){

			

			if (!id) {
				alert("아이디를 입력하지 않았습니다.");
				return false;
			} 
			else ((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
				alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
				return false;
			}
		
		}
		//회원가입창의 아이디 입력란의 값을 가져옴
		function pValue(){
			document.getElementById("userId").value = opener.document.userInfo.id.value;
		}
		//사용클릭하면 부모창으로 값전달
		function sendCheckValue(){
			//중복체크결과 값전달
			opener.document.userInfo.idDuplication.value = "idcheck";
			opener.document.userInfo.id.value = document.getElementById("userId").value;

			if (opener != null) {
                opener.chkForm = null;
                self.close();
            }    



		}
	
	</script>
	
</head>
<body onload="pValue()" >
<div id="wrap">
	<br>
	<b><font size="4" color="gray">아이디 중복체크</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
		<form id="checkForm" action="IdCheckAction.do" method="post">
			<input type="text" name="idinput" id= userId >
			<input type="submit" value="중복확인" onclick="idCheck()">
		</form>
		<div id="msg"></div>
		<br>
		<input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
		<input id = "useBtn" type="button"  value="사용하기" onclick="sendCheckValue()">
	
	</div>
</div>	
</body>
</html>