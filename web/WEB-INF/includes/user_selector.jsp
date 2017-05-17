<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%--@elvariable id="availableTargets" type="java.util.List<com.keirnellyer.simplyrugby.user.Member>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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