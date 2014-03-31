package com.msplearning.repository.jpa;

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

}
