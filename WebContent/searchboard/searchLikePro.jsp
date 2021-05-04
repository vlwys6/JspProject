<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert('추천완료');
	location.href="searchContent.do?bd_num=${bd_num}&pageNum=${pageNum}&searchkeyWord=${searchkeyWord }&type_num=${type_num}";
</script>
</body>
</html>