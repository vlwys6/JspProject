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
		alert("${bd_num}번 게시물 삭제가 완료되었습니다.");
		location.href="petList.do";
	</script>
</c:if>	
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("암호가 일치하지 않습니다.");
		location.href="petDelete.do?bd_num=${bd_num}";
	</script>
</c:if>
</body>
</html>