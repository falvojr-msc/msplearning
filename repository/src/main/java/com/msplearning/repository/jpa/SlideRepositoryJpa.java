package com.msplearning.repository.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Slide;
import com.msplearning.repository.SlideRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The SlideRepositoryJpa class provides the persistence operations of entity {@link Slide}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("slideRepository")
public class SlideRepositoryJpa extends GenericRepositoryJpa<Slide, Long> implements SlideRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Slide> findByLesson(Long idLesson) {
		String jpql = "FROM Slide WHERE lesson.id = :idLesson";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idLesson", idLesson);
		return (List<Slide>) this.findByJPQL(jpql, params);
	}

}
