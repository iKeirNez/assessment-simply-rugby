<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp">
        <c:param name="pageTitle" value="500 - Internal Server Error" />
    </c:import>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h1>500 - Internal Server Error</h1>
            <p>If you're seeing this error, it means Keir can't code. However, please do not tell him this, as he may cry.</p>

            <h4>Nerdy Details</h4>
            <c:set var="throwable" value="${requestScope['javax.servlet.error.exception']}" />
            <%--@elvariable id="throwable" type="java.lang.Throwable"--%>
            <code>
                ${throwable['class'].name}: ${throwable.message}
                <br />

                <c:forEach var="element" items="${throwable.stackTrace}" varStatus="loop">
                    &nbsp;&nbsp;at ${element.toString()}<br />
                </c:forEach>
            </code>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
