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
	@ForeignKey(name="fk_tb_address_2_tb_city")
	private City city;

	@Column(name = "address", length = 50, nullable = false)
	private String address;

	@Column(name = "address_alternative", length = 50)
	private String addressAlternative;

	@Column(name = "zip_code", length = 20, nullable = false)
	private String zipCode;
}
