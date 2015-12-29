<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="giong" tagdir="/WEB-INF/tags"%>

<spring:message code="employee_code" var="i18n_employee_code" />
<spring:message code="employee_name" var="i18n_employee_name" />
<spring:message code="date_of_birth" var="i18n_date_of_birth" />
<spring:message code="sex" var="i18n_sex" />
<spring:message code="email" var="i18n_email" />
<spring:message code="phone" var="i18n_phone" />
<spring:message code="status" var="i18n_status" />

<spring:url value="/employee/${currentEmployee.employeeCode}" var="updateEmployeeUlr" />
<spring:url value="/employee" var="employeeUlr" />
<spring:url value="/employee/removeCurrentEmployee" var="removeCurrentEmployeeUlr" />

<form:form cssClass="ajax-frm" method="POST" modelAttribute="currentEmployee" action="${updateEmployeeUlr}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="pull-left">
				<spring:message code="basic_information" />
			</h3>
			<div class="btn-group pull-right">
				<button type="button" class="btn-lg btn-primary" ${readonly ? '' : 'hidden'} onclick="enableEditing(currentEmployee)">
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
				<giong:form-input label="${i18n_employee_code}" path="employeeCode" identity="true" readonly="true" required="true" placeholder="${i18n_employee_code}" />

				<!-- Employee Name -->
				<giong:form-input label="${i18n_employee_name}" path="employeeName" required="true" readonly="${readonly}" placeholder="${i18n_employee_name}" />
			</div>

			<!-- Row 2 -->
			<div class="row">
				<!-- Date of Birth -->
				<giong:form-input path="dateOfBirth" type="date" readonly="${readonly}" required="true" label="${i18n_date_of_birth}" placeholder="${i18n_date_of_birth}" />

				<!-- Sex -->
				<spring:bind path="sex">
					<div class="col-md-6 form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label required">
							<spring:message code="sex" />
						</label>
						<div class="radio-group">
							<label class="radio-inline">
								<form:radiobutton path="sex" value="M" disabled="${readonly}" />
								<spring:message code="male" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="sex" value="F" disabled="${readonly}" />
								<spring:message code="female" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="sex" value="O" disabled="${readonly}" />
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
				<giong:form-input label="${i18n_email}" path="email" type="email" placeholder="${i18n_email}" readonly="${readonly}" />

				<!-- Phone -->
				<giong:form-input label="${i18n_phone}" path="phoneNo" type="tel" placeholder="${i18n_phone}" readonly="${readonly}" />
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
								<form:radiobutton path="status" value="A" disabled="${readonly}" />
								<spring:message code="active" />
							</label>
							<label class="radio-inline">
								<form:radiobutton path="status" value="S" disabled="${readonly}" />
								<spring:message code="suspended" />
							</label>
							<form:errors path="status" cssClass="control-label" />
						</div>
					</div>
				</spring:bind>
			</div>

			<!-- Buttons group -->
			<div class="btn-group col-md-12">
				<button type="submit" class="btn-lg btn-primary pull-right" ${readonly ? 'hidden' : ''}>
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="save" />
				</button>
				<button type="reset" id="btnCancel" class="btn-lg btn-primary pull-right" ${readonly ? 'hidden' : ''} onclick="disableEditing(currentEmployee)">
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
	<a class="btn btn-lg btn-primary modal-input-href" onclick="openConfirmationModal(this); return false;" rel="#confimationModal" rev="${removeCurrentEmployeeUlr}"
		data-toggle="modal" data-target="#confimationModal">
		<span class="glyphicon glyphicon-trash"></span>
		<spring:message code="remove" />
	</a>
</div>