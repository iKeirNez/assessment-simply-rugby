<%--@elvariable id="user" type="com.keirnellyer.simplyrugby.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">${applicationScope.brand}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <!-- TODO: Apply class 'active' for active links -->
                <c:if test="${not empty user}">
                    <li><a href="<c:url value="${sessionScope.userHome}"/>">Home</a></li>

                    <c:if test="${not empty user.navigation}">
                        <c:forEach items="${user.navigation.items}" var="navElement">
                            <li><a href="<c:url value="${navElement.location}" />">${navElement.display}</a></li>
                        </c:forEach>
                    </c:if>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${not empty user}">
                        <li>
                            <c:choose>
                                <c:when test="${sessionScope.canEditProfile}">
                                    <a href="<c:url value="/member/edit_profile"/>">${sessionScope.userDisplayName}</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="navbar-text">${sessionScope.userDisplayName}</span>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <li><a href="<c:url value="/logout"/>">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="<c:url value="/register" />">Register</a></li>
                        <li><a href="<c:url value="/login"/>">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>