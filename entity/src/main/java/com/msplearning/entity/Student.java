package com.msplearning.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * The Student class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_student")
@PrimaryKeyJoinColumn(name = "id_user", referencedColumnName = "id")
@ForeignKey(name = "fk_tb_student_2_tb_user")
public class Student extends User {

	private static final long serialVersionUID = 1L;

}
