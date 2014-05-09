package com.msplearning.repository.jpa.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.msplearning.repository.generic.GenericRepository;

/**
 * The GenericDaoImpl class provides of the basic operations and also the fundamental objects of persistence.
 * 
 * @author Renan Johannsen de Paula (renanjp)
 * 
 * @param <T>
 *            entity class
 */
public class GenericRepositoryJpa<T extends Serializable, K extends Serializable> implements GenericRepository<T, K> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericRepositoryJpa() {
		Type[] typeArguments = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
		this.persistentClass = (Class<T>) typeArguments[0];
	}

	public GenericRepositoryJpa(EntityManager entityManager) {
		this();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void insert(T entity) {
		this.getEntityManager().persist(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		this.getEntityManager().merge(entity);
	}

	@Override
	@Transactional
	public void delete(K id) {
		this.getEntityManager().remove(this.getEntityManager().getReference(this.getPersistentClass(), id));
	}

	@Override
	public T findById(K id) {
		return this.getEntityManager().find(this.getPersistentClass(), id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) this.findByJPQL("FROM " + this.getPersistentClass().getSimpleName());
	}

	@Override
	public List<?> findByJPQL(String jpql, Object... params) {
		return this.createQuery(jpql, params).getResultList();
	}

	@Override
	public List<?> findByJPQL(String jpql, Map<String, Object> params) {
		return this.createQueryWithNamedParameter(jpql, params).getResultList();
	}
	
	@Override
	public Object findSingleResultByJPQL(String jpql, Object... params) {
		return this.createQuery(jpql, params).getSingleResult();
	}
	
	@Override
	public Object findSingleResultByJPQL(String jpql, Map<String, Object> params) {
		return this.createQueryWithNamedParameter(jpql, params).getSingleResult();
	}

	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null) {
			throw new IllegalStateException("PersistentClass has not been set on DAO before usage");
		}
		return this.persistentClass;
	}

	protected void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null) {
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}
		return this.entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public int count() {
		String jpql = "SELECT COUNT(*) FROM " + this.getPersistentClass().getSimpleName();
		return Integer.parseInt(this.createQuery(jpql).getSingleResult().toString());
	}

	private Query createQuery(String jpql, Object... params) {
		Query query = this.getEntityManager().createQuery(jpql);
		int i = 0;
		for (Object object : params) {
			query.setParameter(++i, object);
		}
		return query;
	}

	private Query createQueryWithNamedParameter(String jpql, Map<String, Object> params) {
		Query query = this.getEntityManager().createQuery(jpql);
		Object[] keys = params.keySet().toArray();
		for (Object key : keys) {
			query.setParameter(key.toString(), params.get(key));
		}
		return query;
	}
}
