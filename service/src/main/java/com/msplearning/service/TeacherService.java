package com.msplearning.service;

import com.msplearning.entity.Teacher;
import com.msplearning.service.jpa.TeacherServiceJpa;

/**
 * Interface of {@link TeacherServiceJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface TeacherService {

	void insert(Teacher entity);

	void update(Teacher entity);

	void delete(Long id);
	
	Teacher findById(Long id);
}
