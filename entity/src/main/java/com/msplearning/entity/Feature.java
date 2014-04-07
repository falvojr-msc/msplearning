package com.msplearning.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tb_feature")
public class Feature implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "operator", length = 1)
	private FeatureOperator operator;

	@Column(name = "mandatory", nullable = false)
	private boolean isMandatory;

	@Column(name = "abstract", nullable = false)
	private boolean isAbstract;

	@Column(name = "hidden", nullable = false)
	private boolean isHidden;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_parent")
	@ForeignKey(name = "fk_tb_feature_2_tb_feature")
	private Set<Feature> children;

	public Feature() {
		super();
	}

	public Feature(Long id) {
		super();
		this.id = id;
	}

	public Feature(Long id, String name, FeatureOperator operator, boolean isMandatory, boolean isAbstract, boolean isHidden) {
		super();
		this.id = id;
		this.name = name;
		this.operator = operator;
		this.isMandatory = isMandatory;
		this.isAbstract = isAbstract;
		this.isHidden = isHidden;
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

	public FeatureOperator getOperator() {
		return this.operator;
	}

	public void setOperator(FeatureOperator operator) {
		this.operator = operator;
	}

	public boolean isMandatory() {
		return this.isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean isAbstract() {
		return this.isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isHidden() {
		return this.isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Set<Feature> getChildren() {
		return this.children;
	}

	public void setChildren(Set<Feature> children) {
		this.children = children;
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
		Feature other = (Feature) obj;
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
