package com.msplearning.entity;

public enum Variability {

	INTERACTIVITY(7L),
	AUTHENTICITY(16L),
	AUDIO(19L),
	IMAGE(20L),
	TEXT(21L),
	VIDEO(22L);

	private Long id;

	private Variability(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

}
