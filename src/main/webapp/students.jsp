<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Student list</title>
    <style>
        .normal {
            color: green;
        }

        .bad {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Students</h2>
    <a href="students?action=create">Add Student</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>RecordNumber</th>
            <th>AverageRating</th>
            <th></th>
            <th></th>
        </tr>
        </thead>

        <c:forEach items="${students}" var="student">
            <jsp:useBean id="student" type="ru.university.model.Student"/>
            <tr class="${student.averageRating<=4 ? 'bad' : 'normal'}">
                <td>${student.name}</td>
                <td>${student.address}</td>
                <td>${student.email}</td>
                <td>${student.recordNumber}</td>
                <td>${student.averageRating}</td>
                <td><a href="students?action=update&id=${student.id}">Update</a></td>
                <td><a href="students?action=delete&id=${student.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>