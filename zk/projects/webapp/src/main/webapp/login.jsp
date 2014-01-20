<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Projects - Login</title>

<link rel="shortcut icon"
	href="<c:url value="/img/icons/icon_ixonos.ico"/>" type="image/x-icon" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>" media="all" />

</head>

<body onload="document.f.j_username.focus();" class="background">

	<div align="center" style="margin-top: 100px;">

		<form name="f" action="<c:url value="/j_security_check"/>"
			method="POST">
			<table style="font-size: 12px;" class="header">
				<tr>
					<td><h1>Projects Allocation Tool</h1></td>
				</tr>
				<tr>
					<td><c:if test="${not empty param.login_error}">
							<span style="color: red"> Your login attempt was not
								successful, try again.<br /> <br /> Reason: <c:out
									value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
							</span>
						</c:if></td>
				</tr>
			</table>
			<table style="font-size: 12px;" class="header">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr align="center">
					<td align="left">User:</td>
					<td><input style="width: 150px" type='text' name='j_username'
						value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
					</td>
				</tr>
				<tr align="center">
					<td align="left">Password:</td>
					<td><input style="width: 150px" type='password'
						name='j_password'></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox"
						name="_spring_security_remember_me"
						id="_spring_security_remember_me"><label
						for="_spring_security_remember_me">Don't ask for my
							password for two weeks</label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<!--                        <td><a href='<c:url value="/usermanagement/usersAdd.do"/>'><fmt:message key="menu.usermanagement.add" />Register</a></td>-->
					<td colspan="2">
						<table>
							<tr>
								<td width="190px"><input name="submit" type="submit"
									value="Log In"></td>
								<td><input name="reset" type="reset"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>

	</div>

</body>
</html>