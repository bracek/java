<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<h1>Welcome to jAstrologer</h1>
	<f:view>
		<h:form>
			<p>
				Enter your name:
				<h:inputText value="#{UserBean.name}" id="name" required="true" />
				<h:message for="name" />
			</p>

			<p>
				Enter your email:
				<h:inputText value="email" id="email" required="true">
					<f:validator validatorId="sk.mka.web.validator.EmailValidator" />
				</h:inputText>
				<h:message for="email" />
			</p>


			<p>
				Enter your birthday:
				<h:inputText value="#{UserBean.birthday}" id="birthday"
					required="true">
					<f:converter converterId="sk.mka.web.converter.MyDateConverter" />
				</h:inputText>
				<h:message for="birthday" style="color:#f00;" />
				(dd/mm/yyyy)
			</p>

			<h:commandButton value="Submit" action="submit" />
		</h:form>
	</f:view>

</body>
</html>
