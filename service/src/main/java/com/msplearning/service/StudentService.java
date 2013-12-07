package com.msplearning.service;

import com.msplearning.entity.Student;
import com.msplearning.service.jpa.StudentServiceJpa;

/**
 * Interface of {@link StudentServiceJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface StudentService {

	void insert(Student entity);

	void update(Student entity);

	void delete(Long id);
	
	Student findById(Long id);
}
