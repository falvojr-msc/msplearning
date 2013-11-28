package com.msplearning.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * The Teacher class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_teacher")
@PrimaryKeyJoinColumn(name = "id_user", referencedColumnName = "id")
@ForeignKey(name = "fk_tb_teacher_2_tb_user")
public class Teacher extends User {

	private static final long serialVersionUID = 1L;

}
