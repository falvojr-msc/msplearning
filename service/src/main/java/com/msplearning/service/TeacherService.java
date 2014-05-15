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
 * The TeacherService class provides the business operations of entity {@link Teacher}.
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
		if (this.teacherRepository.findById(1L) == null) {
			this.createTeacherMock();
		}
	}

	private void createTeacherMock() {
		Teacher teacher = new Teacher();
		teacher.setFirstName("Venilton");
		teacher.setLastName("Falvo Junior");
		teacher.setGender(Gender.M);
		teacher.setEmail("venilton.falvo@gmail.com");
		teacher.setPassword("admin");
		this.insert(teacher);
	}
}
