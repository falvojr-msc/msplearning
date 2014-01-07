package com.msplearning.repository;

import com.msplearning.entity.Teacher;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.TeacherRepositoryJpa;

/**
 * Interface of {@link TeacherRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface TeacherRepository extends GenericRepository<Teacher, Long> {

}
