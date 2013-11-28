package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Phone class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_phone")
@SequenceGenerator(name = "sequencePhone", sequenceName = "sq_tb_phone")
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequencePhone")
	@Column(name = "id")
	private Long id;

	@Column(name = "number", length = 20, nullable = false)
	private String number;

	@Column(name = "main", nullable = false)
	private boolean main;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
}
