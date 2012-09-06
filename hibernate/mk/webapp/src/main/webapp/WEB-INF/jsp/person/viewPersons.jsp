<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="sk.mka.web.controller.PersonController"%>

<h1><fmt:message key="person.search.title"/></h1>
<table class="search">
    <tr>
        <th><fmt:message key="person.form.firstName"/></th>
        <th><fmt:message key="person.form.lastName"/></th>
        <th><fmt:message key="person.form.login"/></th>
    </tr>
    <c:forEach var="person" items="${persons}">
        <tr>
            <c:url var="editUrl" value="/info/person.do">
                <c:param name="id" value="${person.id}" />
            </c:url>
            <c:url var="deleteUrl" value="/delete/person.do">
                <c:param name="id" value="${person.id}" />
            </c:url>

            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.login}</td>
            <td>
                <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
                <a href='<c:out value="${deleteUrl}"/>'><fmt:message key="button.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
