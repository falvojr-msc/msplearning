package com.msplearning.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Student;
import com.msplearning.repository.StudentRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The StudentServiceJpa class provides the business operations of entity
 * {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("studentService")
public class StudentService extends GenericCrudService<Student, Long> {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	protected GenericRepository<Student, Long> getRepository() {
		return this.studentRepository;
	}

	@Override
	public void insert(Student entity) {
		entity.setDateRegistration(new Date());
		entity.setDateLastLogin(new Date());

		super.insert(entity);
	}
}
