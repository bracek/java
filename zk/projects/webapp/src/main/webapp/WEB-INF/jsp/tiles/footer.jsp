<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fi.ixonos.projects.web.servlet.ProjectsInitServlet"%>

<table border="0" class="header" align="right">
	<tbody>
		<tr>
			<td><fmt:message key="locale.changeLanguage" />: <c:url
					var="englishLocaleUrl" value="">
					<c:param name="locale" value="en" />
				</c:url> <c:url var="slovakLocaleUrl" value="">
					<c:param name="locale" value="sk" />
				</c:url> <a href='<c:out value="${englishLocaleUrl}"/>'><fmt:message
						key="locale.english" /></a> <a
				href='<c:out value="${slovakLocaleUrl}"/>'><fmt:message
						key="locale.slovak" /></a></td>
		</tr>
	</tbody>
</table>
