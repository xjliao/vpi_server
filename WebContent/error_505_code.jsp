<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%> 
<%@ page import="java.io.PrintWriter"%> 
<html> 
<head> 
<title>error.jsp title</title> 
</head> 
<body> 
<h2>错误信息：</h2> 
<%= exception %> 
<br> 

<h2>问题如下：</h2><% exception.printStackTrace(new PrintWriter(out));%> 
<hr> 

</body> 
</html>