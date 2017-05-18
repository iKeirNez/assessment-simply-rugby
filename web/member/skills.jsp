<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.keirnellyer.simplyrugby.user.Member"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp">
        <c:param name="pageTitle" value="Skill Tracking" />
    </c:import>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-sm-12 col-md-10 col-lg-8 col-xl-6">
            <div class="table-responsive">
                <c:set var="targetUser" scope="request" value="${user}" />
                <c:import url="/WEB-INF/includes/skills_table.jsp" />
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
