<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Faculty list</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Faculties</h2>
    <a href="faculties?action=create">Add Faculty</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Courses</th>
            <th></th>
            <th></th>
        </tr>
        </thead>


        <c:forEach items="${faculties}" var="faculty">
            <jsp:useBean id="faculty" type="ru.university.model.Faculty"/>
            <tr>
                <td>${faculty.name}</td>
                <td>
                    <form method="post" action="faculties" >
                        <select name="facultyId" hidden>
                            <option value=${faculty.id}></option>
                        </select>
                        <button type="submit" >Курсы</button>
                    </form>
                </td>
                <td><a href="faculties?action=update&id=${faculty.id}">Update</a></td>
                <td><a href="faculties?action=delete&id=${faculty.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>