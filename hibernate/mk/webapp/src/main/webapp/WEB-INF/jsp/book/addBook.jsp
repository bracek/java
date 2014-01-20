<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="sk.mka.web.controller.BookFormController"%>

<h1>
	<fmt:message key="form.title.insertBook" />
</h1>
<fieldset>
	<legend>
		<fmt:message key="form.title.book" />
	</legend>
	<c:url var="url" value="/book/save.do" />
	<form:form action="${url}" commandName="<%=BookFormController.BOOK%>"
		method="post">
		<form:hidden path="id" />
		<table border="0">
			<tr>
				<td><form:label path="name" for="name">
						<fmt:message key="form.name" />:</form:label></td>
				<td>
					<div class="form-error-message">
						<form:errors path="name" />
					</div> <form:input id="name" path="name" cssErrorClass="form-error-field" />
				</td>
			</tr>
			<tr>
				<td><form:label path="author" for="author">
						<fmt:message key="form.author" />:</form:label></td>
				<td>
					<div class="form-error-message">
						<form:errors path="author" />
					</div> <form:input id="author" path="author"
						cssErrorClass="form-error-field" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tableFooter"></td>
			</tr>
			<tr>
				<td><a id="back" href="viewBooks.do"> <img
						alt="<fmt:message key='back'/>" title="<fmt:message key='back'/>"
						src="../img/icons/back.png" />
				</a></td>
				<td>
					<div class="form-buttons">
						<div class="button">
							<input type="image" src="../img/icons/accept.png"
								value="<fmt:message key="button.save"/>" />
						</div>
					</div>
				</td>

			</tr>
		</table>
	</form:form>
</fieldset>

