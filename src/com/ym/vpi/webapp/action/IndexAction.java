package com.ym.vpi.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ym.vpi.Pagination;
import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;
import com.ym.vpi.service.ArticleManager;

@ParentPackage(value = "default")
@Namespace(value = "")
@Results({ @Result(name = "index", location = "/index.jsp") })
public class IndexAction extends AppBaseAction {
	private ArticleManager articleManager;
	private List<Article> allArticles;
	private List<Article> myArticles;
	private User user;
	private Integer nowPage;
	private Integer pageSize;
	private Pagination paginations;
	private String lastUpdatedDateStr;
	private String username;


	public String execute() {
		user = (User) ServletActionContext.getContext().getSession()
				.get("user");
		allArticles = articleManager.allArticles();
//		if (null == nowPage || null == pageSize) {
//			allArticles = articleManager.getPaginationArticle(1, 2);
//		} else {
//			allArticles = articleManager.getPaginationArticle(nowPage, pageSize);
//		}
		//登录后显示我发表的内容
		if (null != user) {
			myArticles = articleManager.myArticles(user);
		}

		return "index";
	}
	
	@SuppressWarnings("unchecked")
	public void mobileExecute() {
		System.out.println("nowPage=" + nowPage + "-pageSize=" + pageSize);
		//默认分页
		if (nowPage == null || pageSize == null) {
			nowPage = 1;
			pageSize = 2;
		}
		
		paginations = articleManager.getPaginationArticle(nowPage, pageSize);
		ServletActionContext.getResponse().setContentType("text/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			
			JSONObject pagintionObj = new JSONObject();
			JSONObject rowsObj = new JSONObject();
			JSONArray rowsArr = new JSONArray();
			
			allArticles = (List<Article>) paginations.getRows();
			
			for (int i = 0, n = allArticles.size(); i < n; i++) {
				Article article = allArticles.get(i);
				JSONObject rowObj = new JSONObject();
				rowObj.put("id", article.getId());
				rowObj.put("title", article.getTitle());
				rowObj.put("content", article.getContent());
				rowObj.put("likeCount", article.getLikeCount());
				rowObj.put("imageId", "1122");
				rowsArr.add(rowObj);
			}
			rowsObj.put("countPage", paginations.getCountPage());
			rowsObj.put("from", paginations.getFrom());
			rowsObj.put("nowPage", paginations.getNowPage());
			rowsObj.put("rows", rowsArr);
			rowsObj.put("size", paginations.getSize());
			rowsObj.put("total", paginations.getTotal());
			
			pagintionObj.put("pagination", rowsObj);
			
			out.println(pagintionObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void mobileMyExecute() {
		System.out.println("nowPage=" + nowPage + "-pageSize=" + pageSize);
		//默认分页
		if (nowPage == null || pageSize == null) {
			nowPage = 1;
			pageSize = 5;
		}
		
		paginations = articleManager.getPaginationMyArticle(nowPage, pageSize, username);
		System.out.println("我的教程:" + paginations.getSize());
		ServletActionContext.getResponse().setContentType("text/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			
			JSONObject pagintionObj = new JSONObject();
			JSONObject rowsObj = new JSONObject();
			JSONArray rowsArr = new JSONArray();
			
			allArticles = (List<Article>) paginations.getRows();
			
			for (int i = 0, n = allArticles.size(); i < n; i++) {
				Article article = allArticles.get(i);
				JSONObject rowObj = new JSONObject();
				rowObj.put("id", article.getId());
				rowObj.put("title", article.getTitle());
				rowObj.put("content", article.getContent());
				rowObj.put("likeCount", article.getLikeCount());
				rowObj.put("imageId", "1122");
				rowsArr.add(rowObj);
			}
			rowsObj.put("countPage", paginations.getCountPage());
			rowsObj.put("from", paginations.getFrom());
			rowsObj.put("nowPage", paginations.getNowPage());
			rowsObj.put("rows", rowsArr);
			rowsObj.put("size", paginations.getSize());
			rowsObj.put("total", paginations.getTotal());
			
			pagintionObj.put("pagination", rowsObj);
			
			out.println(pagintionObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void mobileUpatedExecute() {
		System.out.println("上一次更新时间:" + lastUpdatedDateStr);
		
		if (lastUpdatedDateStr != null && !"".equals(lastUpdatedDateStr)) {
			paginations = articleManager.getUpdatedPaginationArticle(5, lastUpdatedDateStr);
			System.out.println("更新" + paginations.getSize() + "条");
			
			ServletActionContext.getResponse().setContentType("text/json");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			
			try {
				PrintWriter out = ServletActionContext.getResponse().getWriter();
				
				JSONObject pagintionObj = new JSONObject();
				JSONObject rowsObj = new JSONObject();
				JSONArray rowsArr = new JSONArray();
				
				allArticles = (List<Article>) paginations.getRows();
				
				if (allArticles != null && allArticles.size() > 0) {
					for (int i = 0, n = allArticles.size(); i < n; i++) {
						Article article = allArticles.get(i);
						JSONObject rowObj = new JSONObject();
						rowObj.put("id", article.getId());
						rowObj.put("title", article.getTitle());
						rowObj.put("content", article.getContent());
						rowObj.put("likeCount", article.getLikeCount());
						rowObj.put("imageId", "1122");
						rowsArr.add(rowObj);
					}
				
					rowsObj.put("countPage", paginations.getCountPage());
					rowsObj.put("from", paginations.getFrom());
					rowsObj.put("nowPage", paginations.getNowPage());
					rowsObj.put("rows", rowsArr);
					rowsObj.put("size", paginations.getSize());
					rowsObj.put("total", paginations.getTotal());
				
					pagintionObj.put("pagination", rowsObj);
				
					out.println(pagintionObj.toString());
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void mobileUpatedMyArticleExecute() {
		System.out.println("上一次更新时间:" + lastUpdatedDateStr);
		
		if (lastUpdatedDateStr != null && !"".equals(lastUpdatedDateStr)) {
			paginations = articleManager.getUpdatedMyArticle(5, lastUpdatedDateStr, username);
			System.out.println("更新" + paginations.getSize() + "条");
			
			ServletActionContext.getResponse().setContentType("text/json");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			
			try {
				PrintWriter out = ServletActionContext.getResponse().getWriter();
				
				JSONObject pagintionObj = new JSONObject();
				JSONObject rowsObj = new JSONObject();
				JSONArray rowsArr = new JSONArray();
				
				allArticles = (List<Article>) paginations.getRows();
				
				if (allArticles != null && allArticles.size() > 0) {
					for (int i = 0, n = allArticles.size(); i < n; i++) {
						Article article = allArticles.get(i);
						JSONObject rowObj = new JSONObject();
						rowObj.put("id", article.getId());
						rowObj.put("title", article.getTitle());
						rowObj.put("content", article.getContent());
						rowObj.put("likeCount", article.getLikeCount());
						rowObj.put("imageId", "1122");
						rowsArr.add(rowObj);
					}
				
					rowsObj.put("countPage", paginations.getCountPage());
					rowsObj.put("from", paginations.getFrom());
					rowsObj.put("nowPage", paginations.getNowPage());
					rowsObj.put("rows", rowsArr);
					rowsObj.put("size", paginations.getSize());
					rowsObj.put("total", paginations.getTotal());
				
					pagintionObj.put("pagination", rowsObj);
				
					out.println(pagintionObj.toString());
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	
//	@SuppressWarnings("unchecked")
//	public void mobileExecute4() {
//		//默认分页
//		if (nowPage == null && pageSize == null) {
//			nowPage = 1;
//			pageSize = 3;
//		}
//		
//		paginations = articleManager.getPaginationArticle(nowPage, pageSize);
//		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
//		
//		try {
//			PrintWriter out = ServletActionContext.getResponse().getWriter();
//			
//			JSONObject pagintionObj = new JSONObject();
//			JSONObject rowsObj = new JSONObject();
//			JSONArray rowsArr = new JSONArray();
//			
//			allArticles = (List<Article>) paginations.getRows();
//			
//			for (int i = 0, n = allArticles.size(); i < n; i++) {
//				Article article = allArticles.get(i);
//				JSONObject rowObj = new JSONObject();
//				rowObj.put("id", article.getId());
//				rowObj.put("title", article.getTitle());
//				rowObj.put("content", article.getContent());
//				rowObj.put("likeCount", article.getLikeCount());
//				rowObj.put("imageId", "1122");
//				rowsArr.add(rowObj);
//			}
//			rowsObj.put("countPage", paginations.getCountPage());
//			rowsObj.put("from", paginations.getFrom());
//			rowsObj.put("nowPage", paginations.getNowPage());
//			rowsObj.put("rows", rowsArr);
//			rowsObj.put("size", paginations.getSize());
//			rowsObj.put("total", paginations.getTotal());
//			
//			pagintionObj.put("pagination", rowsObj);
//			
//			out.println(pagintionObj.toString());
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void mobileExecuteVip() {
		//默认分页
		if (nowPage == null && pageSize == null) {
			nowPage = 1;
			pageSize = 3;
		}
		
		paginations = articleManager.getPaginationArticle(nowPage, pageSize);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			
			JSONObject pagintionObj = new JSONObject();
			JSONObject rowsObj = new JSONObject();
			JSONArray rowsArr = new JSONArray();
			
			allArticles = (List<Article>) paginations.getRows();
			
			for (int i = 0, n = allArticles.size(); i < n; i++) {
				Article article = allArticles.get(i);
				JSONObject rowObj = new JSONObject();
				rowObj.put("id", article.getId());
				rowObj.put("title", article.getTitle());
				rowObj.put("content", article.getContent());
				rowObj.put("likeCount", article.getLikeCount());
				rowObj.put("imageId", "1122");
				rowsArr.add(rowObj);
			}
			rowsObj.put("countPage", paginations.getCountPage());
			rowsObj.put("from", paginations.getFrom());
			rowsObj.put("nowPage", paginations.getNowPage());
			rowsObj.put("rows", rowsArr);
			rowsObj.put("size", paginations.getSize());
			rowsObj.put("total", paginations.getTotal());
			
			pagintionObj.put("pagination", rowsObj);
			
			out.println(pagintionObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(List<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public List<Article> getMyArticles() {
		return myArticles;
	}

	public void setMyArticles(List<Article> myArticles) {
		this.myArticles = myArticles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getLastUpdatedDateStr() {
		return lastUpdatedDateStr;
	}

	public void setLastUpdatedDateStr(String lastUpdatedDateStr) {
		this.lastUpdatedDateStr = lastUpdatedDateStr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
