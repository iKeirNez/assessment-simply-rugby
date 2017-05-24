<%--@elvariable id="user" type="com.keirnellyer.simplyrugby.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="keirtags" uri="http://keirnellyer.com/jsp/tlds/keirTags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded fixed-top">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="<c:url value="/"/>">${applicationScope.brand}</a>

    <div id="navbar" class="collapse navbar-collapse">
        <div class="navbar-nav mr-auto">
            <!-- TODO: Apply class 'active' for active links -->
            <c:if test="${not empty user}">
                <%--<a class="navbar-text nav-item nav-link" href="<c:url value="${sessionScope.userHome}"/>">Home</a>--%>

                <c:if test="${not empty user.navigation}">
                    <c:forEach items="${user.navigation.items}" var="navElement">
                        <a class="navbar-text nav-item nav-link" href="<c:url value="${navElement.location}" />">
                                ${navElement.display}</a>
                    </c:forEach>
                </c:if>
            </c:if>
        </div>
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${not empty user}">
                    <a class="navbar-text nav-item nav-link" href="<c:url value="/user/edit_profile"/>">
                        <keirtags:displayName user="${sessionScope.user}" />
                    </a>
                    <a class="navbar-text nav-item nav-link" href="<c:url value="/logout"/>">Logout</a>
                </c:when>
                <c:otherwise>
                    <a class="navbar-text nav-item nav-link" href="<c:url value="/register" />">Register</a>
                    <a class="navbar-text nav-item nav-link" href="<c:url value="/login"/>">Login</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div><!--/.nav-collapse -->
</nav>