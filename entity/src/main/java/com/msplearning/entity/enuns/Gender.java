package com.msplearning.entity.enuns;

/**
 * The Gender enum.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public enum Gender {

	/**
	 * Male sex.
	 */
	M("M", "Male"),

	/**
	 * Female Sex.
	 */
	F("F", "Female");

	private String code;
	private String value;

	private Gender(String code, String value) {
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
