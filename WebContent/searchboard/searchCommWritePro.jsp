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
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert('댓글 작성 성공');
		location.href="searchContent.do?bd_num=${bd_num}&pageNum=${pageNum}&searchkeyWord=${searchkeyWord }&commPageNum=${commPageNum}&type_num=${type_num}";
	</script>
</c:if>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert('댓글 작성 실패');
		history.back();
	</script>
</c:if>
</body>
</html>