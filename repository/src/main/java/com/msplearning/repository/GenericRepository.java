package com.msplearning.repository;

import java.io.Serializable;
import java.util.List;

import com.msplearning.repository.jpa.GenericRepositoryJpa;

/**
 * Interface of {@link GenericRepositoryJpa}.
 * 
 * @author Renan Johannsen de Paula (renanjp)
 * 
 * @param <T> entity class
 */
public interface GenericRepository<T extends Serializable> {
	void insert(T entity);

	void update(T entity);

	void remove(Serializable id);

	T findById(Serializable id);

	List<T> findAll();

	List<?> findByJPQL(String jpql, Object... parans);

	Object findSingleResultByJPQL(String jpql, Object... parans);

	int count();
}
