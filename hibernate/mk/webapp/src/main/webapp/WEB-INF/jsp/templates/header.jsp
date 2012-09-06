<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="headerTitle"><fmt:message key="site.title"/></div>

<div class="subHeader">

    Loged user: <security:authentication property="principal.username"/>
    <div align="right"><a href="<c:url value="/logout"/>"><fmt:message key="button.logout"/></a></div>
</div>
