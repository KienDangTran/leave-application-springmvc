package com.giong.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {
	
	private static final String VIEW_NAME = "home";
	
	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Locale locale = request.getLocale();
		
		final Date date = new Date();
		final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		final String formattedDate = dateFormat.format(date);
		final ModelAndView modelNView = new ModelAndView(this.getViewName());
		modelNView.addObject("serverTime", formattedDate);
		
		this.logger.info("Welcome home! The client locale is {}. Server time is {}", locale.getDisplayCountry(), formattedDate);
		
		return modelNView;
	}
	
	@Override
	String getViewName() {
		return HomeController.VIEW_NAME;
	}
	
	@Override
	Validator getValidator() {
		return null;
	}
	
}
