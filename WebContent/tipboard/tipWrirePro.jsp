<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result >0 }">
	<script type="text/javascript">
		alert("입력완료");
		location.href="tipList.do?pageNum=${pageNum}&id=${id}";
	</script>
</c:if>
<c:if test="${result ==0 }">
	<script type="text/javascript">
		alert("입력실패");
		location.href="tipWrite.do?num=${num}&pageNum=${pageNum}&id=${id}";
	</script>
</c:if>
</body>
</body>
</html>