<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="secure"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<secure:authorize ifAnyGranted="ROLE_USER">
	<c:redirect url="index.do?locale=${param.locale}" />
</secure:authorize>
<secure:authorize ifNotGranted="ROLE_USER">
	<c:redirect url="/login.jsp?locale=${param.locale}" />
</secure:authorize>
