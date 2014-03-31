package com.msplearning.service;

import org.springframework.beans.factory.InitializingBean;
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
public class DisciplineService extends GenericCrudService<Discipline, Long> implements InitializingBean {

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Override
	protected GenericRepository<Discipline, Long> getRepository() {
		return this.disciplineRepository;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.createDisciplineMock();
	}

	private void createDisciplineMock() {
		for (int i = 1; i < 21; i++) {
			Discipline discipline = new Discipline();
			discipline.setName("Disciline " + i);
			String description = "";
			for (int j = 0; j < 35; j++) {
				description += " Lorem Ipsum " + j;
			}
			discipline.setDescription(description.trim());
			this.insert(discipline);
		}
	}
}
