package com.giong.config.context;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

@Configuration
public class WebFlowContext extends AbstractFlowConfiguration {
	
	@Autowired
	private WebMvcContext webMvcContext;
	
	@Bean(name = "flowExecutor")
	public FlowExecutor flowExecutor() {
		return this.getFlowExecutorBuilder(this.flowRegistry()).addFlowExecutionListener(new SecurityFlowExecutionListener(), "*").build();
	}
	
	@Bean(name = "flowRegistry")
	public FlowDefinitionRegistry flowRegistry() {
		return this.getFlowDefinitionRegistryBuilder(this.flowBuilderServices()).setBasePath("/WEB-INF").addFlowLocationPattern("/**/*-flow.xml").build();
	}
	
	@Bean(name = "flowBuilderServices")
	public FlowBuilderServices flowBuilderServices() {
		return this.getFlowBuilderServicesBuilder().setViewFactoryCreator(this.mvcViewFactoryCreator()).setValidator(this.validator()).setDevelopmentMode(true).build();
	}
	
	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		final MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Arrays.<ViewResolver> asList(this.webMvcContext.viewResolver()));
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
