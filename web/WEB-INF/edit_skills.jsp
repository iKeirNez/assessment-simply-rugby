<%--@elvariable id="availableTargets" type="java.util.List<com.keirnellyer.simplyrugby.user.Member>"--%>
<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%--@elvariable id="errors" type="java.util.Map<java.lang.String,java.lang.String>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp">
        <c:param name="pageTitle" value="Edit User Skills" />
    </c:import>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-2 push-sm-8 push-md-9 push-lg-10 pb-3">
            <c:import url="/WEB-INF/includes/user_selector.jsp" />
        </div>

        <div class="col-sm-12 col-md-8 col-lg-8 col-xl-6 pull-md-2 pull-lg-0">
            <c:if test="${not empty errors.target}">
                <div class="alert alert-danger" role="alert">
                    <strong>Error:</strong> ${errors.target}
                </div>
            </c:if>

            <c:if test="${not empty targetUser}">
                <form action="" method="post">
                    <div class="row">
                        <div class="table-responsive">
                            <table class="table table-bordered skills-table text-center">
                                <thead>
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
                                <c:forEach items="${targetUser.skills}" var="category">
                                    <c:forEach items="${category.skills}" var="skill" varStatus="skillLoop">
                                        <tr>
                                            <c:set var="rowSpan" value="${category.skills.size()}" />

                                            <c:if test="${skillLoop.isFirst()}">
                                                <th scope="row" rowspan="${rowSpan}">${category.name}</th>
                                            </c:if>

                                            <td>${skill.name}</td>

                                            <c:forEach begin="1" end="5" varStatus="loop">
                                                <td align="center">
                                                    <input type="radio"
                                                           name="${category.name}_${skill.name}" value="${loop.index}"
                                                           <c:if test="${skill.value == loop.index}">checked</c:if> />
                                                </td>
                                            </c:forEach>

                                            <c:if test="${skillLoop.isFirst()}">
                                                <td class="comment" rowspan="${rowSpan}">
                                                    <textarea class="form-control" name="${category.name}_cmnt">${category.comment}</textarea>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="clearfix">
                        <div class="row float-sm-right">
                            <button type="submit" class="btn btn-block btn-success">Save changes</button>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/includes/footer.jsp" />
<script type="application/javascript" src="<c:url value="/js/target_selector.js"/>"></script>
</body>
</html>
