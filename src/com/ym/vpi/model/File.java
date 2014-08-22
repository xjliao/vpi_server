package com.ym.vpi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class File {
	private Long id;
	private String filePath;
	private Long fileLength;
	private Article article;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileLength() {
		return fileLength;
	}

	public void setFileLength(Long fileLength) {
		this.fileLength = fileLength;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "f_article_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
