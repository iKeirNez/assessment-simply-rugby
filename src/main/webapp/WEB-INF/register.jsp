<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="errors" type="java.util.Map"--%>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp">
        <c:param name="pageTitle" value="Register" />
    </c:import>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <div class="row row-top-padding">
        <div class="col-sm-6 col-md-4 mx-auto">
            <div class="card">
                <div class="card-header">
                    <strong>Register a new account</strong>
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

                                    <div class="form-group <c:if test="${not empty errors.username}">has-danger</c:if>">
                                        <c:if test="${not empty errors.username}">
                                            <span class="form-text text-muted">${errors.username}</span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-fw fa-user"></i>
                                        </span>
                                            <input class="form-control" placeholder="Username" name="username"
                                                   type="text" value="${param.username}" autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group <c:if test="${not empty errors.first_name}">has-danger</c:if>">
                                        <c:if test="${not empty errors.first_name}">
                                            <span class="form-text text-muted"><c:out value="${errors.first_name}" /></span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-fw fa-font"></i>
                                        </span>
                                            <input class="form-control" placeholder="First name" name="first_name"
                                                   type="text" value="${param.first_name}">
                                        </div>
                                    </div>

                                    <div class="form-group <c:if test="${not empty errors.last_name}">has-danger</c:if>">
                                        <c:if test="${not empty errors.last_name}">
                                            <span class="form-text text-muted"><c:out value="${errors.last_name}" /></span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-fw fa-font"></i>
                                        </span>
                                            <input class="form-control" placeholder="Last name" name="last_name"
                                                   type="text" value="${param.last_name}">
                                        </div>
                                    </div>

                                    <div class="form-group <c:if test="${not empty errors.password}">has-danger</c:if>">
                                        <c:if test="${not empty errors.password}">
                                            <span class="form-text text-muted"><c:out value="${errors.password}" /></span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="fa fa-fw fa-key"></i>
                                        </span>
                                            <input class="form-control" placeholder="Password" name="password"
                                                   type="password">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" name="submit" class="btn btn-lg btn-primary btn-block"
                                               value="Create account">
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
