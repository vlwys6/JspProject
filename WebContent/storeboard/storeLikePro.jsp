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
           alert('추천수가 증가하였습니다.');
           location.href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}';
      </script>
</body>
</html>