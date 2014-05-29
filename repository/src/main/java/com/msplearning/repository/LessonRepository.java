package com.msplearning.repository;

import java.util.List;

import com.msplearning.entity.Lesson;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.LessonRepositoryJpa;

/**
 * Interface of {@link LessonRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface LessonRepository extends GenericRepository<Lesson, Long> {

	List<Lesson> findByDiscipline(Long idDiscipline);

}
