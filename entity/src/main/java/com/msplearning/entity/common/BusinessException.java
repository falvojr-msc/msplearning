package com.msplearning.entity.common;

/**
 * The ServiceException class, a generic {@link RuntimeException} for business
 * exceptions.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -5014697280512219136L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
