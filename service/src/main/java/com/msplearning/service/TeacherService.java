package com.msplearning.service;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Gender;
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
public class TeacherService extends GenericCrudService<Teacher, Long> implements InitializingBean {

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

	@Override
	public void afterPropertiesSet() throws Exception {
		this.createTeacherMock();
	}

	private void createTeacherMock() {
		for (int i = 1; i < 6; i++) {
			Teacher teacher = new Teacher();
			teacher.setFirstName("Teacher" + i);
			teacher.setLastName("LastName" + i);
			teacher.setGender(Math.random() > 0.5 ? Gender.M : Gender.F);
			teacher.setUsername("teacher" + i);
			teacher.setPassword("123456");
			this.insert(teacher);
		}
	}
}
