<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Course list</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Courses</h2>
    <form method="get" action="courses">
        <input type="hidden" name="action" value="filter">
        <dl>
            <dt>From Cost:</dt>
            <dd><input type="number"   name="startCost" value="${param.startCost}"></dd>
        </dl>
        <dl>
            <dt>To Cost:</dt>
            <dd><input type="number" name="endCost" value="${param.endCost}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <a href="courses?action=create">Add Course</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Number</th>
            <th>Cost</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <c:forEach items="${courses}" var="course">
            <jsp:useBean id="course" type="ru.university.model.Course"/>
            <tr data-cost=${course.cost<16000}>
                <td>${course.name}</td>
                <td>${course.number}</td>
                <td>${course.cost}</td>
                <td><a href="courses?action=update&id=${course.id}">Update</a></td>
                <td><a href="courses?action=delete&id=${course.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>