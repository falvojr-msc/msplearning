package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * The UserAppId class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Embeddable
public class AppUserId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private App app;

	@ManyToOne
	private User user;

	public AppUserId() {
		super();
	}

	public AppUserId(Long idApp, Long idUser) {
		super();
		this.app = new App(idApp);
		this.user = new User(idUser);
	}

	public App getApp() {
		return this.app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.app == null) ? 0 : this.app.hashCode());
		result = (prime * result) + ((this.user == null) ? 0 : this.user.hashCode());
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
		AppUserId other = (AppUserId) obj;
		if (this.app == null) {
			if (other.app != null) {
				return false;
			}
		} else if (!this.app.equals(other.app)) {
			return false;
		}
		if (this.user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!this.user.equals(other.user)) {
			return false;
		}
		return true;
	}

}
