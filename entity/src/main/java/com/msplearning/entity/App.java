package com.msplearning.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The App class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_app")
@SequenceGenerator(name = "sequenceApp", sequenceName = "sq_tb_app")
public class App {

	@Id
	@GeneratedValue(generator = "sequenceApp")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "date_creation", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.app", cascade=CascadeType.ALL)
	private List<AppFeature> appFeatures;

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

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<AppFeature> getAppFeatures() {
		return this.appFeatures;
	}

	public void setAppFeatures(List<AppFeature> appFeatures) {
		this.appFeatures = appFeatures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.appFeatures == null ? 0 : this.appFeatures.hashCode());
		result = prime * result + (this.dateCreation == null ? 0 : this.dateCreation.hashCode());
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		result = prime * result + (this.name == null ? 0 : this.name.hashCode());
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
		App other = (App) obj;
		if (this.appFeatures == null) {
			if (other.appFeatures != null) {
				return false;
			}
		} else if (!this.appFeatures.equals(other.appFeatures)) {
			return false;
		}
		if (this.dateCreation == null) {
			if (other.dateCreation != null) {
				return false;
			}
		} else if (!this.dateCreation.equals(other.dateCreation)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

}