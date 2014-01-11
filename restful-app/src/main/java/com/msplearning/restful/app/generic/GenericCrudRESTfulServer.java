package com.msplearning.restful.app.generic;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.msplearning.service.generic.GenericCrudService;

/**
 * BaseCrudController.
 * 
 * @param <T>
 *            tipo entidade
 * @param <K>
 *            tipo de chave
 * 
 * @author Renan Johannsen de Paula (renanjp)
 */
public abstract class GenericCrudRESTfulServer<T extends Serializable, K extends Serializable> {

	@POST
	public T insert(T entity) throws Exception {
		this.getService().insert(entity);
		return entity;
	}

	@PUT
	public T update(T entity) {
		this.getService().update(entity);
		return entity;
	}

	@GET
	public List<T> getAll() {
		return this.getService().getAll();
	}

	@Path("{id}")
	@GET
	public T getById(@PathParam("id") K id) {
		return this.getService().getById(id);
	}

	@Path("{id}")
	@DELETE
	public void delete(@PathParam("id") K id) {
		this.getService().delete(id);
	}

	protected abstract GenericCrudService<T, K> getService();

}
