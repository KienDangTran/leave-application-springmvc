package com.giong.test.config;

import com.giong.config.context.PersistenceContext;
import com.giong.config.context.SecurityContext;
import com.giong.config.context.WebFlowContext;
import com.giong.config.context.WebMvcContext;
import com.giong.web.service.MessageService;
import com.giong.web.service.mt.EmployeeService;
import com.giong.web.service.mt.SchemeService;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan({ "com.giong.web.service" })
@Import({ WebMvcContext.class, PersistenceContext.class, SecurityContext.class, WebFlowContext.class })
public class TestContext {
	private static final String MESSAGE_SOURCE_BASE_NAME = "classpath:i18n/messages";

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename(TestContext.MESSAGE_SOURCE_BASE_NAME);
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean
	public EmployeeService employeeService() {
		return Mockito.mock(EmployeeService.class);
	}

	@Bean
	public MessageService messageService() {
		return Mockito.mock(MessageService.class);
	}

	@Bean
	public SchemeService schemeService() {
		return Mockito.mock(SchemeService.class);
	}
}
