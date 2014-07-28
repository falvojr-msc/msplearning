package com.msplearning.rest.app.generic;

import java.io.Serializable;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.msplearning.entity.common.BusinessException;

/**
 * The GenericCrudRestServer class.
 *
 * @param <T>
 *            Entiity type.
 * @param <K>
 *            Key type.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
public abstract class GenericCrudRestService<T extends Serializable, K extends Serializable> extends GenericBaseCrudRestService<T, K> {

	@GET
	@Path("{id}")
	public T findById(@PathParam("id") K id) throws BusinessException {
		return this.getService().findById(id);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") K id) throws BusinessException {
		this.getService().delete(id);
	}
}
