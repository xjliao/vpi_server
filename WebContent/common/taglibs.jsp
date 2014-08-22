<%@ page language="java" pageEncoding="utf-8" import="java.util.*"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="me" value="${SECURITY_CONTEXT_KEY}" scope="session"/>