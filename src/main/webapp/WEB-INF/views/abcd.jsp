<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>我的天那</title>
</head>
<body>
     <c:forEach items="${list}" var="user">
         你们的名字: ${user}
     </c:forEach>
</body>
</html>
