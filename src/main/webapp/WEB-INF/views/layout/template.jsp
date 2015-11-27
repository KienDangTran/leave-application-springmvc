<%@taglib
	uri="http://tiles.apache.org/tags-tiles"
	prefix="tiles"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>

<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	session="false"%>

<!DOCTYPE html>

<html>
<head>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">

<title><tiles:insertAttribute
		name="title"
		ignore="true" /></title>
</head>

<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="content">
		<tiles:insertAttribute name="content" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>