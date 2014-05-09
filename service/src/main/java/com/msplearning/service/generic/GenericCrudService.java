package com.msplearning.service.generic;

import java.io.Serializable;
import java.util.List;

import com.msplearning.repository.generic.GenericRepository;

/**
 * The BaseCrucService class.
 * 
 * @param <T>
 *            generic type
 * @param <K>
 *            key type
 * 
 * @author Renan Johannsen de Paula (renanjp)
 */
public abstract class GenericCrudService<T extends Serializable, K extends Serializable> extends BaseService {

	public void insert(T entity) {
		this.getRepository().insert(entity);
	}

	public void update(T entity) {
		this.getRepository().update(entity);
	}

	public List<T> getAll() {
		return this.getRepository().findAll();
	}

	public T getById(K id) {
		return this.getRepository().findById(id);
	}

	public void delete(K id) {
		this.getRepository().delete(id);
	}

	protected abstract GenericRepository<T, K> getRepository();
}
