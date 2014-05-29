package com.msplearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Lesson;
import com.msplearning.repository.LessonRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The LessonService class provides the business operations of entity {@link Lesson}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("lessonService")
public class LessonService extends GenericCrudService<Lesson, Long> {

	@Autowired
	private LessonRepository lessonRepository;

	@Override
	protected GenericRepository<Lesson, Long> getRepository() {
		return this.lessonRepository;
	}

	public List<Lesson> findByDiscipline(Long idDiscipline) {
		return this.lessonRepository.findByDiscipline(idDiscipline);
	}
}
