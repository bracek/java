<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="title" /></title>
<link rel="shortcut icon" href="img/icons/n95.png" />
<link type="text/css" rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
</head>
<body>
	<div class="pageWrapper">
		<div class="menuWrapper">
			<map title="Navigation">
				<p>
					<c:choose>
						<c:when
							test="${fn:endsWith(pageContext.request.requestURI,'hello.jsp')}">
							<span class="currentPage">[Welcome page]</span>
						</c:when>
						<c:otherwise>
[<a href="hello.do">Welcome page</a>]
</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when
							test="${fn:endsWith(pageContext.request.requestURI,'inventory.jsp') 
 or fn:endsWith(pageContext.request.requestURI,'editProduct.jsp')}">
							<span class="currentPage">[Manage Inventory]</span>
						</c:when>
						<c:otherwise>
[<a href="inventory.do">Manage Inventory</a>]
</c:otherwise>
					</c:choose>
				</p>
			</map>
		</div>
		<div class="bodyWrapper">