<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <form method="post" action="users">
        <spring:message code="app.login"/> : <label>
        <select name="userId">
        <option value="1"><spring:message code = "user.student"/></option>
        <option value="2"><spring:message code = "user.professor"/></option>
    </select>
    </label>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

