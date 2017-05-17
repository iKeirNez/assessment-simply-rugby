<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Skills</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row">

        <c:if test="${targetUser != null}">
            <div class="col-sm-12 col-md-10 col-lg-8 col-xl-6">
                <c:import url="/WEB-INF/skills_guest.jsp">
                    <c:param name="skillsUser" value="${targetUser}" />
                </c:import>
            </div>
        </c:if>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
