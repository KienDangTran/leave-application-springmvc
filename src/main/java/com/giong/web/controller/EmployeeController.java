package com.giong.web.controller;

import com.giong.constant.RequestURL;
import com.giong.constant.View;
import com.giong.exception.NotFoundException;
import com.giong.web.persistence.JsonResponse;
import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.mt.EmployeeService;
import com.giong.web.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeValidator employeeValidator;

	@RequestMapping(value = RequestURL.EMPLOYEE_SUMMARY, method = RequestMethod.GET)
	protected String getAllEmployee(Model model) throws Exception {
		model.addAttribute("allEmployees", this.employeeService.getAllEmployee());
		return View.EMPLOYEE_SUMMARY.getViewName();
	}

	@RequestMapping(value = RequestURL.EMPLOYEE_DETAIL, method = RequestMethod.GET)
	public String viewEmployeeDetails(@PathVariable("employeeCode") String employeeCode, Model model) {
		final MtEmployee currentEmployee = this.employeeService.findEmployeeyByCode(employeeCode);
		if (currentEmployee == null)
			throw new NotFoundException(this.messageService.getMessages("err.employee_is_not_found", employeeCode));
		model.addAttribute("currentEmployee", currentEmployee);
		model.addAttribute("readonly", true);
		return View.EMPLOYEE_DETAIL.getViewName();
	}

	@RequestMapping(value = RequestURL.EMPLOYEE_DETAIL, method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveOrUpdateEmployee(@Validated @RequestBody MtEmployee currentEmployee,
		BindingResult result) {
		final JsonResponse jsonResponse = new JsonResponse();
		if (result.hasErrors()) {
			jsonResponse.setResult(result.getAllErrors());
		} else {
			this.employeeService.saveOrUpdateEmployee(currentEmployee);
			jsonResponse.setStatus(JsonResponse.RESPONSE_STATUS_SUCCESS);
			jsonResponse.setResult(this.messageService.getMessages("msg.all_info_have_been_saved_successfully"));
		}
		return jsonResponse;
	}

	@RequestMapping(value = RequestURL.EMPLOYEE_ADD, method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("currentEmployee", this.employeeService.createEmptyEmployee());
		return View.EMPLOYEE_DETAIL.getViewName();
	}

	@RequestMapping(value = RequestURL.EMPLOYEE_REMOVE, method = RequestMethod.GET)
	public String removeEmployee(@PathVariable("employeeCode") String employeeCode,
		RedirectAttributes redirectAttributes) {
		this.employeeService.removeEmployee(employeeCode);
		return "redirect:/" + View.EMPLOYEE_SUMMARY.getViewName();
	}

	@RequestMapping(value = RequestURL.EMPLOYEE_REMOVE_BATCH, method = RequestMethod.GET)
	public String removeBatchEmployees(Model model) {
		return "redirect:/" + View.EMPLOYEE_SUMMARY.getViewName();
	}

	/*
	 * GETTER & SETTER
	 */
	@Override
	String getViewName() {
		return View.EMPLOYEE_SUMMARY.getViewName();
	}

	@Override
	Validator getValidator() {
		return this.employeeValidator;
	}
}
