package com.msplearning.repository;

import java.util.List;

import com.msplearning.entity.EducationalContent;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.EducationalContentRepositoryJpa;

/**
 * Interface of {@link EducationalContentRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface EducationalContentRepository extends GenericRepository<EducationalContent, Long> {

	List<EducationalContent> findByLesson(Long idLesson);

}
