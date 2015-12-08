<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<meta name="viewport" content="initial-scale=1, maximum-scale=1"></meta>

<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/webjars/jquery-ui/1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<div id="header" class="container-fluid">
		<tiles:insertAttribute name="header" />
	</div>
	<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="250">
		<tiles:insertAttribute name="menu" />
	</nav>
	<div id="content" class="container">
		<tiles:insertAttribute name="content" />
	</div>
	<div id="footer" class="container">
		<tiles:insertAttribute name="footer" />
	</div>

	<script src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/webjars/jquery-ui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js" type="text/javascript"></script>
</body>
</html>