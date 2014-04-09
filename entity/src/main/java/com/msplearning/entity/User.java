package com.msplearning.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.msplearning.entity.crypto.Base64Type;

/**
 * The User class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_user", uniqueConstraints = { @UniqueConstraint(name = "uk_tb_user_username", columnNames = { "username" }) })
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "sequenceUser", sequenceName = "sq_tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequenceUser")
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 1, nullable = false)
	private Gender gender;

	@Column(name = "date_birth")
	@Temporal(TemporalType.DATE)
	private Date dateBirth;

	@Column(name = "username", length = 20, nullable = false)
	private String username;

	@Column(name = "password", length = 30, nullable = false)
	@Type(type = Base64Type.TYPE)
	private String password;

	@Column(name = "date_registration", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRegistration;

	@Column(name = "date_last_login", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastLogin;

	@Transient
	private boolean isTeacher;

	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateBirth() {
		return this.dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateRegistration() {
		return this.dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Date getDateLastLogin() {
		return this.dateLastLogin;
	}

	public void setDateLastLogin(Date dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}

	public boolean isTeacher() {
		return this instanceof Teacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
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
		User other = (User) obj;
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
