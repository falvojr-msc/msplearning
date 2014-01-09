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
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isMain() {
		return this.main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + (this.main ? 1231 : 1237);
		result = (prime * result) + ((this.number == null) ? 0 : this.number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Phone other = (Phone) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.main != other.main) {
			return false;
		}
		if (this.number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!this.number.equals(other.number)) {
			return false;
		}
		return true;
	}

}
