package com.ym.vpi.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ym.vpi.Pagination;
import com.ym.vpi.dao.ArticleDao;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;

public class ArticleDaoImpl implements ArticleDao {
	private JdbcTemplate jdbcTemplate;
	private HibernateTemplate hibernateTemplate;
	private Article article;
	private List<Article> articles;
	private Pagination pagination;

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getMyArticles(User user) {
		// TODO Auto-generated method stub
		String sqlStr = "from Article a where a.user.id = " + user.getId();
		articles = (List<Article>) hibernateTemplate.find(sqlStr);

		return articles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesByUsername(String username) {
		// TODO Auto-generated method stub
		String sqlStr = "from Article a where a.user.username = '" + username
				+ "'";
		articles = (List<Article>) hibernateTemplate.find(sqlStr);
		return articles;
	}

	/**
	 * 新建文章
	 */
	@Override
	public boolean save(Article article) {
		if (hibernateTemplate.save(article) != null) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		String sqlStr = "from Article ORDER BY createTime desc";
		articles = (List<Article>) hibernateTemplate.find(sqlStr);

		return articles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Article getArticleById(Long id) {
		// TODO Auto-generated method stub
		String sqlStr = "from Article a where a.id = " + id;
		articles = (List<Article>) hibernateTemplate.find(sqlStr);

		if (null != articles && articles.size() > 0) {
			for (int i = 0, n = articles.size(); i < n; i++) {
				article = articles.get(i);

				return article;
			}
		}
		return null;
	}

	// 文章修改，点赞数等
	@Override
	public void update(Article article) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(article);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
//	public List<Article> getPaginationArticle(final int currentPage, final int pageSize) {
//		final int startIndex = (currentPage - 1) * pageSize;	
//		final String sql = "from Article";
//		final int counts = hibernateTemplate.find(sql).size();
		
		// TODO Auto-generated method stub
//		articles = hibernateTemplate.execute(new HibernateCallback() {
//
//			@Override
//			public Object doInHibernate(Session arg0)
//					throws HibernateException, SQLException {
//				// TODO Auto-generated method stub
//		          Query q = (Query) hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(sql);  
//	              q.setFirstResult(startIndex);  
//	              q.setMaxResults(pageSize);  
//	              List<Article> results =q.list();
//	              return results;
//			}
//		});
//		return articles;
//	}
	
	public Pagination getPaginationArticle(int currentPage, final int pageSize) {
		final int startIndex = (currentPage - 1) * pageSize;
		final String sql = "from Article ORDER BY createTime desc";
		int total = hibernateTemplate.find(sql).size();
		//有效数则进位
		int countPage = total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
		
		articles =  hibernateTemplate.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(sql); 
				query.setFirstResult(startIndex); 
				query.setMaxResults(pageSize); 
				List list = query.list(); 
				return list;
			}
		});
		
		pagination = new Pagination();
		pagination.setSize(pageSize);
		pagination.setFrom(startIndex + 1);
		pagination.setNowPage(currentPage);
		pagination.setCountPage(countPage);
		pagination.setTotal(total);
		pagination.setRows(articles);
		
		return pagination;
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Pagination getPaginationMyArticle(int currentPage, final int pageSize, String username) 
	{
		final int startIndex = (currentPage - 1) * pageSize;
		final String sql = "from Article where user.username = '" + username + "' ORDER BY createTime desc";
		int total = hibernateTemplate.find(sql).size();
		System.out.println("total=" + total);
		//有效数则进位
		int countPage = total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
		System.out.println("countPage=" + countPage);
		System.out.println("pageSize=" + pageSize);
		articles =  hibernateTemplate.executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(sql); 
				query.setFirstResult(startIndex); 
				query.setMaxResults(pageSize); 
				List list = query.list(); 
				return list;
			}
		});
		
		pagination = new Pagination();
		pagination.setSize(pageSize);
		pagination.setFrom(startIndex + 1);
		pagination.setNowPage(currentPage);
		pagination.setCountPage(countPage);
		pagination.setTotal(total);
		pagination.setRows(articles);
		
		return pagination;
	}
	
	@Override
	public Pagination getUpdatedPaginationArticle(int updateCount,
			String lastUpdatedDate) {
		// TODO Auto-generated method stub

		String sql = "from Article a where a.createTime > '" + lastUpdatedDate + "' ORDER BY a.createTime desc";
		
		articles =   hibernateTemplate.find(sql);
		pagination = new Pagination();
		pagination.setRows(articles);
		
		return pagination;
	}
	
	public Pagination getUpdatedMyArticle(int updateCount, String lastUpdatedDate, String username) {
		String sql = "from Article a where a.createTime > '" + lastUpdatedDate + "'"
					+ " and user.username = '" + username + "' ORDER BY a.createTime desc";
		
		articles =   hibernateTemplate.find(sql);
		pagination = new Pagination();
		pagination.setRows(articles);
		
		return pagination;
	}


}
