package com.msplearning.repository.jpa;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Student;
import com.msplearning.repository.StudentRepository;

/**
 * The StudentRepositoryJpa class provides the persistence operations of entity {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("studentDaoJpa")
public class StudentRepositoryJpa extends GenericRepositoryJpa<Student> implements StudentRepository {

}
