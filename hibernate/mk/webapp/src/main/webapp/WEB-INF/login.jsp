<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>miroslav katrak's webpage</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/main.css"/>" />
</head>

<body>
	<div id="content">
		<h1>Login Required</h1>

		<div class="section">
			<table class="search">
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
		</div>

		<div class="section">
			<c:if test="${not empty param.login_error}">
				<div class="errors">
					Your login attempt was not successful, try again.<br /> <br />
					Reason:
					<%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage()%>
				</div>
			</c:if>
		</div>

		<div class="section">
			<form name="f" action="<c:url value="/loginProcess" />" method="post">
				<fieldset>
					<div class="field">
						<div class="label">
							<label for="j_username">User:</label>
						</div>
						<div class="output">
							<input type="text" name="j_username" id="j_username"
								<c:if test="${not empty param.login_error}">value="<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>"</c:if> />
							<script type="text/javascript">
                                    Spring.addDecoration(new Spring.ElementDecoration({
                                        elementId : "j_username",
                                        widgetType : "dijit.form.ValidationTextBox",
                                        widgetAttrs : { promptMessage : "Your username", required : true }}));
                                </script>
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="j_password">Password:</label>
						</div>
						<div class="output">
							<input type="password" name="j_password" id="j_password" />
							<script type="text/javascript">
                                    Spring.addDecoration(new Spring.ElementDecoration({
                                        elementId : "j_password",
                                        widgetType : "dijit.form.ValidationTextBox",
                                        widgetAttrs : { promptMessage : "Your password", required : true}}));
                                </script>
						</div>
					</div>
					<div class="field">
						<div class="label">
							<label for="remember_me">Don't ask for my password for
								two weeks:</label>
						</div>
						<div class="output">
							<input type="checkbox" name="_spring_security_remember_me"
								id="remember_me" />
							<script type="text/javascript">
                                    Spring.addDecoration(new Spring.ElementDecoration({
                                        elementId : "remember_me",
                                        widgetType : "dijit.form.CheckBox"}));
                                </script>
						</div>
					</div>
					<div class="form-buttons">
						<div class="button">
							<input name="submit" id="submit" type="submit" value="Login" />
							<script type="text/javascript">
                                    Spring.addDecoration(new Spring.ValidateAllDecoration({event : 'onclick', elementId : 'submit'}));
                                </script>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>

</body>
</html>
