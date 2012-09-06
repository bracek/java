<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SkillNet - Error 500</title>
    <link rel="shortcut icon" href="<c:url value="/img/icons/icon_ixonos.ico"/>" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" media="all"/>
</head>

<body>
<table style="font-size: 12px;">
    <tr>
        <td><img src="<c:url value="/img/ixonos_logo_main.jpg"/>"/></td>
    </tr>
    <tr>
        <td>
            <h2>Error 500 - Internal server error - Error page</h2>
            <c:redirect url="/index.jsp"/>
        </td>
    </tr>
</table>

</body>

</html>