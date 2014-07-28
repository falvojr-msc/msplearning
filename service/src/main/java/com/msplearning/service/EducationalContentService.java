package com.msplearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.EducationalContent;
import com.msplearning.repository.EducationalContentRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The EducationalContentService class provides the business operations of entity {@link EducationalContent}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("educationalContentService")
public class EducationalContentService extends GenericCrudService<EducationalContent, Long> {

	@Autowired
	private EducationalContentRepository educationalContentRepository;

	@Override
	protected GenericRepository<EducationalContent, Long> getRepository() {
		return this.educationalContentRepository;
	}

	public List<EducationalContent> findByLesson(Long idLesson) {
		return this.educationalContentRepository.findByLesson(idLesson);
	}
}
