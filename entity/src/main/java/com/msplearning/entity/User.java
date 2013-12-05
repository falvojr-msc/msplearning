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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import com.msplearning.entity.crypto.Base64Type;
import com.msplearning.entity.json.adapter.DateAdapter;

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

	@Column(name = "date_birth", nullable = false)
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
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dateRegistration;

	@Column(name = "date_last_login", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastLogin;

	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_address_2_tb_user")
	protected List<Address> addresses;
	
	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_email_2_tb_user")
	protected List<Email> emails;
	
	@OneToMany
	@JoinColumn(name = "id_user", nullable = false)
	@ForeignKey(name="fk_tb_phone_2_tb_user")
	protected List<Phone> phones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Date dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public Date getDateLastLogin() {
		return dateLastLogin;
	}

	public void setDateLastLogin(Date dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}
