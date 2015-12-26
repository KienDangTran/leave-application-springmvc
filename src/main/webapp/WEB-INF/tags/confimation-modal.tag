<%@ tag language="java" pageEncoding="UTF-8" description="Modal Box: Yes/No"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="id" required="true" type="java.lang.String"%>

<%-- confirm modal dialog --%>
<div class="modal fade" id="${id}">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">
					<span class="glyphicon glyphicon-warning-sign"></span>
				</h3>
			</div>

			<div class="modal-body" align="center">
				<jsp:doBody />
			</div>

			<div class="modal-footer">
				<div class="btn-group btn-group-justified">
					<a class="btn btn-lg btn-primary" href="#" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove-sign"></span>
						<spring:message code="no" />
					</a>
					<a class="btn btn-lg btn-primary btn-modal-yes" href="#">
						<span class="glyphicon glyphicon-ok-sign"></span>
						<spring:message code="yes" />
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

