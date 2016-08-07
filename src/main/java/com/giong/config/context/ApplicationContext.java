package com.giong.config.context;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@ComponentScan({ "com.giong.web.service" })
@Import({ WebMvcContext.class, PersistenceContext.class, SecurityContext.class, WebFlowContext.class })
public class ApplicationContext {
	private static final String MESSAGE_SOURCE_BASE_NAME = "classpath:i18n/messages";

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename(ApplicationContext.MESSAGE_SOURCE_BASE_NAME);
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
}
