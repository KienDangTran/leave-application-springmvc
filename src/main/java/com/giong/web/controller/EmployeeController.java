package com.giong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.mt.EmployeeService;
import com.giong.web.validator.EmployeeValidator;

@Controller
public class EmployeeController extends BaseController {
	
	private static final String EMPLOYEE_VIEW_NAME = "employee";
	private static final String EMPLOYEE_DETAILS_VIEW_NAME = "employeeDetails";
	
	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeValidator validator;
	private List<MtEmployee> allEmployees;
	private MtEmployee currentEmployee;
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	protected String getAllEmployee(Model model) throws Exception {
		this.allEmployees = this.service.getAllEmployee();
		model.addAttribute("allEmployees", this.allEmployees);
		return EmployeeController.EMPLOYEE_VIEW_NAME;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.GET)
	public String viewEmployeeDetails(@PathVariable("employeeCode") String employeeCode, Model model) {
		this.currentEmployee = this.service.findEmployeeyCode(employeeCode);
		model.addAttribute("currentEmployee", this.currentEmployee);
		model.addAttribute("editable", false);
		return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("currentEmployee") @Validated MtEmployee currentEmp, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			this.logger.error(result.getAllErrors().toString());
			model.addAttribute("editable", true);
			return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
		}
		
		model.addAttribute("editable", false);
		this.currentEmployee = currentEmp;
		this.service.saveEmployee(this.currentEmployee);
		return "redirect:/employee/" + this.currentEmployee.getEmployeeCode();
	}
	
	@RequestMapping(value = "/employee/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		this.currentEmployee = this.service.createEmptyEmployee();
		model.addAttribute("currentEmployee", this.currentEmployee);
		model.addAttribute("editable", true);
		return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
	}
	
	/*
	 * GETTER & SETTER
	 */
	@Override
	String getViewName() {
		return EmployeeController.EMPLOYEE_VIEW_NAME;
	}
	
	@Override
	Validator getValidator() {
		return this.validator;
	}
	
	public MtEmployee getCurrentEmployee() {
		return this.currentEmployee;
	}
	
	public void setCurrentEmployee(MtEmployee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
	
}
