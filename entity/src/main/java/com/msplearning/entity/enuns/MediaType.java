package com.msplearning.entity.enuns;

/**
 * The MediaType enum.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public enum MediaType {

	/**
	 * Text type.
	 */
	T("T", "Text"),
	/**
	 * Image type.
	 */
	I("I", "Image"),
	/**
	 * Video type.
	 */
	V("V", "Video");

	private String code;
	private String value;

	private MediaType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}
}
