package com.giong.config.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.giong.config.JpaConfig;
import com.giong.config.SecurityConfig;
import com.giong.config.WebFlowConfig;
import com.giong.config.WebMvcConfig;
import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Register the Root application context
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebMvcConfig.class, JpaConfig.class, WebFlowConfig.class, SecurityConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		// Register the Dandelion filter
		final FilterRegistration.Dynamic dandelionFilter = servletContext.addFilter("dandelionFilter", new DandelionFilter());
		dandelionFilter.addMappingForUrlPatterns(null, false, "/*");
		
		// Register the Spring dispatcher servlet
		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("spring", new DispatcherServlet(new AnnotationConfigWebApplicationContext()));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		// Register the Dandelion servlet
		final ServletRegistration.Dynamic dandelionServlet = servletContext.addServlet("dandelion", new DandelionServlet());
		dandelionServlet.setLoadOnStartup(2);
		dandelionServlet.addMapping("/dandelion-assets/*");
	}
	
}
