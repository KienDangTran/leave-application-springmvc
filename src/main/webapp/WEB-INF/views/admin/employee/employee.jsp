<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<spring:message code="employee_code" var="employee_code" />
<spring:message code="employee_name" var="employee_name" />

<h1>
	<spring:message code="employee_management" />
</h1>

<datatables:table id="empTbl" data="${allEmployees}" row="employee" cssClass="table table-striped">
	<datatables:column title="${employee_code}" filterable="true">
		<spring:url value="/employee/${employee.employeeCode}" var="employeeDetailsUrl" htmlEscape="true" />
		<a href="${employeeDetailsUrl}">${employee.employeeCode}</a>
	</datatables:column>

	<datatables:column title="${employee_name}" property="employeeName" filterable="true" sortable="true" />
</datatables:table>



