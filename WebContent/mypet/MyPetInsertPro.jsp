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
		alert("입력 완료");  
		location.href="main.do";
	</script>
</c:if>
<c:if test="${result == 0 }">  
	<script type="text/javascript">
		alert("입력 실패");  
		location.href="join.do";
	</script>
</c:if>
</body>
</html>