<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发表文章</title>
</head>
<body>
	<form action="${ctx }/article.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="userId" value="${userId }"/>
		<table>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><textarea rows="5" cols="15" name="content"></textarea>
			</tr>
			<tr>
				<td>图片1</td>
				<td><input type="file" name="files" accept="image/gif, image/jpeg"/></td>
			</tr>
			<tr>
				<td>图片2</td>
				<td><input type="file" name="files" accept="image/gif, image/jpeg" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="method:addArticle" value="发表" /></td>
			</tr>
		</table>
	</form>
</body>
</html>