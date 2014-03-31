package com.msplearning.service;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Gender;
import com.msplearning.entity.Student;
import com.msplearning.repository.StudentRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The StudentService class provides the business operations of entity {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("studentService")
public class StudentService extends GenericCrudService<Student, Long> implements InitializingBean {

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

	@Override
	public void afterPropertiesSet() throws Exception {
		this.createStudentMock();
	}

	private void createStudentMock() {
		for (int i = 1; i < 6; i++) {
			Student student = new Student();
			student.setFirstName("Student" + i);
			student.setLastName("LastName" + i);
			student.setGender(Math.random() > 0.5 ? Gender.M : Gender.F);
			student.setUsername("student" + i);
			student.setPassword("123456");
			this.insert(student);
		}
	}
}
