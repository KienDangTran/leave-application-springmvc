<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1>ERROR: 403 - Access Denied</h1>
<div align="center">
	<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/403.png" alt="403 error" />
	<h2>
		<c:out value="${exception.message}" />
	</h2>
</div>