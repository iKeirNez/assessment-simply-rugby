<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--@elvariable id="errors" type="java.util.Map"--%>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Login</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <div class="row row-top-padding">
        <div class="col-sm-6 col-md-4 mx-auto">
            <div class="card">
                <div class="card-header">
                    <strong>Sign in to continue</strong>
                </div>
                <div class="card-block">
                    <form method="post" action="">
                        <input type="hidden" name="redirect_to" value="${fn:escapeXml(param.redirect_to)}" />
                        <fieldset>
                            <div class="row">
                                <div class="col-sm-12 col-md-10 mx-auto">
                                    <c:if test="${not empty errors.overall}">
                                        <div class="alert alert-danger">
                                                ${errors.overall}
                                        </div>
                                    </c:if>

                                    <div class="form-group <c:if test="${not empty errors.username}">has-error</c:if>">
                                        <c:if test="${not empty errors.username}">
                                            <span class="help-block">${errors.username}</span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-user"></i>
                                        </span>
                                            <input class="form-control" placeholder="Username" name="username"
                                                   type="text" value="${param.username}" autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group <c:if test="${not empty errors.password}">has-error</c:if>">
                                        <c:if test="${not empty errors.password}">
                                            <span class="help-block">${errors.password}</span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-key"></i>
                                        </span>
                                            <input class="form-control" placeholder="Password" name="password"
                                                   type="password">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" name="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>

                    <div class="text-center">
                        <c:url value="/register" var="registerURL">
                            <%-- TODO is there a built-in JSTL function to do this for us (forward params) --%>
                            <c:forEach items="${param}" var="theParam">
                                <c:param name="${theParam.key}" value="${theParam.value}" />
                            </c:forEach>
                        </c:url>
                        <span>Don't have an account? <a href="${registerURL}">Register now</a>.</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
