<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="employee_code" var="employee_code" />
<spring:message code="employee_name" var="employee_name" />
<spring:message code="date_of_birth" var="date_of_birth" />
<spring:message code="email" var="email" />
<spring:message code="phone" var="phone" />

<spring:url value="/employee/${currentEmployee.employeeCode}" var="updateEmployeeUlr" />
<spring:url value="/employee" var="employeeUlr" />
<spring:url value="/employee/removeCurrentEmployee" var="removeCurrentEmployeeUlr" />

<form:form method="POST" modelAttribute="currentEmployee" action="${updateEmployeeUlr}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="pull-left">
				<spring:message code="basic_information" />
			</h3>
			<div class="btn-group pull-right">
				<button type="button" class="btn-lg btn-primary allow-edit" ${editable ? 'hidden' : ''}>
					<span class="glyphicon glyphicon-edit"></span>
					<spring:message code="edit" />
				</button>
			</div>
			<div class="clearfix"></div>
		</div>

		<div class="panel-body">
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
						<label class="control-label required" for="txtDoB">${date_of_birth}</label>
						<i>(eg. 01/31/1970)</i>
						<form:input type="text" cssClass="form-control datepicker" id="txtDoB" placeholder="${date_of_birth}" path="dateOfBirth" readonly="${editable ? 'false' : 'true'}"
							required="true" />
						<form:errors path="dateOfBirth" cssClass="control-label" />
					</div>
				</spring:bind>
				<!-- Sex -->
				<spring:bind path="sex">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label required">
							<spring:message code="sex" />
						</label>
						<div class="radio-group">
							<label class="radio-inline">
								<form:radiobutton path="sex" value="M" disabled="${editable ? 'false' : 'true'}" />
								<spring:message code="male" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="sex" value="F" disabled="${editable ? 'false' : 'true'}" />
								<spring:message code="female" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="sex" value="O" disabled="${editable ? 'false' : 'true'}" />
								<spring:message code="other" />
							</label>
							<form:errors path="sex" cssClass="control-label" />
						</div>
					</div>
				</spring:bind>
			</div>

			<!-- Row 3 -->
			<div class="row">
				<!-- Email -->
				<spring:bind path="email">
					<div class="col-md-6 form-group  ${status.error ? 'has-error' : ''}">
						<label class="control-label" for="txtEmpName">${email}</label>
						<form:input type="email" cssClass="form-control" id="txtEmpCode" placeholder="${email}" path="email" readonly="${editable ? 'false' : 'true'}" />
						<form:errors path="email" cssClass="control-label" />
					</div>
				</spring:bind>
				<!-- Phone -->
				<spring:bind path="phoneNo">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label" for="txtPhone">${phone}</label>
						<form:input type="tel" cssClass="form-control" id="txtPhone" placeholder="${phone}" path="phoneNo" readonly="${editable ? 'false' : 'true'}" />
						<form:errors path="phoneNo" cssClass="control-label" />
					</div>
				</spring:bind>
			</div>

			<!-- Row 4 -->
			<div class="row">
				<!-- Status -->
				<spring:bind path="status">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">
							<spring:message code="status" />
						</label>
						<div class="radio-group">
							<label class="radio-inline">
								<form:radiobutton path="status" value="A" disabled="${editable ? 'false' : 'true'}" />
								<spring:message code="active" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="status" value="S" disabled="${editable ? 'false' : 'true'}" />
								<spring:message code="suspended" />
							</label>
							<form:errors path="status" cssClass="control-label" />
						</div>
					</div>
				</spring:bind>
			</div>

			<!-- Buttons group -->
			<div class="btn-group col-md-12">
				<button type="submit" class="btn-lg btn-primary pull-right" ${editable ? '' : 'hidden'}>
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="save" />
				</button>
				<button type="reset" id="btnCancel" class="btn-lg btn-primary pull-right btn-frm-ctrl" ${editable ? '' : 'hidden'}>
					<span class="glyphicon glyphicon-floppy-remove"></span>
					<spring:message code="cancel" />
				</button>
			</div>
		</div>
	</div>
</form:form>

<div class="btn-group btn-group-justified">
	<a class="btn btn-lg btn-primary" href="${employeeUlr}">
		<span class="glyphicon glyphicon-circle-arrow-left"></span>
		<spring:message code="back" />
	</a>
	<a class="btn btn-lg btn-primary" ${editable ? 'hidden' : ''} href="${removeCurrentEmployeeUlr}">
		<span class="glyphicon glyphicon-trash"></span>
		<spring:message code="remove" />
	</a>
</div>