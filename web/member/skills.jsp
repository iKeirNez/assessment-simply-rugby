<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.keirnellyer.simplyrugby.user.JuniorMember"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Skill Viewer</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <table class="table-bordered table-hover">
        <tr>
            <td>Category</td>
            <td>Type</td>

            <c:forEach begin="1" end="5" varStatus="loop">
                <td>${loop.index}</td>
            </c:forEach>
        </tr>

        <c:forEach items="${user.skills}" var="skill">
            <tr>
                <td>${skill.category.name()}</td>
                <td>${skill.type}</td>

                <c:forEach begin="1" end="5" varStatus="loop">
                    <td>
                        <c:if test="${skill.value == loop.index}">
                            *
                        </c:if>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
