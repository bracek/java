<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
<%@ page import="sk.mka.web.controller.EditProductController"%>
<%@ page import="sk.mka.web.controller.ApplicationCodes"%>
<%@ page import="sk.mka.web.controller.DeleteProductController"%>
<script type="text/javascript" src="dwr/interface/DwrProduct.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>


<script language="JavaScript">
    //Registering onchange event
    $(function(){
        $("img[@id^='delete']").click(function() {
            var id = this.id.substring(6);
            DwrProduct.deleteProduct(id);

            //refreshing table
            $('#product'+id).remove();
        });
    });
</script>

<h3>
	<fmt:message key="product.title" />
</h3>
<table border="0" cellspacing="3" cellpadding="1">
	<tr>
		<th>id</th>
		<th><fmt:message key="product.desc" /></th>
		<th class="alignRight"><fmt:message key="product.price" /></th>
		<th><fmt:message key="form.edit" /></th>
		<th><fmt:message key="form.delete" /></th>
	</tr>
	<tr>
		<td colspan="5" class="tableHeader" />
	</tr>
	<c:if test="${!empty products}">
		<c:forEach items="${products}" var="product" varStatus="status">
			<tr id="product${product.id}">
				<td>${product.id}</td>
				<td><c:out value="${product.description}" /></td>
				<td class="alignRight"><c:out value="${product.price}" />&nbsp;<fmt:message
						key="euro" /></td>
				<td class="alignRight"><a
					href="editProduct.do?<%=EditProductController.PRODUCT%>=${product.id}">
						<img alt="<fmt:message key='edit'/>"
						title="<fmt:message key='edit'/>" src="img/icons/edit.png" />
				</a></td>
				<td class="alignRight"><a
					href="deleteProduct.do?<%=DeleteProductController.PRODUCT%>=${product.id}">
						<img alt="<fmt:message key='delete'/>"
						title="<fmt:message key='delete'/>" src="img/icons/delete.png" />
				</a></td>
			</tr>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="5" class="tableFooter"><a href="editProduct.do">
				<img alt="<fmt:message key='new'/>" title="<fmt:message key='new'/>"
				src="img/icons/add.png" />
		</a></td>
	</tr>
</table>
</body>
</html>