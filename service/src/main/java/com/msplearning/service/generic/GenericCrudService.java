package com.msplearning.service.generic;

import java.io.Serializable;
import java.util.List;

import com.msplearning.entity.common.BusinessException;
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

	public void insert(T entity) throws BusinessException {
		this.validate(entity);
		this.getRepository().insert(entity);
	}

	public void update(T entity) throws BusinessException {
		this.validate(entity);
		this.getRepository().update(entity);
	}

	public List<T> getAll() {
		return this.getRepository().findAll();
	}

	public T findById(K id) throws BusinessException {
		T entity = this.getRepository().findById(id);
		if (entity == null) {
			throw new BusinessException(this.getMessage("R.message.003"));
		}
		return entity;
	}

	public void delete(K id) throws BusinessException {
		this.findById(id);
		this.getRepository().delete(id);
	}

	protected void validate(T entity) throws BusinessException { }

	protected abstract GenericRepository<T, K> getRepository();
}
