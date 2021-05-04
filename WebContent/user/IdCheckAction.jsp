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


<c:if test="${result == false }">
	<script type="text/javascript">
		alert("등록 가능한 아이디 입니다.");
		location.href="IdCheckForm.do";
		
	</script>
</c:if>
<c:if test="${result == true }">  
	<script type="text/javascript">
		alert("아이디가 중복이 있습니다.");  
		
		location.href="IdCheckForm.do";
	</script>
</c:if>
</body>
</html>