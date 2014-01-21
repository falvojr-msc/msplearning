package com.msplearning.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Student;
import com.msplearning.entity.Teacher;
import com.msplearning.repository.TeacherRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The TeacherServiceJpa class provides the business operations of entity
 * {@link Teacher}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("teacherService")
public class TeacherService extends GenericCrudService<Teacher, Long> {

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	protected GenericRepository<Teacher, Long> getRepository() {
		return this.teacherRepository;
	}

	@Override
	public void insert(Teacher entity) {
		entity.setDateRegistration(new Date());
		entity.setDateLastLogin(new Date());

		super.insert(entity);
	}
}
