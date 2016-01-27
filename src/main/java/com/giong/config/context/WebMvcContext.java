package com.giong.config.context;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.giong.config.tiles.TilesConfig;
import com.giong.constant.View;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.giong.web.controller, com.giong.web.validator" })
public class WebMvcContext extends WebMvcConfigurerAdapter {
	
	private static final String LOCALE_PARAM = "locale";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/resources/**")) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * <code>Resolves views selected for rendering by @Controllers to tiles resources in the Apache TilesConfigurer bean</code>
	 */
	@Bean(name = "viewResolver")
	public UrlBasedViewResolver viewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
	/**
	 * <code>Configures Apache tiles definitions bean used by Apache TilesViewResolver to resolve views selected for rendering by @Controllers</code>
	 */
	@Bean(name = "tilesConfigurer")
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setCheckRefresh(true);
		tilesConfigurer.setDefinitionsFactoryClass(TilesConfig.class);
		TilesConfig.addDefinitions();
		return tilesConfigurer;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		final LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName(WebMvcContext.LOCALE_PARAM);
		registry.addInterceptor(interceptor);
	}
	
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		final SimpleMappingExceptionResolver exResolver = new SimpleMappingExceptionResolver();
		
		final Properties exMappings = new Properties();
		exMappings.put("org.springframework.security.access.AccessDeniedException", View.ERROR_403.getViewName());
		exMappings.put("com.giong.exception.NotFoundException", View.ERROR_404.getViewName());
		exMappings.put("java.lang.Exception", View.ERROR.getViewName());
		exMappings.put("java.lang.RuntimeException", View.ERROR.getViewName());
		exResolver.setExceptionMappings(exMappings);
		
		final Properties statusCodes = new Properties();
		statusCodes.put(View.ERROR_403.getViewName(), "403");
		statusCodes.put(View.ERROR_404.getViewName(), "404");
		statusCodes.put(View.ERROR.getViewName(), "500");
		exResolver.setStatusCodes(statusCodes);
		
		return exResolver;
	}
}
