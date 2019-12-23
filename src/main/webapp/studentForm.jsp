<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Student</title>
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
    <h2>${param.action == 'create' ? 'Create student' : 'Edit student'}</h2>
    <jsp:useBean id="student" type="ru.university.model.Student" scope="request"/>
    <form method="post" action="students">
        <input type="hidden" name="id" value="${student.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${student.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt>Address:</dt>
            <dd><input type="text" value="${student.address}" size=40 name="address" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="email" value="${student.email}" name="email" required></dd>
        </dl>
        <dl>
            <dt>RecordNumber:</dt>
            <dd><input type="number" value="${student.recordNumber}" name="recordNumber" required></dd>
        </dl>
        <dl>
            <dt>AverageRating:</dt>
            <dd><input type="number" step="any" value="${student.averageRating}" name="averageRating" required></dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
