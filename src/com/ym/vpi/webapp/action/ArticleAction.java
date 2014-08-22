package com.ym.vpi.webapp.action;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;

@ParentPackage(value = "default")
@Namespace(value = "")
@Results({
		@Result(name = "add", location = "/article.jsp"),
		@Result(name = "success", location = "index.action", type = "redirect") })
				
public class ArticleAction extends AppBaseAction {
	private Article article;
	private String title;
	private String content;
	private File[] files;
	private User user;
	private String msg;
	private String articleId;
	private String fileNames;
	private String fileType;
	private File file;
	
	public String execute() {
		return "success";
	}

	public String addArticle() {
		if (null != title && !"".equals(title) && null != content
				&& !"".equals(content)) {
			user = (User) ServletActionContext.getContext().getSession()
					.get("user");
			article = new Article();
			article.setTitle(title);
			article.setContent(content);
			article.setUser(user);
			article.setCreateTime(new Date());

			this.getArticleManager().addArticle(article);
			
			return "success";
		}

		return "add";
	}

	public String likeArticle() {
		user = (User) ServletActionContext.getContext().getSession()
				.get("user");
		article = this.getArticleManager().articlesById(Long.valueOf(articleId));
		article.setLikeCount(199);
		this.getArticleManager().like(article);

		return "success";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
