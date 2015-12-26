<%@ tag language="java" pageEncoding="UTF-8" description="display form's errors or success messages"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="successMessages" type="java.lang.String" description="success messages (String)"%>
<%@ attribute name="status" type="org.springframework.web.servlet.support.BindStatus" description="bind status (org.springframework.web.servlet.support.BindStatus)"%>


<spring:bind path="*">
	<c:if test="${status.error}">
		<div class="frm-notification alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong> <form:errors path="*" cssClass="control-label" />
			</strong>
		</div>
	</c:if>
	<c:if test="${not empty messages}">
		<div class="frm-notification alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong> ${messages} </strong>
		</div>
	</c:if>
</spring:bind>
