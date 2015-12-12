<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<spring:message code="employee_code" var="employee_code" />
<spring:message code="employee_name" var="employee_name" />

<h1>
	<spring:message code="employee_summary" />
</h1>

<div class="table-responsive panel">
	<div class="panel-heading">
		<div class="btn-group">
			<button type="button" class="btn-lg btn-primary pull-right" onclick="">
				<span class="glyphicon glyphicon-plus-sign" />
				<spring:message code="add" />
			</button>
		</div>
	</div>
	<div class="panel-body">
		<datatables:table id="empTbl" data="${allEmployees}" row="employee" cssClass="table table-striped">
			<datatables:column title="${employee_code}" property="employeeCode" cssCellClass="identity" />
			<datatables:column title="${employee_name}" property="employeeName" />
			<datatables:column sortable="false" filterable="false">
				<a href="<spring:url value="/employee/${employee.employeeCode}" />">
					<span class="glyphicon glyphicon-eye-open"></span>
					<spring:message code="view_details" />
				</a>
			</datatables:column>
		</datatables:table>
	</div>
</div>


