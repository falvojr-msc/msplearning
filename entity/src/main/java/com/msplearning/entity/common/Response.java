package com.msplearning.entity.common;

public class Response {

	private Status status;
	private Object entity;
	private String businessMessage;

	public Response() {
		super();
	}

	public Response(Status status) {
		super();
		this.status = status;
	}

	public Response(Status status, Object entity) {
		super();
		this.status = status;
		this.entity = entity;
	}

	public Response(Status status, String businessMessage) {
		super();
		this.status = status;
		this.businessMessage = businessMessage;
	}

	public Response(Status status, BusinessException businessException) {
		super();
		this.status = status;
		if (businessException != null) {
			this.businessMessage = businessException.getMessage();
		}
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getEntity() {
		return this.entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public String getBusinessMessage() {
		return this.businessMessage;
	}

	public void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	}

	public boolean hasBusinessMessage() {
		return (this.businessMessage != null) && !"".equals(this.businessMessage.trim());
	}
}
