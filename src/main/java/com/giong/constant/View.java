package com.giong.constant;

public enum View {
	ERROR("error/error", "/WEB-INF/views/errors/error.jsp"),
	ERROR_403("error/403", "/WEB-INF/views/errors/403.jsp"),
	ERROR_404("error/404", "/WEB-INF/views/errors/404.jsp"),
	HOME("home", "/WEB-INF/views/home.jsp"),
	EMPLOYEE_SUMMARY("maintainance_setup/employee", "/WEB-INF/views/maintainance_setup/employee/employee.jsp"),
	EMPLOYEE_DETAIL("maintainance_setup/employee/employeeDetails",
		"/WEB-INF/views/maintainance_setup/employee/employee-details.jsp");

	private String viewName;
	private String viewUrl;

	View(String viewName, String viewUrl) {
		this.viewName = viewName;
		this.viewUrl = viewUrl;
	}

	public String getViewName() {
		return this.viewName;
	}

	public String getViewUrl() {
		return this.viewUrl;
	}

	@Override
	public String toString() {
		return this.getViewName();
	}
}
