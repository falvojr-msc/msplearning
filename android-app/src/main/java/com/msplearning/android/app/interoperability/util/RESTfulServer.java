package com.msplearning.android.app.interoperability.util;

/**
 * The RESTfulServer class centralizes constants useful for interfaces with
 * @Rest.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public final class RESTfulServer {

	private RESTfulServer() {
		super();
	}

	/**
	 * URL from local server.
	 */
	public static final String DEBUG = "192.168.0.102:8080/restful-app/rest";

	/**
	 * URL from dedicated server.
	 */
	public static final String RELEASE = "msplearning.com/restful-app/rest";
}