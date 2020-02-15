<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="university" tagdir="/WEB-INF/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userTo.name} <spring:message code="app.profile"/></h3>
                <%--@elvariable id="userTo" type="ru.university.to.UserTo"--%>
                <form:form class="form-group" modelAttribute="userTo" method="post" action="profile"
                           charset="utf-8" accept-charset="UTF-8">

                    <university:inputField labelCode="user.name" name="name"/>
                    <university:inputField labelCode="user.email" name="email"/>
                    <university:inputField labelCode="user.password" name="password" inputType="password"/>
                    <university:inputField labelCode="user.address" name="address" inputType="text"/>

                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="app.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            <spring:message code="app.save"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>