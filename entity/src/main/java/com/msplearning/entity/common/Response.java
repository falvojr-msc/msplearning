package com.msplearning.entity.common;

import java.io.Serializable;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Status status;
	private T entity;
	private String businessMessage;

	public Response() {
		super();
	}

	public Response(Status status) {
		super();
		this.status = status;
	}

	public Response(Status status, T entity) {
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

	public T getEntity() {
		return this.entity;
	}

	public void setEntity(T entity) {
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
