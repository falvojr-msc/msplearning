package com.msplearning.restful.app;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.msplearning.restful.app.generic.CustomMediaType;
import com.msplearning.service.exception.BusinessException;

/**
 * The DownloadRESTfulServer class provides the RESTful services of the
 * resources download.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Component
@Path("/product")
public class ProductRESTfulServer {

	/**
	 * This field is set by Spring on context:property-placeholder configured in
	 * applicationContext.xml
	 */
	@Value("${msplearning.apk.uri}")
	private String uriApk;

	@Path("apk/{id}")
	@GET
	@Produces(CustomMediaType.APPLICATION_ANDROID_PACKAGE_ARCHIVE)
	public Response getAndroidApk(@PathParam("id") Long idApp) throws BusinessException {
		try {
			File fileApk = new File(this.uriApk);
			return Response.ok(fileApk).header("Content-Disposition", "attachment;filename=MSPlearning.apk").build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
