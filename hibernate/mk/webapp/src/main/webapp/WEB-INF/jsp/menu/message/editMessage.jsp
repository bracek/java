<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/jsp/include/header.jsp"%>
<%@ page import="sk.mka.web.controller.ApplicationCodes"%>
<%@ page import="sk.mka.web.controller.EditMessageController"%>
<script language="JavaScript">
    $( function() {
        $('#accept').click( function() {
            $('#messageForm').submit();
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


<%@ include file="/WEB-INF/jsp/include/header.jsp"%>

<h3><fmt:message key="editMessage.heading" /></h3>
<fieldset>
    <legend><fmt:message key="message" /></legend>

    <c:url var="url" value="/menu/message/editMessage.do" />
    <form:form action="${url}" id="messageForm" commandName="<%=ApplicationCodes.MESSAGE.COMMAND%>" method="post">
        <form:hidden path="id" />
        <table border="0">
            <tr>
                <td>
                    <form:label path="message" for="message">
                    <fmt:message key="message.message" />:</form:label>
                </td>
                <td>
                    <div class="form-error-message"><form:errors path="message"/></div>
                    <form:input id="message" path="message" cssErrorClass="form-error-field"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="userfrom" for="userfrom">
                <fmt:message key="message.userfrom" />:</form:label></td>
                <td>
                    <div class="form-error-message"><form:errors path="userfrom"/></div>
                    <form:input id="userfrom" path="userfrom" cssErrorClass="form-error-field"/>
                </td>
            </tr>
            <tr><td colspan="2" class="tableFooter"></td></tr>
            <tr>
                <td>
                    <a id="back" href="viewMessages.do"> <img alt="<fmt:message key='back'/>" title="<fmt:message key='back'/>" src="../../img/icons/back.png" /> </a>
                </td>
                <td class="alignRight">
                    <c:if test="${not empty message.id}">
                        <a id="delete" href="deleteMessage.do?<%=ApplicationCodes.MESSAGE.COMMAND%>=${message.id}"> <img alt="<fmt:message key='delete'/>" title="<fmt:message key='delete'/>" src="img/icons/remove.png" /> </a>
                    </c:if>
                    <input   src="../../img/icons/accept.png" type="image" value="<fmt:message key="button.save"/>" ></input>
                </td>
            </tr>
        </table>
    </form:form>
</fieldset>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>


