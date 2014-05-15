package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_app_feature")
@AssociationOverrides({
	@AssociationOverride(name = "id.app", joinColumns = @JoinColumn(name = "id_app", nullable = false)),
	@AssociationOverride(name = "id.feature", joinColumns = @JoinColumn(name = "id_feature", nullable = false)) })
public class AppFeature implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppFeatureId id;

	@Column(name = "active", nullable = false)
	private boolean isActive;

	public AppFeatureId getId() {
		return this.id;
	}

	public void setId(AppFeatureId id) {
		this.id = id;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
		AppFeature other = (AppFeature) obj;
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
