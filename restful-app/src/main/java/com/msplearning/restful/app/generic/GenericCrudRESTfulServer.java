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
	public T insert(T entity) {
		try {
			this.getService().insert(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	@PUT
	public T update(T entity) {
		try {
			this.getService().update(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	@GET
	public List<T> getAll() {
		try {
			return this.getService().getAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Path("{id}")
	@GET
	public T getById(@PathParam("id") K id) {
		try{
			return this.getService().getById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@Path("{id}")
	@DELETE
	public void delete(@PathParam("id") K id) {
		try {
			this.getService().delete(id);
		} catch (Exception e) {
		}
	}

	protected abstract GenericCrudService<T, K> getService();

}
