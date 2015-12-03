package com.giong.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.mt.EmployeeService;

@Controller
public class EmployeeController extends BaseController {
	
	private static final String VIEW_NAME = "employee";
	
	@Autowired
	private EmployeeService empService;
	
	private List<MtEmployee> allEmployees;
	
	@Override
	String getViewName() {
		return EmployeeController.VIEW_NAME;
	}
	
	@Override
	Validator getValidator() {
		return null;
	}
	
	@Override
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ModelAndView modelNView = new ModelAndView(EmployeeController.VIEW_NAME);
		modelNView.addObject("allEmployees", this.getAllEmployees());
		return modelNView;
	}
	
	/*
	 * GETTER & SETTER
	 */
	public List<MtEmployee> getAllEmployees() {
		this.allEmployees = this.empService.getAllEmployee();
		return this.allEmployees;
	}
}
