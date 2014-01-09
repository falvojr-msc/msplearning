package com.msplearning.repository.jpa;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Teacher;
import com.msplearning.repository.TeacherRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The TeacherRepositoryJpa class provides the persistence operations of entity
 * {@link Teacher}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("teacherDaoJpa")
public class TeacherRepositoryJpa extends GenericRepositoryJpa<Teacher, Long> implements TeacherRepository {

}
