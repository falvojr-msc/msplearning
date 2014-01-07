package com.msplearning.restful.app.generic;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msplearning.service.generic.GenericCrudService;

/**
 * BaseCrudController.
 * 
 * @param <T> tipo entidade
 * @param <K> tipo de chave
 * @author renan
 */
public abstract class GenericCrudRESTfulServer<T extends Serializable, K extends Serializable> {

	private final Logger logger = LoggerFactory.getLogger(GenericCrudRESTfulServer.class);
	
	@POST
	public T insert(T entity) {
		try {
			this.getService().insert(entity);
		} catch (Exception exception) {
			if (entity == null)
				logger.error("The entity is null.", exception);
			else
				logger.error(String.format("An error occurred while trying to insert a %s class instance.", entity.getClass().getSimpleName(), exception));
			return null;
		}
		return entity;
	}

	@PUT
	public T update(T entity) {
		try {
			this.getService().update(entity);
		} catch (Exception exception) {
			if (entity == null)
				logger.error("The entity is null.", exception);
			else
				logger.error(String.format("An error occurred while trying to update a %s class instance.", entity.getClass().getSimpleName(), exception));
			return null;
		}
		return entity;
	}

	@GET
	public List<T> getAll() {
		try{
			return this.getService().getAll();
		} catch (Exception exception) {
			logger.error(String.format("An error occurred while trying find all elements.", exception));
			return null;
		}
	}

	@Path("{id}")
	@GET
	public T getById(@PathParam("id") K id) {
		try {
			return this.getService().getById(id);
		} catch (Exception exception) {
			if (id == null)
				logger.error("The id is null.", exception);
			else
				logger.error(String.format("An error occurred while trying find by id %d.", id, exception));
			return null;
		}
	}

	@Path("{id}")
	@DELETE
	public void delete(@PathParam("id") K id) {
		try {
			this.getService().delete(id);
		} catch (Exception exception) {
			if (id == null)
				logger.error("The id is null.", exception);
			else
				logger.error(String.format("An error occurred while trying delete the id %d.", id, exception));
		}
	}

	protected abstract GenericCrudService<T, K> getService();

}

