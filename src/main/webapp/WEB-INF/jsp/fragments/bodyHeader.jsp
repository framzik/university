<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
    <a href="courses"><spring:message code="course.title"/></a> | <a href="users"><spring:message code="user.title"/></a> | <a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>
</header>