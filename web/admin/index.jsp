<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="keirtags" uri="http://keirnellyer.com/jsp/tlds/keirTags" %>
<%--@elvariable id="user" type="com.keirnellyer.simplyrugby.user.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Administrator Dashboard</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <p>Welcome to the admin dashboard, <keirtags:displayName user="${user}" /></p>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
