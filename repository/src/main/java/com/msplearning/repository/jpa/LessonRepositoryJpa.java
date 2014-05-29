package com.msplearning.repository.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Lesson;
import com.msplearning.repository.LessonRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The DisciplineRepositoryJpa class provides the persistence operations of entity {@link Lesson}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("lessonRepository")
public class LessonRepositoryJpa extends GenericRepositoryJpa<Lesson, Long> implements LessonRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> findByDiscipline(Long idDiscipline) {
		String jpql = "FROM Lesson WHERE idDiscipline = :idDiscipline";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idDiscipline", idDiscipline);
		return (List<Lesson>) this.findByJPQL(jpql, params);
	}

}
