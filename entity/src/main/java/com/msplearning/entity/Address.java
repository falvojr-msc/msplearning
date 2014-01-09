package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * The Address class.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_address")
@SequenceGenerator(name = "sequenceAddress", sequenceName = "sq_tb_address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequenceAddress")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_city", nullable = false)
	@ForeignKey(name = "fk_tb_address_2_tb_city")
	private City city;

	@Column(name = "address", length = 50, nullable = false)
	private String address;

	@Column(name = "address_alternative", length = 50)
	private String addressAlternative;

	@Column(name = "zip_code", length = 20, nullable = false)
	private String zipCode;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressAlternative() {
		return this.addressAlternative;
	}

	public void setAddressAlternative(String addressAlternative) {
		this.addressAlternative = addressAlternative;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.address == null) ? 0 : this.address.hashCode());
		result = (prime * result) + ((this.addressAlternative == null) ? 0 : this.addressAlternative.hashCode());
		result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.zipCode == null) ? 0 : this.zipCode.hashCode());
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
		Address other = (Address) obj;
		if (this.address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!this.address.equals(other.address)) {
			return false;
		}
		if (this.addressAlternative == null) {
			if (other.addressAlternative != null) {
				return false;
			}
		} else if (!this.addressAlternative.equals(other.addressAlternative)) {
			return false;
		}
		if (this.city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!this.city.equals(other.city)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.zipCode == null) {
			if (other.zipCode != null) {
				return false;
			}
		} else if (!this.zipCode.equals(other.zipCode)) {
			return false;
		}
		return true;
	}

}
