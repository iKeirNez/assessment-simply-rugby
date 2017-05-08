<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="session" type="com.keirnellyer.simplyrugby.user.Member"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/includes/head.jsp" />
    <title>Skill Viewer</title>

    <c:if test="${user.username == 'ged'}">
        <script type="application/javascript">
            new Audio('<c:url value="/res/music.mp3" />').play();
            window.setInterval(strobe, 50);

            function strobe() {
                $('th, td').each(function (i) {
                    $(this).css({'color': getRandomColor(), 'background-color': getRandomColor()});
                });
            }

            function getRandomColor() {
                var letters = '0123456789ABCDEF';
                var color = '#';
                for (var i = 0; i < 6; i++ ) {
                    color += letters[Math.floor(Math.random() * 16)];
                }
                return color;
            }
        </script>
    </c:if>
</head>
<body>
<c:import url="/WEB-INF/includes/navbar.jsp" />
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-sm-12 col-md-10 col-lg-8 col-xl-6">
            <table class="table table-bordered table-responsive skills-table">
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
                <c:forEach items="${user.skills}" var="category">
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
        </div>
    </div>
</div>
<c:import url="/WEB-INF/includes/footer.jsp" />
</body>
</html>
