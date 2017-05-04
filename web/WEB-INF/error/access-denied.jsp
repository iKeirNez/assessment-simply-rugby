<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Access Denied</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <h1>Access Denied</h1>
    <p>You do not have permission to view this.</p>

    <%--@elvariable id="customMessage" type="java.lang.String"--%>
    <c:if test="${not empty customMessage}">
        <p>${customMessage}</p>
    </c:if>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
