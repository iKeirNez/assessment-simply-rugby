<%--@elvariable id="availableTargets" type="java.util.List<com.keirnellyer.simplyrugby.user.Member>"--%>
<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Edit Skills</title>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-md-3 col-lg-2 push-sm-8 push-md-9 push-lg-10 pb-3">
            <div class="card card-inverse card-info text-center">
                <div class="card-block">
                    <h5 class="card-title"><label for="user_selector">User Selection</label></h5>
                    <form action="" method="get">
                        <div class="form-group">
                            <%--JS function submits this form when the select element changes--%>
                            <select class="form-control" id="user_selector" name="target">
                                <c:if test="${empty targetUser}">
                                    <option disabled selected>Select a user</option>
                                </c:if>

                                <c:forEach var="u" items="${availableTargets}">
                                    <option value="${u.username}" <c:if test="${u == targetUser}">selected</c:if>>${u.firstName} ${u.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <c:if test="${not empty targetUser}">
            <div class="col-sm-12 col-md-8 col-lg-8 col-xl-6 pull-md-2 pull-lg-0">
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
            </div>
        </c:if>
    </div>
</div>

<c:import url="/WEB-INF/includes/footer.jsp" />
<script type="application/javascript">
    $('#user_selector').change(function() {
        $(this).closest('form').submit();
    });
</script>
</body>
</html>
