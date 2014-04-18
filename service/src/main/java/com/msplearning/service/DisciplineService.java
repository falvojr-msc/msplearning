package com.msplearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msplearning.entity.Discipline;
import com.msplearning.repository.DisciplineRepository;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.service.generic.GenericCrudService;

/**
 * The DisciplineService class provides the business operations of entity {@link Discipline}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Service("disciplineService")
public class DisciplineService extends GenericCrudService<Discipline, Long> {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	protected GenericRepository<Discipline, Long> getRepository() {
		return this.disciplineRepository;
	}
}
