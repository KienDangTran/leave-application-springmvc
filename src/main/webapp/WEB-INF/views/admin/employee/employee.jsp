<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:message code="employee_code" var="employee_code" />
<spring:message code="employee_name" var="employee_name" />
<spring:message code="select" var="select" />

<spring:url value="/employee/add" var="addEmpUrl" />
<spring:url value="/employee/removeBatchEmployee" var="removeBatchEmployeeUrl" />

<h1>
	<spring:message code="employee_management" />
</h1>

<datatables:table id="empTbl" data="${allEmployees}" row="employee" cssClass="table table-striped">
	<datatables:column title="${employee_code}" cssCellClass="identity">
		<a href="<spring:url value="/employee/${employee.employeeCode}" />"> ${employee.employeeCode} </a>
	</datatables:column>
	<datatables:column title="${employee_name}" property="employeeName" />
	<datatables:column sortable="false" filterable="false" title="${select}">
		<input type="checkbox" class="row-selecting" name="check${employee.employeeCode}" value="${employee.employeeCode}">
	</datatables:column>
</datatables:table>

<br />
<div class="btn-group btn-group-justified">
	<a class="btn btn-lg btn-primary" href="${addEmpUrl}">
		<span class="glyphicon glyphicon-plus-sign"></span>
		<spring:message code="add" />
	</a>
	<a class="btn btn-lg btn-primary" href="${removeBatchEmployeeUrl}">
		<span class="glyphicon glyphicon-trash"></span>
		<spring:message code="remove_all" />
	</a>
</div>