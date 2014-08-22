package com.ym.vpi.service.impl;

import java.util.Date;
import java.util.List;

import com.ym.vpi.Pagination;
import com.ym.vpi.dao.ArticleDao;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;
import com.ym.vpi.service.ArticleManager;

public class ArticleManagerImpl implements ArticleManager {
	private ArticleDao articleDao;

	@Override
	public List<Article> allArticles() {
		// TODO Auto-generated method stub
		return articleDao.getAllArticles();
	}

	@Override
	public List<Article> myArticles(User user) {
		// TODO Auto-generated method stub
		return articleDao.getMyArticles(user);
	}

	@Override
	public List<Article> articlesByUsername(String username) {
		// TODO Auto-generated method stub
		return articleDao.getArticlesByUsername(username);
	}

	@Override
	public boolean addArticle(Article article) {
		// 新增文章
		boolean isSave = articleDao.save(article);

		if (isSave) {
			return true;
		}

		return false;
	}

	@Override
	public Article articlesById(Long id) {
		// TODO Auto-generated method stub
		return articleDao.getArticleById(id);
	}

	@Override
	public void like(Article article) {
		// TODO Auto-generated method stub
		articleDao.update(article);

	}

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public Pagination getPaginationArticle(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return articleDao.getPaginationArticle(currentPage, pageSize);
	}
	
	//下拉刷新
	public Pagination getUpdatedPaginationArticle(int updateCount, Date lastUpdatedDate) {
		return articleDao.getUpdatedPaginationArticle(updateCount, lastUpdatedDate);
	}
	
	//下拉刷新
	public Pagination getUpdatedPaginationArticle(int updateCount, String lastUpdatedDate) {
		return articleDao.getUpdatedPaginationArticle(updateCount, lastUpdatedDate);
	}

//	@Override
//	public List<Article> getPaginationArticle(final int currentPage, final int pageSize) {
//		// TODO Auto-generated method stub
//		return articleDao.getPaginationArticle(currentPage, pageSize);
//	}
	
	

}
