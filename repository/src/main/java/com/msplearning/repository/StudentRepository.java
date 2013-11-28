package com.msplearning.repository;

import com.msplearning.entity.Student;
import com.msplearning.repository.jpa.StudentRepositoryJpa;

/**
 * Interface of {@link StudentRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface StudentRepository {
	
	void insert(Student entity);

	void update(Student entity);
}
