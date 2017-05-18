<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%--@elvariable id="errors" type="java.util.Map<java.lang.String,java.lang.String>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp">
        <c:param name="pageTitle" value="Skill Tracking" />
    </c:import>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-3 col-lg-2 push-md-9 push-lg-10 pb-3">
            <c:import url="/WEB-INF/includes/user_selector.jsp" />
        </div>

        <div class="col-sm-12 col-md-10 col-lg-8 col-xl-8 pull-lg-1">
            <c:if test="${not empty errors.target}">
                <div class="row">
                    <div class="alert alert-danger" role="alert">
                        <strong>Error:</strong> ${errors.target}
                    </div>
                </div>
            </c:if>

            <c:if test="${targetUser != null}">
                <div class="row">
                    <div class="table-responsive">
                        <c:import url="/WEB-INF/includes/skills_table.jsp">
                            <c:param name="skillsUser" value="${targetUser}" />
                        </c:import>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
<script type="application/javascript" src="<c:url value="/js/target_selector.js"/>"></script>
</body>
</html>
