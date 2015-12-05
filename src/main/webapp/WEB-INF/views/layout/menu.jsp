<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/" var="homeUrl" htmlEscape="true" />
<spring:url value="/employee" var="employeeUrl" htmlEscape="true" />

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="${homeUrl}">
				<img src="${pageContext.request.contextPath}/resources/images/spring-pivotal-logo.png" alt="spring-pivotal-logo" />
			</a>
		</div>

		<div>
			<b>
				<ul class="nav navbar-nav">
					<li><a href="${homeUrl}">
							<span class="glyphicon glyphicon-home"></span>
							<spring:message code="home" />
						</a></li>
					<li><a href="${employeeUrl}">
							<spring:message code="employee" />
						</a></li>
				</ul>
			</b>
		</div>

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
</nav>