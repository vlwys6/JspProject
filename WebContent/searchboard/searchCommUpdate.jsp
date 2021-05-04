<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../Error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	@font-face{
		font-family: "SCDream4";
		src: url("fonts/SCDream4.otf") format("opentype");
		font-style: normal;
		font-weight: normal;
	
	}
	body{font-family: 'SCDream4';}
</style>
</head>
<body>
<form action="searchCommUpdatePro.do?bd_num=${bd_num}&pageNum=${pageNum }&serachkeyWord=${searchkeyWord}">
	<input type="hidden" name="type_num" value="${type_num }">
	<input type="hidden" name="pageNum" value="${pageNum }">
	<input type="hidden" name="comm_num" value="${comm_num }">
	<input type="hidden" name="commPageNum" value="${commPageNum }">
	<input type="hidden" name="bd_num" value="${bd_num }">
	댓글내용 : <input type="text" name="comm_cont" value="${comments.comm_cont }"><p>
	<input type="hidden" name="searchkeyWord" value="${searchkeyWord }">
	<input type="submit" value="수정">	
</form>
<input type="button" value="수정취소" onclick="history.back();">
</body>
</html>