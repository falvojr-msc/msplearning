package com.msplearning.repository;

import com.msplearning.entity.App;
import com.msplearning.repository.generic.GenericRepository;
import com.msplearning.repository.jpa.AppRepositoryJpa;

/**
 * Interface of {@link AppRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface AppRepository extends GenericRepository<App, Long> {

}
