package com.msplearning.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Student;
import com.msplearning.repository.StudentRepository;
import com.msplearning.service.StudentService;

/**
 * The StudentServiceImpl class provides the business operations of entity {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("studentServiceJpa")
public class StudentServiceJpa implements StudentService {

	@Autowired
	private StudentRepository studentRepositoryJpa;
	
	@Override
	public void insert(Student entity) {
		this.studentRepositoryJpa.insert(entity);
	}

	@Override
	public void update(Student entity) {
		this.studentRepositoryJpa.update(entity);
	}

}
