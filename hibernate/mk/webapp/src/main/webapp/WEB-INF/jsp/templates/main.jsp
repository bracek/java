<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<security:authorize
	ifAnyGranted="ROLE_USER,ROLE_GROUP_MANAGER,ROLE_ADMIN">

	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="site.title" /></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>" />

</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
	</html>


</security:authorize>
