package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class AppFeatureId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@ForeignKey(name="fk_tb_app_feature_2_tb_app")
	private App app;

	@ManyToOne
	@ForeignKey(name="fk_tb_app_feature_2_tb_feature")
	private Feature feature;

	public App getApp() {
		return this.app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public Feature getFeature() {
		return this.feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.app == null) ? 0 : this.app.hashCode());
		result = (prime * result) + ((this.feature == null) ? 0 : this.feature.hashCode());
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
		AppFeatureId other = (AppFeatureId) obj;
		if (this.app == null) {
			if (other.app != null) {
				return false;
			}
		} else if (!this.app.equals(other.app)) {
			return false;
		}
		if (this.feature == null) {
			if (other.feature != null) {
				return false;
			}
		} else if (!this.feature.equals(other.feature)) {
			return false;
		}
		return true;
	}

}
