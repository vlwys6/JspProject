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
                   alert('입력이 완료되었습니다.');
                   //location.href='storeList.do';
                    location.href='storeList.do?pageNum=${pageNum}'; 
            </script>
     </c:if>
     
     <c:if test="${result == 0}">
           <script type="text/javascript">
                   alert('입력이 안됬습니다.');
                   //location.href='storeWrite.do';
                    location.href='storeWrite.do?bd_num=${bd_num}&pageNum=${pageNum}';
            </script>
     </c:if>
</body>
</html>