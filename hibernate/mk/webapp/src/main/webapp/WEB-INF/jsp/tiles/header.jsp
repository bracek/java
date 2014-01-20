<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="header" style="height: 100px; background: #c5e6ef;">
	<div style="position: absolute; text-align: right; margin-right: 10px;">
		<img src="<c:url value="/img/ixonos_logo_main.jpg"/>" />
	</div>
	<div>&nbsp;</div>
	<div style="text-align: right; margin-right: 10px;">
		<%--
            principal equals com.ixonos.skillnet.logic.security.SkillnetUser
            for example: pricipal.fullName == skillnetUser.getFullName() 
         --%>
		<fmt:message key="header.loggeduser" />
		:&nbsp;
		<security:authentication property="principal.fullName" />
		&nbsp;[
		<security:authentication property="principal.username" />
		] &nbsp;<a href="<c:url value="/logout"/>"><fmt:message
				key="header.logout" /></a>
	</div>
	<div style="text-align: right; margin-right: 10px;">
		<jsp:include
			page="/WEB-INF/jsp/tiles/usermanagement/changePasswordLabel.zul" />
	</div>
</div>