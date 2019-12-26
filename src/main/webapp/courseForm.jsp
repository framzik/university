<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Course</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create course' : 'Edit course'}</h2>
    <jsp:useBean id="course" type="ru.university.model.UniversityCourse" scope="request"/>
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
