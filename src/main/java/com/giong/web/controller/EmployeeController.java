package com.giong.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@Override
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ModelAndView mv = new ModelAndView(EmployeeController.EMPLOYEE_VIEW_NAME);
		mv.addObject("allEmployees", this.getAllEmployees());
		return mv;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.GET)
	public ModelAndView viewEmployeeDetails(@PathVariable("employeeCode") String employeeCode) {
		final ModelAndView mv = new ModelAndView(EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME);
		this.currentEmployee = this.service.findEmployeeyCode(employeeCode);
		mv.addObject("currentEmployee", this.currentEmployee);
		return mv;
	}
	
	@RequestMapping(value = "/employee/{employeeCode}", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("currentEmployee") @Validated MtEmployee currentEmp, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			this.logger.error(result.getAllErrors().toString());
			return EmployeeController.EMPLOYEE_DETAILS_VIEW_NAME;
		}
		
		redirectAttributes.addFlashAttribute("css", "success");
		this.currentEmployee = currentEmp;
		this.service.updateEmployee(this.currentEmployee);
		return "redirect:/employee/" + this.currentEmployee.getEmployeeCode();
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
	
	public List<MtEmployee> getAllEmployees() {
		this.allEmployees = this.service.getAllEmployee();
		return this.allEmployees;
	}
	
	public MtEmployee getCurrentEmployee() {
		return this.currentEmployee;
	}
	
	public void setCurrentEmployee(MtEmployee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
}
