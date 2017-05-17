<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>View Skills</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-2 push-sm-8 push-md-9 push-lg-10 pb-3">
            <c:import url="/WEB-INF/includes/user_selector.jsp" />
        </div>

        <c:if test="${targetUser != null}">
            <div class="col-sm-12 col-md-10 col-lg-8 col-xl-6">
                <c:import url="/WEB-INF/includes/skills_table.jsp">
                    <c:param name="skillsUser" value="${targetUser}" />
                </c:import>
            </div>
        </c:if>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
<script type="application/javascript" src="<c:url value="/js/target_selector.js"/>"></script>
</body>
</html>
