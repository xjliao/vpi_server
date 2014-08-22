package com.ym.vpi.webapp.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ym.vpi.model.User;
import com.ym.vpi.service.ArticleManager;
import com.ym.vpi.service.UserManager;

public class AppBaseAction extends ActionSupport {
	private ArticleManager articleManager;
	private UserManager userManager;

	public ArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
