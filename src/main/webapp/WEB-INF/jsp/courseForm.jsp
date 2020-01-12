<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Course</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create course' : 'Edit course'}</h2>
    <jsp:useBean id="course" type="ru.university.model.Course" scope="request"/>
    <form method="post" action="courses">
        <input type="hidden" name="id" value="${course.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${course.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Number</dt>
            <dd><input type="number" value="${course.number}" name="number" required></dd>
        </dl>
        <dl>
            <dt>Cost</dt>
            <dd><input type="number" step="any" value="${course.cost}" name="cost" required></dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
