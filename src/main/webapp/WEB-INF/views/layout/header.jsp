<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/" var="homeUrl" htmlEscape="true" />

<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
    	<span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="${homeUrl}" style="padding-top: 0px;"><img src="${pageContext.request.contextPath}/resources/images/header.png" alt="header" /></a>
</div>
