<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="secure"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%--<secure:authorize ifNotGranted="ROLE_USER,ROLE_GROUP_MANAGER,ROLE_ADMIN">
    <c:redirect url="/login.jsp?locale=en"/>

</secure:authorize>
<secure:authorize ifAnyGranted="ROLE_USER, ROLE_GROUP_MANAGER, ROLE_ADMIN">
    <c:redirect url="default.do?locale=en"/>
</secure:authorize>--%>


<c:redirect url="defaultBuilder.do?locale=en" />
