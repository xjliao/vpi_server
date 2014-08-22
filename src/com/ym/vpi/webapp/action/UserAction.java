package com.ym.vpi.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ym.vpi.model.Article;
import com.ym.vpi.model.User;
import com.ym.vpi.service.ArticleManager;
import com.ym.vpi.service.UserManager;

@ParentPackage(value = "default")
@Namespace(value = "")
@Results({ @Result(name = "login", location = "/login.jsp"),
		@Result(name = "register", location = "/register.jsp"),
		@Result(name = "success", location = "index.action", type = "redirect") })
public class UserAction extends AppBaseAction {
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String msg;
	private User user;
	private List<Article> allArticles;
	private List<Article> myArticles;

	public String execute() {
		return "login";
	}

	/**
	 * 登陆
	 * 
	 * @return
	 */
	public String login() {
		if (null != username && null != password && !"".equals(username)
				&& !"".equals(password)) {
			boolean isLogin = this.getUserManager().login(username, password);
			// 登陆校验成功，返回首页
			if (isLogin) {
				// 获取登陆用户的信息
				user = this.getUserManager().getUserByUsername(username);
				// 将已登录的用户，放入session中，方便使用
				ServletActionContext.getContext().getSession()
						.put("user", user);

				return "success";
			}

			msg = "登陆失败";
			return "login";
		}

		return "login";
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session != null) {
			// 移除session中得user信息
			ServletActionContext.getContext().getSession().remove("user");
			session.invalidate();
		}

		ServletActionContext.getRequest().getSession(true);
		return "login";

	}

	public String register() {
		// 一些基本的注册条件
		if (null != email && !"".equals(email) && null != username
				&& !"".equals(username) && null != password
				&& !"".equals(password) && null != confirmPassword
				&& !"".equals(confirmPassword)) {

			if (!password.equals(confirmPassword)) {
				msg = "两次输入密码不正确！";
				return "register";
			}

			user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			boolean isRegister = this.getUserManager().register(user);
			// 注册成功
			if (isRegister) {
				// 注册完后，自动登录
				return this.login();
			}

			msg = "注册失败";
			return "register";
		}

		return "register";
	}

	public void mobileRegister() {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().setContentType("text/json");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject obj = new JSONObject();
			String msg = "failure";

			if (null != email && !"".equals(email) && null != username
					&& !"".equals(username) && null != password
					&& !"".equals(password)) {
				user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				boolean isRegister = this.getUserManager().register(user);
				// 注册成功
				if (isRegister) {
					// 注册完后，自动登录
					obj.put("accountId", this.getUserManager().getUserByUsername(user.getUsername()));
					msg = "ok";
				}
			}

			obj.put("msg", msg);
			out.write(obj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mobileLogin() {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().setContentType("text/json");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject userObj = new JSONObject();
			JSONObject userJsonObj = new JSONObject();
			String msg = "failure";

			if (null != username && null != password && !"".equals(username)
					&& !"".equals(password)) {
				boolean isLogin = this.getUserManager().login(username,
						password);
				// 登陆校验成功，返回首页
				if (isLogin) {
					// 获取登陆用户的信息
					user = this.getUserManager().getUserByUsername(username);
					// 将已登录的用户，放入session中，方便使用
					ServletActionContext.getContext().getSession()
							.put("user", user);
					userObj.put("id", user.getId());
					userObj.put("username", user.getUsername());
					userObj.put("password", user.getPassword());
					userJsonObj.put("user", userObj.toString());
					msg = "ok";
				}
			}
			
			System.out.println("用户名=" + username);
			System.out.println("密码=" + password);
			
			userJsonObj.put("msg", msg);
			out.write(userJsonObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void mobileMyArticle() {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			JSONObject obj = new JSONObject();
			String msg = "failure";

			if (null != email && !"".equals(email) && null != username
					&& !"".equals(username) && null != password
					&& !"".equals(password)) {
				user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				boolean isRegister = this.getUserManager().register(user);
				// 注册成功
				if (isRegister) {
					//获取注册
					Long userId = this.getUserManager()
							.getUserByUsername(username).getId();
					obj.put("accountId", userId);
					msg = "ok";
				}
			}

			obj.put("msg", msg);
			out.write(obj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}
