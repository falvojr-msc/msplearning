package com.msplearning.service.generic;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * The BaseService class. Load the {@link MessageSource} based on default locale.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
public class BaseService {

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	protected String getMessage(String key, Object... params) {
		return this.messageSource.getMessage(key, params, Locale.getDefault());
	}
}
