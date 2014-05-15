package com.msplearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Student class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_student")
@PrimaryKeyJoinColumn(name = "id_user", referencedColumnName = "id")
public class Student extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "academic_identifier", length = 50)
	private String academicIdentifier;

	public String getAcademicIdentifier() {
		return this.academicIdentifier;
	}

	public void setAcademicIdentifier(String academicIdentifier) {
		this.academicIdentifier = academicIdentifier;
	}

}
