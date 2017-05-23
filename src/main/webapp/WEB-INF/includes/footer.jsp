<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <div class="container text-center">
        <span class="text-muted">
            Copyright &copy; <f:formatDate value="${now}" pattern="Y" /> ${applicationScope.author}
        </span>
    </div>
</footer>
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
        integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

<%--@elvariable id="errors" type="java.util.Map<java.lang.String,java.lang.String>"--%>
<c:if test="${not empty errors}">
    <script type="application/javascript">
        console.log("The following errors/warnings occurred whilst requesting this page.");

        <c:forEach var="entry" items="${errors}">
            console.log('${entry.key}: ${entry.value}');
        </c:forEach>
    </script>
</c:if>