<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="stibrik.springapp.web.EditProductController"%>

<%@ include file="/include/header.jsp"%>

<script language="JavaScript">
$( function() {
$('#accept').click( function() {
$('#productForm').submit();
});
});

$( function() {
$('#delete').click( function() {
if (confirm('Delete?')) {
return true;
} else {
return false;
};
});
});
</script>

<h3>
	<fmt:message key="editProduct.heading" />
</h3>
<fieldset>
	<legend>
		<fmt:message key="product" />
	</legend>
	<form:form id="productForm"
		commandName="<%=EditProductController.PRODUCT%>" method="post">
		<form:hidden path="id" />
		<table border="0">
			<tr>
				<td><form:label path="description" for="description">
						<fmt:message key="product.desc" />:</form:label></td>
				<td>
					<div class="form-error-message">
						<form:errors path="description" />
					</div> <form:input id="description" path="description"
						cssErrorClass="form-error-field" />
				</td>
			</tr>
			<tr>
				<td><form:label path="price" for="price">
						<fmt:message key="product.price" />:</form:label></td>
				<td>
					<div class="form-error-message">
						<form:errors path="price" />
					</div> <form:input id="price" path="price"
						cssErrorClass="form-error-field" />&nbsp;<fmt:message key="euro" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tableFooter"></td>
			</tr>
			<tr>
				<td><a id="back" href="inventory.do"> <img
						alt="<fmt:message key='back'/>" title="<fmt:message key='back'/>"
						src="img/icons/back.png" />
				</a></td>
				<td class="alignRight"><c:if test="${not empty product.id}">
						<a id="delete"
							href="deleteProduct.do?<%=EditProductController.PRODUCT%>=${product.id}">
							<img alt="<fmt:message key='delete'/>"
							title="<fmt:message key='delete'/>" src="img/icons/remove.png" />
						</a>
					</c:if> <a id="accept"> <img alt="<fmt:message key='accept'/>"
						title="<fmt:message key='accept'/>" src="img/icons/accept.png" />
				</a></td>
			</tr>
		</table>
	</form:form>
</fieldset>

<%@ include file="/include/footer.jsp"%>