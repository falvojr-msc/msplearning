package com.msplearning.repository;

import java.util.List;

import com.msplearning.entity.Slide;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.SlideRepositoryJpa;

/**
 * Interface of {@link SlideRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface SlideRepository extends GenericRepository<Slide, Long> {

	List<Slide> findByLesson(Long idLesson);

}
