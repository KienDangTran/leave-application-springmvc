<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="spring"
	uri="http://www.springframework.org/tags"%>
	
<spring:url
	value="/"
	var="dashboardUrl"
	htmlEscape="true" />
<spring:url
	value="/employee"
	var="employeeUrl"
	htmlEscape="true" />

<ul class="cd-accordion-menu animated">

	<li><a href="${dashboardUrl}"><spring:message code="dashboard" /></a></li>

	<li class="has-children"><input
		type="checkbox"
		name="maintainance"
		id="maintainance"><label for="maintainance"><spring:message code="maintainance_setup" /></label>
		<ul>
			<li class="has-children"><input
				type="checkbox"
				name="admin"
				id="admin"> <label for="admin"><spring:message code="administrator" /></label>
				<ul>
					<li><a href="${employeeUrl}"><spring:message code="employee" /></a></li>
				</ul></li>

			<li class="has-children"><input
				type="checkbox"
				name="super-admin"
				id="super-admin"> <label for="super-admin"><spring:message code="super_administrator" /></label>
				<ul>
					<li><a href="#0"><spring:message code="user" /></a></li>
				</ul></li>
		</ul></li>

	<li class="has-children"><input
		type="checkbox"
		name="processing"
		id="processing"> <label for="processing"><spring:message code="processing" /> </label>
		<ul>
			<li class="has-children"><input
				type="checkbox"
				name="leave"
				id="leave"> <label for="leave"><spring:message code="leave" /></label>
				<ul>
					<li><a href="#0"><spring:message code="leave_entitlement" /></a></li>
					<li><a href="#0"><spring:message code="leave_application" /></a></li>
				</ul></li>

			<li class="has-children"><input
				type="checkbox"
				name="approval"
				id="approval"> <label for="approval"><spring:message code="approval" /></label>
				<ul>
					<li><a href="#0"><spring:message code="approval_summary" /></a></li>
				</ul></li>
		</ul></li>

	<li class="has-children"><input
		type="checkbox"
		name="enquiry"
		id="enquiry"> <label for="enquiry"><spring:message code="enquiry" /></label>
		<ul>
			<li><a href="#0"><spring:message code="leave_enquiry" /></a></li>
		</ul></li>

	<li class="has-children"><input
		type="checkbox"
		name="report"
		id="report"> <label for="report"><spring:message code="report" /></label>

		<ul>
			<li><a href="#0"><spring:message code="leave_balance_report" /></a></li>
		</ul></li>
</ul>

<script src="resources/js/jquery-2.1.4.js"></script>
<script src="resources/js/main.js"></script>
