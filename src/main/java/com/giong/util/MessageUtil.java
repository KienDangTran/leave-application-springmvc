package com.giong.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil implements MessageSourceAware {
	
	public static MessageUtil instance() {
		return new MessageUtil();
	}
	
	private MessageSource messageSource;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessages(String code, Object... param) {
		final String msg = this.messageSource.getMessage(code, param, LocaleContextHolder.getLocale());
		return msg;
	}
	
	public String getMessage(String code) {
		return this.getMessages(code, new Object[] {});
	}
}
