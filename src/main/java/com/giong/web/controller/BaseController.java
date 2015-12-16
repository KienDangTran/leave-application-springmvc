package com.giong.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public abstract class BaseController extends AbstractController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	abstract String getViewName();
	
	abstract Validator getValidator();
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.getValidator());
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		final ModelAndView model = new ModelAndView();
		//		final Map<String, Object> results = GetResultGenerator().getResultForController(q);
		//		model.addObject("data", results);
		model.setViewName(this.getViewName());
		
		this.logger.debug("getModelAndView : [model] : {}", model);
		
		return model;
	}
	
}
