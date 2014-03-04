package com.msplearning.entity.common;

public enum Status {
	OK(200),
	SERVER_ERROR(500);

	private int statusCode;

	private Status(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public static Status fromStatusCode(int statusCode) {
		for (Status enumElement : Status.values()) {
			if (statusCode == enumElement.getStatusCode()) {
				return enumElement;
			}
		}
		return null;
	}
}
