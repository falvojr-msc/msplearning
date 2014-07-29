package com.msplearning.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.msplearning.entity.enuns.MediaType;

/**
 * The EducationalContent class.
 *
 * @author Venilton Falvo Junior (veniltonjr)
 */
@Entity
@Table(name = "tb_educational_content")
@SequenceGenerator(name = "sequenceEducationalContent", sequenceName = "sq_tb_educational_content")
public class EducationalContent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequenceEducationalContent")
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type", length = 1, nullable = false)
	private MediaType mediaType;

	@Column(name = "title", length = 50, nullable = false)
	private String title;

	@Column(name = "content", length = 500, nullable = false)
	private String content;

	@Column(name = "footnote", length = 50)
	private String footnote;

	@Column(name = "page", nullable = false)
	private Long page;

	@Column(name = "id_lesson", nullable = false)
	private Long idLesson;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFootnote() {
		return this.footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public Long getPage() {
		return this.page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getIdLesson() {
		return this.idLesson;
	}

	public void setIdLesson(Long idLesson) {
		this.idLesson = idLesson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
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
		EducationalContent other = (EducationalContent) obj;
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