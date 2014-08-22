package com.ym.vpi.dao;

import java.util.Date;
import java.util.List;

import com.ym.vpi.Pagination;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;

public interface ArticleDao {
	// 我发表的文章
	public List<Article> getArticlesByUsername(String username);

	public List<Article> getMyArticles(User user);

	// 发表文章
	public boolean save(Article article);

	// 所有文章
	public List<Article> getAllArticles();
	
	// 分页文章
//	public List<Article> getPaginationArticle(final int currentPage, final int pageSize);
	public Pagination getPaginationArticle(int currentPage, int pageSize);
	
	//下拉刷新
	public Pagination getUpdatedPaginationArticle(int updateCount, Date lastUpdatedDate);
	public Pagination getUpdatedPaginationArticle(int updateCount, String lastUpdatedDate);

	// 某篇指定文章的
	public Article getArticleById(Long id);
	
	// 文章的修改、点赞数等
	public void update(Article article);
}
