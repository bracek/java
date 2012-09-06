<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="right">
    <div>
        <fmt:message key="button.locale"/>:
        <c:url var="englishLocaleUrl" value="/index.do">
            <c:param name="locale" value="" />
        </c:url>
        <c:url var="slovakLocaleUrl" value="/index.do">
            <c:param name="locale" value="sk" />
        </c:url>

        <a href='<c:out value="${englishLocaleUrl}"/>'><fmt:message key="locale.english"/></a>
        <a href='<c:out value="${slovakLocaleUrl}"/>'><fmt:message key="locale.slovak"/></a>
    </div>

    <div>&nbsp;</div>

    <div><fmt:message key="site.footer"/></div>
</div>