<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert( "반려 커뮤니티에 오신것을 환영합니다 !!");  
		location.href="main.do";
	</script>
</c:if>
<c:if test="${result == -1 || result == 0 }">
		<script>
			alert("아이디 혹은 비밀번호가 틀렸습니다.");
			location.href = "loginForm.do";
		</script>
		
	</c:if>

</body>
</html>