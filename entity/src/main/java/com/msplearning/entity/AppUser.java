package com.msplearning.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The UserApp class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_app_user")
@AssociationOverrides({
	@AssociationOverride(name = "id.app", joinColumns = @JoinColumn(name = "id_app", nullable = false)),
	@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "id_user", nullable = false)) })
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppUserId id;

	@Column(name = "date_request", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateRequest;

	@Column(name = "active", nullable = false)
	private boolean isActive;

	@Column(name = "admin", nullable = false)
	private boolean isAdmin;

	public AppUserId getId() {
		return this.id;
	}

	public void setId(AppUserId id) {
		this.id = id;
	}

	public Date getDateRequest() {
		return this.dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
		AppUser other = (AppUser) obj;
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
