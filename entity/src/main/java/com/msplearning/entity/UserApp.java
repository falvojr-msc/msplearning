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

@Entity
@Table(name = "tb_app_user", catalog = "msplearning")
@AssociationOverrides({ 
	@AssociationOverride(name = "id.app", joinColumns = @JoinColumn(name = "id_app")),
	@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "id_user")) })
public class UserApp implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserAppId id;

	@Column(name = "date_request", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateRequest;

	@Column(name = "active", nullable = false)
	private boolean active;

	public UserAppId getId() {
		return id;
	}

	public void setId(UserAppId id) {
		this.id = id;
	}

	public Date getDateRequest() {
		return dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserApp other = (UserApp) obj;
		if (id == null ? other.id != null : !id.equals(other.id))
			return false;
		return true;
	}
}
