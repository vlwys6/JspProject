<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

@font-face {
font-family:"SCDream4";
src:url("fonts/SCDream4.otf") format("opentype");
font-style:normal;
font-weight:normal;
}

body {font-family: 'SCDream4'; }

</style>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert('삭제성공');
		location.href="petContent.do?id=${id}&pageNum=${pageNum}&commPageNum=${commPageNum}&bd_num=${bd_num}&type_num=${type_num}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert('삭제 실패');
		location.href="petContent.do?id=${id}&pageNum=${pageNum}&commPageNum=${commPageNum}&bd_num=${bd_num}&type_num=${type_num}";
	</script>
</c:if>
</body>
</html>