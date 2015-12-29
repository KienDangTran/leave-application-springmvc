<%@ tag language="java" pageEncoding="UTF-8" description="input tag for text, emai, tel, date"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="id" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="type" type="java.lang.String" description="input type: text, email, tel, date"%>
<%@ attribute name="required" type="java.lang.Boolean"%>
<%@ attribute name="readonly" type="java.lang.Boolean"%>
<%@ attribute name="placeholder" type="java.lang.String"%>
<%@ attribute name="identity" type="java.lang.Boolean"%>
<%@ attribute name="inputClass" type="java.lang.String"%>
<%@ attribute name="colClass" type="java.lang.String"%>
<%@ attribute name="label" required="true" type="java.lang.String"%>
<%@ attribute name="items" type="java.util.Map" description="Map(value, label) use for type = radio/checkbox"%>

<spring:bind path="${path}">
	<div class="form-group ${empty colClass ? 'col-md-6' : colClass} ${status.error ? 'has-error' : ''}">
		<label class="control-label  ${required ? 'required' : ''}" for="${id}">${label} </label>
		<c:choose>
			<c:when test="${type eq 'date'}">
				<i>(yyyy-mm-dd)</i>
				<form:input type="text" cssClass="form-control datepicker ${inputClass}" id="${id}" placeholder="${placeholder}" path="${path}" readonly="${readonly}" required="${required}" />
			</c:when>

			<c:when test="${type eq 'radio'}">
				<div class="radio-group">
					<c:forEach items="${items}" var="item">
						<label class="radio-inline">
							<form:radiobutton path="${path}" value="${item.key}" disabled="${readonly}" />
							${item.value}
						</label>
					</c:forEach>
				</div>
			</c:when>

			<c:when test="${type eq 'checkbox'}">
				<div class="radio-group">
					<c:forEach items="${items}" var="item">
						<label class="radio-inline">
							<form:checkbox path="${path}" value="${item.key}" disabled="${readonly}" />
							${item.value}
						</label>
					</c:forEach>
				</div>
			</c:when>

			<c:otherwise>
				<form:input type="${empty type ? 'text' : type}" cssClass="form-control ${inputClass} ${identity ? 'identity' : ''}" id="${id}" placeholder="${placeholder}" path="${path}"
					readonly="${readonly}" required="${required}" />
			</c:otherwise>
		</c:choose>
	</div>
</spring:bind>