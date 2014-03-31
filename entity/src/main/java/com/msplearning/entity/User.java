package com.msplearning.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
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

	@Transient
	@Column(name = "date_birth")
	@Temporal(TemporalType.DATE)
	private Date dateBirth;

	@Column(name = "username", length = 20, nullable = false)
	private String username;

	/**
	 * The length 28 has calculated for one field with size 20 (Base 64).
	 */
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
	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_address_2_tb_user")
	private List<Address> addresses;

	@Transient
	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_email_2_tb_user")
	private List<Email> emails;

	@Transient
	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_phone_2_tb_user")
	private List<Phone> phones;

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

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (this.addresses == null ? 0 : this.addresses.hashCode());
		result = (prime * result) + (this.dateBirth == null ? 0 : this.dateBirth.hashCode());
		result = (prime * result) + (this.dateLastLogin == null ? 0 : this.dateLastLogin.hashCode());
		result = (prime * result) + (this.dateRegistration == null ? 0 : this.dateRegistration.hashCode());
		result = (prime * result) + (this.emails == null ? 0 : this.emails.hashCode());
		result = (prime * result) + (this.firstName == null ? 0 : this.firstName.hashCode());
		result = (prime * result) + (this.gender == null ? 0 : this.gender.hashCode());
		result = (prime * result) + (this.id == null ? 0 : this.id.hashCode());
		result = (prime * result) + (this.lastName == null ? 0 : this.lastName.hashCode());
		result = (prime * result) + (this.password == null ? 0 : this.password.hashCode());
		result = (prime * result) + (this.phones == null ? 0 : this.phones.hashCode());
		result = (prime * result) + (this.username == null ? 0 : this.username.hashCode());
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
		if (this.addresses == null) {
			if (other.addresses != null) {
				return false;
			}
		} else if (!this.addresses.equals(other.addresses)) {
			return false;
		}
		if (this.dateBirth == null) {
			if (other.dateBirth != null) {
				return false;
			}
		} else if (!this.dateBirth.equals(other.dateBirth)) {
			return false;
		}
		if (this.dateLastLogin == null) {
			if (other.dateLastLogin != null) {
				return false;
			}
		} else if (!this.dateLastLogin.equals(other.dateLastLogin)) {
			return false;
		}
		if (this.dateRegistration == null) {
			if (other.dateRegistration != null) {
				return false;
			}
		} else if (!this.dateRegistration.equals(other.dateRegistration)) {
			return false;
		}
		if (this.emails == null) {
			if (other.emails != null) {
				return false;
			}
		} else if (!this.emails.equals(other.emails)) {
			return false;
		}
		if (this.firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!this.firstName.equals(other.firstName)) {
			return false;
		}
		if (this.gender != other.gender) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!this.lastName.equals(other.lastName)) {
			return false;
		}
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.phones == null) {
			if (other.phones != null) {
				return false;
			}
		} else if (!this.phones.equals(other.phones)) {
			return false;
		}
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
