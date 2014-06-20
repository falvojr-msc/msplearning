package com.msplearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Slide;
import com.msplearning.repository.SlideRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The SlideService class provides the business operations of entity {@link Slide}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("slideService")
public class SlideService extends GenericCrudService<Slide, Long> {

	@Autowired
	private SlideRepository slideRepository;

	@Override
	protected GenericRepository<Slide, Long> getRepository() {
		return this.slideRepository;
	}

	public List<Slide> findByLesson(Long idLesson) {
		return this.slideRepository.findByLesson(idLesson);
	}
}
