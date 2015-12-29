<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="giong" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<meta name="viewport" content="initial-scale=1, maximum-scale=1"></meta>

<sec:csrfMetaTags />

<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/webjars/jquery-ui/1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<div id="header" class="container-fluid">
		<tiles:insertAttribute name="header" />
	</div>
	<nav class="navbar navbar-inverse" data-spy="affix">
		<tiles:insertAttribute name="menu" />
	</nav>
	<div id="content" class="container">
		<tiles:insertAttribute name="content" />
		<giong:confimation-modal id="confimationModal">
			<spring:message code="confirmation.are_u_sure" />
		</giong:confimation-modal>
	</div>
	<div id="footer" class="container">
		<tiles:insertAttribute name="footer" />
	</div>

	<script src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/webjars/jquery-ui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.tools.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		jQuery.noConflict();
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js" type="text/javascript"></script>
</body>
</html>