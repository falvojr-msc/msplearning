package com.msplearning.restful.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msplearning.entity.util.BusinessException;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*")
public class LoggingFilter implements Filter {

	private Logger logger;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (BusinessException exception) {
			this.logger.error("Business exception", exception);
		} catch (Exception exception) {
			this.logger.error("Unexpected exception", exception);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.logger = LoggerFactory.getLogger(LoggingFilter.class);
	}

}
