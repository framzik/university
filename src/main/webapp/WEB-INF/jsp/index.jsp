<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <form method="post" action="users" class="form-inline">
            <label><spring:message code="app.login"/></label>
            <select name="userId" class="form-control mx-3">
                <option value="1" selected><spring:message code = "user.student"/></option>
                <option value="2"><spring:message code = "user.professor"/></option>
            </select>
            <button type="submit" class="btn btn-primary"><spring:message code="common.select"/></button>
        </form>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

