package com.msplearning.android.app.interoperability.util;

/**
 * The RESTfulServer class centralizes constants useful for interfaces with
 * @Rest.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public final class RestServerUrl {

	private RestServerUrl() {
		super();
	}

	/**
	 * URL from local server.
	 */
	public static final String DEBUG = "http://192.168.0.101:8080/restful-app/rest";

	/**
	 * URL from dedicated server.
	 */
	public static final String RELEASE = "http://msplearning.com/restful-app/rest";
}