package com.msplearning.entity.enuns;

/**
 * The FeatureOperator enum.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public enum FeatureOperator {

	/**
	 * And operator.
	 */
	A("A", "And"),
	/**
	 * Or operator.
	 */
	O("O", "Or"),
	/**
	 * Alternative operator, same logic of an XOR gate.
	 */
	X("X", "Alternative");

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
