package com.msplearning.repository;

import java.io.Serializable;

import com.msplearning.entity.Teacher;

/**
 * Interface of {@link TeacherRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface TeacherRepository {
	
	void insert(Teacher entity);

	void update(Teacher entity);
	
	void delete(Serializable id);
	
	Teacher findById(Serializable id);
}
