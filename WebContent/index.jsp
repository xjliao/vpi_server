<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	Welcome!
	<c:choose>
		<c:when test="${user.username == null }">
			<a href="${ctx }/user.action?method:login">登陆</a>&nbsp;&nbsp;
	 		<a href="${ctx }/user.action?method:register">注册</a>
		</c:when>
		<c:otherwise>
			<font color='red'>${user.username } </font>
			<a href="${ctx }/user.action?method:logout">退出</a>
			<a href="${ctx }/article.action?method:addArticle&userId=${user.id}">发表文章</a>
			<br />
		</c:otherwise>

	</c:choose>
	<br />
	<font color="blue">所有文章</font>
	<table>
		<tr>
			<th>序号</th>
			<th>id</th>
			<th>标题</th>
			<th>内容</th>
			<c:if test="${user.username != null }">
				<th>赞一个</th>
			</c:if>
		</tr>

		<c:forEach var="article" items="${allArticles }" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${article.id} </td>
				<td>${article.title }</td>
				<td>${article.content }</td>
				<c:if test="${user.username != null }">
					<td>
						<a href="${ctx }/article.action?method:likeArticle&userId=${user.id}&articleId=${article.id}">点赞${article.likeCount}</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<c:if test="${user.username != null }">
		<font color="blue">我发表的文章</font>
		<table>
			<tr>
				<th>序号</th>
				<th>id</th>
				<th>标题</th>
				<th>内容</th>
			</tr>

			<c:forEach var="article" items="${myArticles }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${article.id }</td>
					<td>${article.title }</td>
					<td>${article.content }</td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

</body>
</html>