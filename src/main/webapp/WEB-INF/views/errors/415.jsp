<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>ERROR: 415 - Unsupport Media Type</h1>
<div class="page-content">
    <img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/415.jpg" alt="415 error"/>
    <c:out value="${exception}"/>
</div>
