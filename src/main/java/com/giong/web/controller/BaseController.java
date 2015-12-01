package com.giong.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.AbstractController;

public abstract class BaseController extends AbstractController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	abstract String getViewName();
	
	abstract Validator getValidator();
	
	
}
