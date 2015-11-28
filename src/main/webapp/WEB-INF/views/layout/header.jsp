<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib
	prefix="spring"
	uri="http://www.springframework.org/tags"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<img
	src="resources/images/header.png"
	alt="header" />

<div id="user-box">
	<h3>
		<pre>
			<spring:message code="welcome" /> <a href="#"><sec:authentication property="principal.username" /></a>! | <a href="<c:url value="/logout"/>"><spring:message
					code="logout" /></a>
		</pre>
	</h3>
</div>