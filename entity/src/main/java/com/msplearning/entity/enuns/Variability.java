package com.msplearning.entity.enuns;

public enum Variability {

	INTERACTIVITY(7L),
	TEXT(19L),
	IMAGE(20L),
	VIDEO(21L);

	private Long id;

	private Variability(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

}
