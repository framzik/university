<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="course.title"/></h3>

    <form method="get" action="courses/between">
        <dl>
            <dt><spring:message code="course.fromCost"/>:</dt>
            <dd><input type="number" name="startCost" value="${param.startCost}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="course.toCost"/>:</dt>
            <dd><input type="number" name="endCost" value="${param.endCost}"></dd>
        </dl>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
    <hr>
    <a href="courses/create"><spring:message code="course.create"/></a>
    <hr>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="course.name"/></th>
            <th><spring:message code="course.number"/></th>
            <th><spring:message code="course.cost"/></th>
        </tr>
        </thead>

        <c:forEach items="${courses}" var="course">
            <jsp:useBean id="course" scope="page" type="ru.university.model.Course"/>
            <tr data-cost=${course.cost<16000}>
                <td>${course.name}</td>
                <td>${course.number}</td>
                <td>${course.cost}</td>
                <td><a href="courses/update?id=${course.id}"><spring:message code="app.update"/></a></td>
                <td><a href="courses/delete?id=${course.id}"><spring:message code="app.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>