<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Miroslav Katrak's webpage</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>miroslav katrak's webpage</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
    </head>

    <body>
    <div id="content">
        <h1>Login Required</h1>
    </div>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<c:url value="/img/icons/icon_ixonos311.ico"/>" type="image/x-icon"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" media="all" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/calendar-win2k-1.css"/>" media="all" />
    <script type="text/javascript" src="<c:url value="/js/jquery-1.2.6.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
    </head>

    <body onload="document.f.j_username.focus();">
        <table align="center" border="1">
            <tr>
               <th colspan="2">Test logins:</th>
                
            </tr>
            <tr>
                <td>login/password</td>
                <td>group name</td>
            </tr>
            <tr>
                <td>kari / kari</td>
                <td>ROLE_CEO</td>
            </tr>
            <tr>
                <td>roman / roman</td>
                <td>ROLE_SITEMANAGER</td>
            </tr>
            <tr>
                <td>slavo / slavo</td>
                <td>ROLE_GROUPMANAGER</td>
            </tr>
            <tr>
                <td>marian / marian</td>
                <td>ROLE_GROUPMANAGER</td>
            </tr>
            <tr>
                <td>jano / jano</td>
                <td>ROLE_USER</td>
            </tr>
            <tr>
                <td>miro / miro</td>
                <td>ROLE_USER</td>
            </tr>
        </table>

        <c:if test="${not empty param.login_error}">
            <span style="color:red">
                Your login attempt was not successful, try again.<br/><br/>
                Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
            </span>
        </c:if>

        <form name="f" action="<c:url value="/j_security_check"/>" method="POST">
            <table align="center">
                <tr><td>User:</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
                <tr><td>Password:</td><td><input type='password' name='j_password'></td></tr>
                <tr><td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr>

                <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
                <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
            </table>

        </form>

    </body>
</html>