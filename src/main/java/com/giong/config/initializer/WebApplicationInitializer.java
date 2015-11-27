package com.giong.config.initializer;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.giong.config.JpaConfig;
import com.giong.config.SecurityConfig;
import com.giong.config.ServiceConfig;
import com.giong.config.WebFlowConfig;
import com.giong.config.WebMvcConfig;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ServiceConfig.class, JpaConfig.class, WebMvcConfig.class, WebFlowConfig.class, SecurityConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain"), new OpenEntityManagerInViewFilter() };
	}
	
}
