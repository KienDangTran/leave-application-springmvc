package com.giong.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.mt.EmployeeService;

@Controller
public class EmployeeController extends BaseController {
	
	private static final String EMPLOYEE_VIEW_NAME = "employee";
	private static final String EMPLOYEE_DETAILS_VIEW_NAME = "employeeDetails";
	
	@Autowired
	private EmployeeService empService;
	
	private List<MtEmployee> allEmployees;
	
	@Override
	String getViewName() {
		return EmployeeController.EMPLOYEE_VIEW_NAME;
	}
	
	@Override
	Validator getValidator() {
		return null;
	}
	
	@Override
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ModelAndView mv = new ModelAndView(EmployeeController.EMPLOYEE_VIEW_NAME);
		mv.addObject("allEmployees", this.getAllEmployees());
		return mv;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.GET)
	public ModelAndView employeeDetails(@PathVariable("employeeCode") String employeeCode) {
		final ModelAndView mv = new ModelAndView(EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME);
		final MtEmployee employee = this.empService.findEmployeeyCode(employeeCode);
		mv.addObject("employee", employee);
		return mv;
	}
	
	/*
	 * GETTER & SETTER
	 */
	public List<MtEmployee> getAllEmployees() {
		this.allEmployees = this.empService.getAllEmployee();
		return this.allEmployees;
	}
}
