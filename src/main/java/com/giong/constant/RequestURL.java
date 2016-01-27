package com.giong.constant;

public interface RequestURL {
	public static final String HOME = "/";
	
	public static final String EMPLOYEE_SUMMARY = "/employee";
	public static final String EMPLOYEE_DETAIL = "/employee/{employeeCode}";
	public static final String EMPLOYEE_ADD = "/employee/add";
	public static final String EMPLOYEE_REMOVE = "/employee/removeEmployee/{employeeCode}";
	public static final String EMPLOYEE_REMOVE_BATCH = "/employee/removeBatchEmployee";
}
