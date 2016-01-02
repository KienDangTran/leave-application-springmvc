package com.giong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.giong.web.persistence.JsonResponse;
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
		model.addAttribute("readonly", true);
		return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.POST)
	public @ResponseBody JsonResponse updateEmployee(@Validated @RequestBody MtEmployee currentEmp, BindingResult result) {
		final JsonResponse jsonResponse = new JsonResponse();
		if (result.hasErrors()) {
			this.logger.error(result.getAllErrors().toString());
			jsonResponse.setResult(result.getAllErrors());
		}
		else {
			this.currentEmployee = currentEmp;
			this.service.saveEmployee(this.currentEmployee);
			jsonResponse.setStatus(JsonResponse.RESPONSE_STATUS_SUCCESS);
			jsonResponse.setResult(this.messageService.getMessage("msg.all_info_have_been_saved_successfully"));
		}
		return jsonResponse;
	}
	
	@RequestMapping(value = "/employee/add", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		this.currentEmployee = this.service.createEmptyEmployee();
		model.addAttribute("currentEmployee", this.currentEmployee);
		return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
	}
	
	@RequestMapping(value = "/employee/removeCurrentEmployee", method = RequestMethod.GET)
	public String removeCurrentEmployee(Model model) {
		if (this.currentEmployee != null) {
			this.service.removeEmployee(this.currentEmployee);
		}
		return "redirect:/employee";
	}
	
	@RequestMapping(value = "/employee/removeBatchEmployee", method = RequestMethod.GET)
	public String removeBatchEmployee(Model model) {
		return "redirect:/employee";
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
