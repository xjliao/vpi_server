package com.ym.vpi.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

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

	//推送
	private String devToken;
	
	public void push() {
		try {
			PayLoad payLoad = new PayLoad();
	        payLoad.addAlert("这是一个iphone push demo");//push的内容
	        payLoad.addBadge(1);//图标小红圈的数值
	        payLoad.addSound("default");//铃音
	                  
	        PushNotificationManager pushManager = PushNotificationManager.getInstance();
	        pushManager.addDevice("iPhone", devToken);
	                  
	        //Connect to APNs
	                    /************************************************
	                    测试的服务器地址：gateway.sandbox.push.apple.com /端口2195 
	        产品推送服务器地址：gateway.push.apple.com / 2195 
	                   ***************************************************/
	        String host= "gateway.sandbox.push.apple.com";
	        int port = 2195;
	        String certificatePath= "/Users/xjliao/Desktop/push/vpipush.p12";//导出的证书
	        String certificatePassword= "199010";//此处注意导出的证书密码不能为空因为空密码会报错
	        pushManager.initializeConnection(host,port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
	                  
	        //Send Push
	        Device client = pushManager.getDevice("iPhone");
	        pushManager.sendNotification(client, payLoad);
	        pushManager.stopConnection();
	
	        pushManager.removeDevice("iPhone");
       } catch (Exception e) {
    	   e.printStackTrace();
       }
		
	}
	
	
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

	public String getDevToken() {
		return devToken;
	}

	public void setDevToken(String devToken) {
		this.devToken = devToken;
	}
	
}
