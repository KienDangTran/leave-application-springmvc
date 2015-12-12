<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/" var="homeUrl" htmlEscape="true" />
<b>
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbarCollapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a href="${homeUrl}">
			<img class="img-responsive navbar-brand" src="${pageContext.request.contextPath}/resources/images/spring-pivotal-logo.png" alt="spring-pivotal-logo" />
		</a>
	</div>

	<div class="collapse navbar-collapse navbarCollapse">
		<ul class="nav navbar-nav">
			<li><a href="${homeUrl}">
					<span class="glyphicon glyphicon-home"></span>
					<spring:message code="home" />
				</a></li>

			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="glyphicon glyphicon-wrench"></span>
					<spring:message code="maintainance_setup" />
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="<spring:url value="/employee" />">
							<spring:message code="employee" />
						</a></li>
					<li><a href="<spring:url value="#" />">
							<spring:message code="user" />
						</a></li>
				</ul></li>

			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
					<span class="glyphicon glyphicon-cog"></span>
					<spring:message code="processing" />
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="<spring:url value="#" />">
							<spring:message code="leave_entitlement" />
						</a></li>
					<li><a href="<spring:url value="#" />">
							<spring:message code="leave_application" />
						</a></li>
				</ul></li>

			<li><a href="<spring:url value="#" />">
					<span class="glyphicon glyphicon-ok"></span>
					<spring:message code="approval" />
				</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">
					<span class="glyphicon glyphicon-user"></span>
					<sec:authentication property="principal.mtEmployee.employeeName" />
				</a></li>
			<li><a href="<c:url value="/logout"/>">
					<span class="glyphicon glyphicon-log-out"></span>
					<spring:message code="logout" />
				</a></li>
		</ul>
	</div>
</b>
