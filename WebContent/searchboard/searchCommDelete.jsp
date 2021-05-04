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
정말로 삭제하시겠습니까?<p>
<input type="button" value="예" onclick="location.href='searchCommDeletePro.do?pageNum=${pageNum}&bd_num=${bd_num }&comm_num=${comm_num }&commPageNum=${commPageNum }&type_num=${type_num}&searchkeyWord=${searchkeyWord }'">
<input type="button" value="아니오" onclick="history.back()">
</body>
</html>