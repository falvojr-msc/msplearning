package com.msplearning.repository.jpa;

import org.springframework.stereotype.Repository;

import com.msplearning.entity.App;
import com.msplearning.entity.Student;
import com.msplearning.repository.AppRepository;
import com.msplearning.repository.jpa.generic.GenericRepositoryJpa;

/**
 * The StudentRepositoryJpa class provides the persistence operations of entity
 * {@link Student}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Repository("appRepository")
public class AppRepositoryJpa extends GenericRepositoryJpa<App, Long> implements AppRepository {

}
