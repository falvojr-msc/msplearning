package com.msplearning.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Lesson class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_lesson")
@SequenceGenerator(name = "sequenceLesson", sequenceName = "sq_tb_lesson")
public class Lesson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequenceLesson")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "id_discipline", nullable = false)
	private Long idDiscipline;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "lesson", cascade = CascadeType.ALL)
	private Set<Slide> slides;

	public Lesson() {
		super();
	}

	public Lesson(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdDiscipline() {
		return this.idDiscipline;
	}

	public void setIdDiscipline(Long idDiscipline) {
		this.idDiscipline = idDiscipline;
	}

	public Set<Slide> getSlides() {
		return this.slides;
	}

	public void setSlides(Set<Slide> slides) {
		this.slides = slides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
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
		Lesson other = (Lesson) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}