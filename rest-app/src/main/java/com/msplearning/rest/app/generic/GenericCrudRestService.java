package com.msplearning.rest.app.generic;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.msplearning.entity.common.BusinessException;
import com.msplearning.entity.common.Response;
import com.msplearning.entity.common.Status;
import com.msplearning.service.generic.GenericCrudService;

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
public abstract class GenericCrudRestService<T extends Serializable, K extends Serializable> {

	@POST
	public Response<T> insert(T entity) {
		try {
			this.getService().insert(entity);
			return new Response<T>(Status.OK, entity);
		} catch (BusinessException businessException) {
			return new Response<T>(Status.OK, businessException);
		}
	}

	@PUT
	public Response<T> update(T entity) {
		try {
			this.getService().update(entity);
			return new Response<T>(Status.OK, entity);
		} catch (BusinessException businessException) {
			return new Response<T>(Status.OK, businessException);
		}
	}

	@GET
	public Response<List<T>> getAll() {
		try {
			return new Response<List<T>>(Status.OK, this.getService().getAll());
		} catch (BusinessException businessException) {
			return new Response<List<T>>(Status.OK, businessException);
		}
	}

	@Path("{id}")
	@GET
	public Response<T> getById(@PathParam("id") K id) {
		try {
			return new Response<T>(Status.OK, this.getService().getById(id));
		} catch (BusinessException businessException) {
			return new Response<T>(Status.OK, businessException);
		}
	}

	@Path("{id}")
	@DELETE
	public Response<Void> delete(@PathParam("id") K id) {
		try {
			this.getService().delete(id);
			return new Response<Void>(Status.OK);
		} catch (BusinessException businessException) {
			return new Response<Void>(Status.OK, businessException);
		}
	}

	protected abstract GenericCrudService<T, K> getService();

}
