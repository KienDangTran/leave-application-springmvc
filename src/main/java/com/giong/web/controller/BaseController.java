package com.giong.web.controller;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.AbstractController;

public abstract class BaseController extends AbstractController {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	abstract String getViewName();
	
	abstract Validator getValidator();
	
	
}
