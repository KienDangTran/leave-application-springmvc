<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/" var="homeUrl" htmlEscape="true"/>
<a href="${homeUrl}"><spring:message code="home"/></a>
