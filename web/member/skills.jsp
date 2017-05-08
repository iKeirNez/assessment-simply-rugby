<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.keirnellyer.simplyrugby.user.Member"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Skill Viewer</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <table class="table table-bordered skills-table">
        <thead class="thead-default">
        <tr>
            <th rowspan="2">Category</th>
            <th rowspan="2">Skill</th>
            <th colspan="5">Skill Level</th>
            <th rowspan="2">Comments</th>
        </tr>
        <tr>
            <c:forEach begin="1" end="5" varStatus="loop">
                <th>${loop.index}</th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${user.skills}" var="category">
            <c:forEach items="${category.skills}" var="skill" varStatus="skillLoop">
                <c:set var="rowSpan" value="${category.skills.size()}" />
                <tr>
                    <c:if test="${skillLoop.isFirst()}">
                        <th scope="row" rowspan="${rowSpan}">${category.name}</th>
                    </c:if>

                    <td>${skill.name}</td>

                    <c:forEach begin="1" end="5" varStatus="loop">
                        <td>
                            <c:if test="${skill.value == loop.index}">
                                *
                            </c:if>
                        </td>
                    </c:forEach>

                    <c:if test="${skillLoop.isFirst()}">
                        <td rowspan="${rowSpan}">${category.comment}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
