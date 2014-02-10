package com.msplearning.entity;

/**
 * The FeatureOperator enum.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public enum FeatureOperator {

	/**
	 * And operator.
	 */
	AND("A", "And"),
	/**
	 * Or operator.
	 */
	OR("O", "Or"),
	/**
	 * Alternative operator, same logic of an XOR gate.
	 */
	ALTERNATIVE("X", "Alternative");

	private String code;
	private String value;

	private FeatureOperator(String code, String value) {
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
