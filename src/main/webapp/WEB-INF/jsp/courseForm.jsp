<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="course" type="ru.university.model.Course" scope="request"/>
    <h3><spring:message code="${course.new() ? 'course.add' : 'course.edit'}"/></h3>
    <form method="post" action="courses">
        <input type="hidden" name="id" value="${course.id}">
        <dl>
            <dt><spring:message code="course.name"/></dt>
            <dd><input type="text" value="${course.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="course.number"/></dt>
            <dd><input type="number" value="${course.number}" name="number" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="course.cost"/></dt>
            <dd><input type="number" step="any" value="${course.cost}" name="cost" required></dd>
        </dl>

        <button type="submit"><spring:message code="app.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="app.cansel"/></button>
    </form>
</section>
</body>
</html>
