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
	location.href="tipContent.do?pageNum=${pageNum}&bd_num=${bd_num}&type_num=${type_num}";
</script>
</body>
</html>