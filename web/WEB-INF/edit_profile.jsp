<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.keirnellyer.simplyrugby.user.JuniorMember"/>
<c:set var="errors" value="${requestScope.errors}" />
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Edit Profile</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container">
    <div class="row row-top-padding">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>Edit profile details</strong>
                </div>
                <div class="panel-body">
                    <form method="post" action="">
                        <fieldset>
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <c:if test="${requestScope.updated}">
                                        <div class="alert alert-success">
                                            Updated profile.
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty errors.overall}">
                                        <div class="alert alert-danger">
                                                ${errors.overall}
                                        </div>
                                    </c:if>

                                    <div class="form-group ${not empty errors.first_name ? 'has-error' : ''}">
                                        <c:if test="${not empty errors.first_name}">
                                            <span class="help-block">${errors.first_name}</span>
                                        </c:if>

                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                            <input class="form-control" placeholder="First name" name="first_name"
                                                   value="<c:out value="${param.first_name}" default="${user.firstName}" />"
                                                   type="text" autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group ${not empty errors.last_name ? 'has-error' : ''}">
                                        <c:if test="${not empty errors.last_name}">
                                            <span class="help-block">${errors.last_name}</span>
                                        </c:if>

                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-lock"></i>
                                            </span>
                                            <input class="form-control" placeholder="Last name" name="last_name"
                                                   type="text"
                                                   value="<c:out value="${param.last_name}" default="${user.lastName}" />">
                                        </div>
                                    </div>

                                    <div class="form-group ${not empty errors.password ? 'has-error' : ''}">
                                        <c:if test="${not empty errors.password}">
                                            <span class="help-block">${errors.password}</span>
                                        </c:if>

                                        <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-lock"></i>
                                        </span>
                                            <input class="form-control" placeholder="Password" name="password"
                                                   type="password">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" name="submit" class="btn btn-lg btn-primary btn-block"
                                               value="Update profile">
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
