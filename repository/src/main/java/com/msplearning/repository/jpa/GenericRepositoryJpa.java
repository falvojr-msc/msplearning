package com.msplearning.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.msplearning.repository.GenericRepository;

/**
 * The GenericDaoImpl class provides of the basic operations and also the fundamental objects of persistence.
 * 
 * @author Renan Johannsen de Paula (renanjp)
 * 
 * @param <T> entity class
 */
public class GenericRepositoryJpa<T extends Serializable> implements GenericRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericRepositoryJpa() {
		Type[] typeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		this.persistentClass = (Class<T>) typeArguments[0];
	}

	public GenericRepositoryJpa(EntityManager entityManager) {
		this();
		this.entityManager = entityManager;
	}

	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null)
			throw new IllegalStateException("PersistentClass has not been set on DAO before usage");
		return this.persistentClass;
	}

	protected void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null)
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		return this.entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Query createQuery(String JPQL, Object... parans) {
		Query query = this.getEntityManager().createQuery(JPQL);
		int i = 0;
		for (Object object : parans) {
			query.setParameter(++i, object);
		}
		return query;
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
	public void delete(Serializable id) {
		this.getEntityManager().remove(this.getEntityManager().getReference(getPersistentClass(), id));
	}

	@Override
	public T findById(Serializable id) {
		return this.getEntityManager().find(getPersistentClass(), id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) this.findByJPQL("FROM " + this.getPersistentClass().getSimpleName());
	}

	@Override
	public List<?> findByJPQL(String jpql, Object... parans) {
		Query query = this.createQuery(jpql, parans);
		return query.getResultList();
	}

	@Override
	public Object findSingleResultByJPQL(String jpql, Object... parans) {
		return this.createQuery(jpql, parans).getSingleResult();
	}

	@Override
	public int count() {
		String jpql = new String("SELECT COUNT(*) FROM " + this.getPersistentClass().getSimpleName());
		return Integer.parseInt(this.createQuery(jpql).getSingleResult().toString());
	}

}
