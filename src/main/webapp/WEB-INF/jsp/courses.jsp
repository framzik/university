<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/university.common.js" defer></script>
<script type="text/javascript" src="resources/js/university.courses.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="course.title"/></h3>

        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">

                    <div class="row">

                        <div class="col-5">
                            <label for="startCost"><spring:message code="course.fromCost"/></label>
                            <input class="form-control" value="0"  type="number" name="startCost" id="startCost">
                        </div>

                        <div class="col-5">
                            <label for="endCost"><spring:message code="course.toCost"/></label>
                            <input class="form-control" value="250000" type="number" name="endCost" id="endCost">
                        </div>

                    </div>
                </form>
            </div>

            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    <spring:message code="app.cancel"/>
                </button>
                <button class="btn btn-primary" onclick="updateFilteredTable()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="course.filter"/>
                </button>
            </div>
        </div>

        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="app.add"/>
        </button>

        <br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="course.name"/></th>
                <th><spring:message code="course.number"/></th>
                <th><spring:message code="course.cost"/></th>
                <th></th>
                <th></th>
            </tr>

            </thead>

        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="course.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="course.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="course.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="number" class="col-form-label"><spring:message code="course.number"/></label>
                        <input type="number" class="form-control" id="number" name="number"
                               placeholder="<spring:message code="course.number"/>">
                    </div>

                    <div class="form-group">
                        <label for="cost" class="col-form-label"><spring:message code="course.cost"/></label>
                        <input type="number" class="form-control" id="cost" name="cost"
                               placeholder="<spring:message code="course.cost"/>">
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="app.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="app.save"/>
                </button>
            </div>

        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>