<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="employee_code" var="employee_code" />
<spring:message code="employee_name" var="employee_name" />
<spring:message code="date_of_birth" var="date_of_birth" />
<spring:message code="email" var="email" />
<spring:message code="phone" var="phone" />

<spring:url value="/employee/${currentEmployee.employeeCode}" var="updateEmployeeULR" />

<h1>
	<spring:message code="employee_details" />
</h1>
<div class="panel panel-primary">
	<div class="panel-heading">
		<b><spring:message code="basic_information" /></b>
	</div>

	<div class="panel-body">
		<form:form method="POST" modelAttribute="currentEmployee" action="${updateEmployeeULR}">
			<!-- Row 1 -->
			<div class="row">
				<!-- Employee Code -->
				<spring:bind path="employeeCode">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label required" for="txtEmpCode">${employee_code}</label>
						<form:input type="text" cssClass="form-control identity" id="txtEmpCode" placeholder="${employee_code}" path="employeeCode" readonly="true" required="true" />
						<form:errors path="employeeCode" cssClass="control-label" />
					</div>
				</spring:bind>
				<!-- Employee Name -->
				<spring:bind path="employeeName">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label required" for="txtEmpName">${employee_name}</label>
						<form:input type="text" cssClass="form-control" id="txtEmpCode" placeholder="${employee_name}" path="employeeName" readonly="${editable ? 'false' : 'true'}" required="true" />
						<form:errors path="employeeName" cssClass="control-label" />
					</div>
				</spring:bind>
			</div>

			<!-- Row 2 -->
			<div class="row">
				<!-- Date of Birth -->
				<spring:bind path="dateOfBirth">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label required" for="txtDoB">${date_of_birth}</label> <i>(eg. 01/31/1970)</i>
						<form:input type="text" cssClass="form-control datepicker" id="txtDoB" placeholder="${date_of_birth}" path="dateOfBirth" readonly="${editable ? 'false' : 'true'}"
							required="true" />
						<form:errors path="dateOfBirth" cssClass="errors" />
					</div>
				</spring:bind>
				<!-- Email -->
				<spring:bind path="email">
					<div class="col-md-6 form-group  ${status.error ? 'has-error' : ''}">
						<label class="control-label" for="txtEmpName">${email}</label>
						<form:input type="email" cssClass="form-control" id="txtEmpCode" placeholder="${email}" path="email" readonly="${editable ? 'false' : 'true'}" />
						<form:errors path="email" cssClass="control-label" />
					</div>
				</spring:bind>
			</div>

			<!-- Row 3 -->
			<div class="row">
				<!-- Phone -->
				<spring:bind path="phoneNo">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label" for="txtPhone">${phone}</label>
						<form:input type="tel" cssClass="form-control" id="txtPhone" placeholder="${phone}" path="phoneNo" readonly="${editable ? 'false' : 'true'}" />
						<form:errors path="phoneNo" cssClass="control-label" />
					</div>
				</spring:bind>
				<!-- Status -->
				<spring:bind path="status">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label"><spring:message code="status" /></label>
						<div style="padding: 6px 12px;">
							<label class="radio-inline"><form:radiobutton path="status" value="A" disabled="${editable ? 'false' : 'true'}" /> <spring:message code="active" /></label> <label
								class="radio-inline"><form:radiobutton path="status" value="S" disabled="${editable ? 'false' : 'true'}" /> <spring:message code="suspended" /></label>
							<form:errors path="status" cssClass="control-label" />
						</div>
					</div>
				</spring:bind>
			</div>

			<!-- Buttons group -->
			<div class="col-md-12 form-group">
				<div class="col-sm-offset-2 col-sm-10 btn-group">
					<button type="submit" class="btn-lg btn-primary pull-right" ${editable ? '' : 'hidden'}>
						<span class="glyphicon glyphicon-floppy-disk" />
						<spring:message code="save" />
					</button>
					<button type="button" class="btn-lg btn-primary pull-right allow-edit" ${editable ? 'hidden' : ''}>
						<span class="glyphicon glyphicon-edit" />
						<spring:message code="edit"/>
					</button>
				</div>
			</div>
		</form:form>
	</div>
</div>