package com.msplearning.rest.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/rest/*")
public class LoggingFilter implements Filter {

	private Logger logger;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.logger = LoggerFactory.getLogger(LoggingFilter.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			this.localeChangeInterceptor(request);
			chain.doFilter(request, response);
		} catch (Exception exception) {
			this.logger.error("Unexpected exception", exception);
		}
	}

	/**
	 * Identify and set the default {@link Locale} for the application.
	 *
	 * @param request
	 *            {@link ServletRequest}
	 */
	private void localeChangeInterceptor(ServletRequest request) {
		String langParam = request.getParameter("lang");
		if (langParam == null) {
			Locale.setDefault(Locale.US);
		} else {
			Locale localeLang = Locale.forLanguageTag(langParam);
			if (localeLang == null || localeLang.toLanguageTag() == "") {
				Locale.setDefault(Locale.US);
			} else {
				Locale.setDefault(localeLang);
			}
		}
	}

	@Override
	public void destroy() {

	}
}
