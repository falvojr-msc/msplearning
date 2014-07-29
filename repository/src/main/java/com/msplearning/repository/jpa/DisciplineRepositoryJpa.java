package com.msplearning.repository.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.Discipline;
import com.msplearning.repository.DisciplineRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The DisciplineRepositoryJpa class provides the persistence operations of entity
 * {@link Discipline}.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("disciplineRepository")
public class DisciplineRepositoryJpa extends GenericRepositoryJpa<Discipline, Long> implements DisciplineRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<Discipline> findByApp(Long idApp) {
		String jpql = "FROM Discipline WHERE idApp = :idApp";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idApp", idApp);
		return (List<Discipline>) this.findByJPQL(jpql, params);
	}

}
