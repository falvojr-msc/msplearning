package com.msplearning.android.app.rest;

/**
 * The RESTfulServer class centralizes constants useful for interfaces with
 * @Rest.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public final class RestServerUtil {

	private RestServerUtil() {
		super();
	}

	/**
	 * URL from local server.
	 */
	public static final String DEBUG = "http://192.168.1.101:8080/rest-app/rest";

	/**
	 * URL from dedicated server.
	 */
	public static final String RELEASE = "http://msplearning.com/rest-app/rest";
}