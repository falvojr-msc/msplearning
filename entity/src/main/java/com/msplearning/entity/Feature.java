package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "id_parent")
	@ForeignKey(name = "fk_tb_feature_2_tb_feature")
	private Feature parent;

	public Feature() {
		super();
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

	public Feature(Long id, String name, FeatureOperator operator, boolean isMandatory, boolean isAbstract, boolean isHidden, Feature parent) {
		this(id, name, operator, isMandatory, isAbstract, isHidden);
		this.parent = parent;
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

	public Feature getParent() {
		return this.parent;
	}

	public void setParent(Feature parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + (this.isAbstract ? 1231 : 1237);
		result = (prime * result) + (this.isHidden ? 1231 : 1237);
		result = (prime * result) + (this.isMandatory ? 1231 : 1237);
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result) + ((this.operator == null) ? 0 : this.operator.hashCode());
		result = (prime * result) + ((this.parent == null) ? 0 : this.parent.hashCode());
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
		if (this.isAbstract != other.isAbstract) {
			return false;
		}
		if (this.isHidden != other.isHidden) {
			return false;
		}
		if (this.isMandatory != other.isMandatory) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.operator != other.operator) {
			return false;
		}
		if (this.parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!this.parent.equals(other.parent)) {
			return false;
		}
		return true;
	}

}
