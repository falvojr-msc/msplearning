package com.msplearning.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Teacher;
import com.msplearning.repository.TeacherRepository;
import com.msplearning.service.TeacherService;

/**
 * The TeacherServiceJpa class provides the business operations of entity {@link Teacher}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("teacherServiceJpa")
public class TeacherServiceJpa implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepositoryJpa;
	
	@Override
	public void insert(Teacher entity) {
		this.teacherRepositoryJpa.insert(entity);
	}

	@Override
	public void update(Teacher entity) {
		this.teacherRepositoryJpa.update(entity);
	}

	@Override
	public void delete(Long id) {
		this.teacherRepositoryJpa.delete(id);
	}
	
	@Override
	public Teacher findById(Long id) {
		return this.teacherRepositoryJpa.findById(id);
	}
}
