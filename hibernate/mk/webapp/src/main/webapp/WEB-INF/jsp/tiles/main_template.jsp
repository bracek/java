<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<security:authorize
        ifAnyGranted="ROLE_USER,ROLE_GROUP_MANAGER,ROLE_ADMIN">
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <tiles:useAttribute name="titleKey" classname="java.lang.String"/>
        <title>SkillNet - <fmt:message key="${titleKey}"/></title>

        <link rel="shortcut icon" href="<c:url value="/img/icons/icon_ixonos.ico"/>" type="image/x-icon"/>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" media="screen"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/menu.css"/>" media="screen"/>
    </head>

    <body>
    <table id="layoutTable" border="0" cellpadding="0" cellspacing="0"
           width="100%" height="100%">
        <tr valign="top" height="100">
            <td colspan="2"><tiles:insertAttribute name="header"/></td>
        </tr>
        <tr valign="top">
            <td width="200"><tiles:insertAttribute name="menu"/></td>
            <td><tiles:insertAttribute name="body"/></td>
        </tr>
        <tr valign="bottom" height="60">
            <td colspan="2"><tiles:insertAttribute name="footer"/></td>
        </tr>
    </table>
    </body>
    </html>
</security:authorize>
