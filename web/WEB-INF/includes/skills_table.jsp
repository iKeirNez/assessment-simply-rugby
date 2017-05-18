<%--@elvariable id="targetUser" type="com.keirnellyer.simplyrugby.user.Member"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-bordered skills-table">
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
                    <c:choose>
                        <c:when test="${skill.value == loop.index}">
                            <c:choose>
                                <c:when test="${skill.value >= 4}">
                                    <td class="bg-success"></td>
                                </c:when>
                                <c:when test="${skill.value >= 2}">
                                    <td class="bg-warning"></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="bg-danger"></td>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${skillLoop.isFirst()}">
                    <td rowspan="${rowSpan}">${category.comment}</td>
                </c:if>
            </tr>
        </c:forEach>
    </c:forEach>
    </tbody>
</table>