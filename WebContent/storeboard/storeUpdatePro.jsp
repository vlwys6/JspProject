<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${result > 0}">
          <script type="text/javascript">
          alert('수정완료');
          location.href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}';
          </script>
    </c:if>
    <c:if test="${result == 0}">
          <script type="text/javascript">
          alert('수정실패');
          location.href='storeContent.do?bd_num=${bd_num}&pageNum=${pageNum}&type_num=${type_num}';
          </script>
    </c:if>
</body>
</html>