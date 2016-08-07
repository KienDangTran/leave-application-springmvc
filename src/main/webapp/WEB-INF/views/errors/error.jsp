<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>ERROR: Internal Server Error</h1>
<div align="center">
    <h2>
        <c:out value="${exception.message}"/>
    </h2>
</div>
