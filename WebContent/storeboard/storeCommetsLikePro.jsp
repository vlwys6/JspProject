<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert('댓글추천완료');
	location.href="storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&commPageNum=${commPageNum}&type_num=${type_num}";
</script>
</body>
</html>