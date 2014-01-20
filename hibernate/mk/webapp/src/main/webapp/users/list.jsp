<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="sk.mka.web.controller.view.ListUsersController"%>
<%@ page import="sk.mka.web.controller.UsersController"%>

<h1>
	<fmt:message key="users.title" />
</h1>
<table border="0" cellspacing="3" cellpadding="1">
	<tr>
		<th class="alignRight">Id</th>
		<th class="alignRight"><fmt:message key="form.name" /></th>
		<th><fmt:message key="form.edit" /></th>
		<th><fmt:message key="form.delete" /></th>
	</tr>
	<tr>
		<td colspan="7" class="tableHeader" />
	</tr>
	<c:forEach var="myobj" items="${users}">
		<tr>

			<c:url var="editUrl" value="/users/edit.do">
				<c:param name="id" value="${myobj.id}" />
			</c:url>
			<c:url var="deleteUrl" value="/users/delete.do">
				<c:param name="id" value="${myobj.id}" />
			</c:url>
			<c:url var="addUrl" value="/users/saveusers.do" />

			<td>${myobj.id}</td>
			<td>${myobj.name}</td>

			<td class="alignRight"><a href='<c:out value="${editUrl}"/>'>
					<img alt="<fmt:message key='edit'/>"
					title="<fmt:message key='edit'/>" src="../img/icons/edit.png" />
			</a></td>


			<td class="alignRight"><a href='<c:out value="${deleteUrl}"/>'>
					<img alt="<fmt:message key='edit'/>"
					title="<fmt:message key='edit'/>" src="../img/icons/delete.png" />
			</a></td>

		</tr>
		<tr>
	</c:forEach>

	<td colspan="6" class="tableFooter"><a href="${addUrl}"> <img
			alt="<fmt:message key='new'/>" title="<fmt:message key='new'/>"
			src="../img/icons/add.png" />
	</a></td>
	</tr>
</table>


