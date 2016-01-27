<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1>Error: 404 - Page not found</h1>
<div>
	<p>
	<div align="center">
		<img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/404.png" alt="404 error" />
		<h2>
			<c:out value="${exception.message}" />
		</h2>
	</div>
	</p>
</div>
