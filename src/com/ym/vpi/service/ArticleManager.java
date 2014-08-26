package com.ym.vpi.service;

import java.util.Date;
import java.util.List;

import com.ym.vpi.Pagination;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;

public interface ArticleManager {
	// 所有文章
	public List<Article> allArticles();

	// 我发表的文章
	public List<Article> myArticles(User user);

	public List<Article> articlesByUsername(String username);

	// 分页文章
	//public List<Article> getPaginationArticle(final int currentPage, final int pageSize);
	public Pagination getPaginationArticle(int currentPage, int pageSize);
	//我发表的文章
	public Pagination getPaginationMyArticle(int currentPage, final int pageSize, String username);
	//下拉刷新
	public Pagination getUpdatedPaginationArticle(int updateCount, String lastUpdatedDate);
	//我发表的文章
	public Pagination getUpdatedMyArticle(int updateCount, String lastUpdatedDate, String username);
	// 指定id的文章，比如点击进入文章明细页面
	public Article articlesById(Long id);

	// 发表文章
	public boolean addArticle(Article article);

	// 点赞
	public void like(Article article);
}
