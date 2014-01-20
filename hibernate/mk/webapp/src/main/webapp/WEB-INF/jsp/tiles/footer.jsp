<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ixonos.skillnet.web.servlet.SkillnetInitServlet"%>

<div class="footer" style="height: 60px; background: #c5e6ef;">
	<div style="position: absolute; text-align: left; margin-left: 10px;">
		<div>&nbsp;</div>
		<div>&nbsp;</div>
		<jsp:include page="/WEB-INF/jsp/tiles/changelog/changelogLabel.zul" />
	</div>
	<div>&nbsp;</div>
	<div style="text-align: right; margin-right: 10px;">
		Ixonos SkillNet -
		<fmt:message key="footer.version" /><%= SkillnetInitServlet.props.get("skillnet.version") %>
		&copy; 2009 Ixonos
	</div>
	<div style="text-align: right; margin-right: 10px;">
		<fmt:message key="locale.changeLanguage" />
		:
		<c:url var="englishLocaleUrl" value="">
			<c:param name="locale" value="en" />
		</c:url>
		<c:url var="slovakLocaleUrl" value="">
			<c:param name="locale" value="sk" />
		</c:url>
		<a href='<c:out value="${englishLocaleUrl}"/>'><fmt:message
				key="locale.english" /></a> <a
			href='<c:out value="${slovakLocaleUrl}"/>'><fmt:message
				key="locale.slovak" /></a>
	</div>
</div>