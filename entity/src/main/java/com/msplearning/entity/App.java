package com.msplearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_app")
@SequenceGenerator(name = "sequenceApp", sequenceName = "sq_tb_app")
public class App {

	@Id
	@GeneratedValue(generator = "sequencePhone")
	@Column(name = "id")
	private Long id;
}
