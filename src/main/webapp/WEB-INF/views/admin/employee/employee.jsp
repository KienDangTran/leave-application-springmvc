<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib
	prefix="spring"
	uri="http://www.springframework.org/tags"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>

<h1>
	<spring:message code="employee_management" />
</h1>

<datatables:table
	id="empTbl"
	data="${allEmployees}">

	<datatables:column
		property="employeeCode"
		titleKey="employee_code">
	</datatables:column>

	<datatables:column
		titleKey="employee_name"
		property="employeeName" />
</datatables:table>



