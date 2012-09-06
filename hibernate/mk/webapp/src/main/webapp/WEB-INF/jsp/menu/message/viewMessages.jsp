<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ page import="sk.mka.web.controller.EditMessageController"%>
<%@ page import="sk.mka.web.controller.ApplicationCodes"%>
<%@ page import="sk.mka.web.controller.DeleteMessageController"%>
<script type="text/javascript" src="dwr/interface/DwrProduct.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>


<h3><fmt:message key="message.heading"/></h3>
<table border="0" cellspacing="3" cellpadding="1">
    <tr>
        <th/>
        <th><fmt:message key="message.userfrom"/></th>
        <th class="alignRight"><fmt:message key="message.message"/></th>
        <th/>
        <th/>
    </tr>
    <tr>
        <td colspan="5" class="tableHeader"/>
    </tr>
    <c:if test="${!empty messages}">
        <c:forEach items="${messages}" var="message" varStatus="status">
            <tr id="product${message.id}">
                <td class="alignRight">${message.id}</td>
                <td class="alignRight"><c:out value="${message.userfrom}"/></td>
                <td class="alignRight"><c:out value="${message.message}"/></td>

                <td class="alignRight">
                    <a href="../../menu/message/editMessage.do?id=${message.id}">
                        <img alt="<fmt:message key='edit'/>" title="<fmt:message key='edit'/>" src="../../img/icons/edit.png"/>
                    </a>
                </td>

                <td class="alignRight">

                    <a href="../../menu/message/deleteMessage.do?<%=ApplicationCodes.MESSAGE.COMMAND%>=${message.id}">
                        <img alt="<fmt:message key='delete'/>" title="<fmt:message key='delete'/>" src="../../img/icons/delete.png"/>
                    </a>

                </td>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <td colspan="5" class="tableFooter">
            <a href="../../menu/message/editMessage.do">
                <img alt="<fmt:message key='new'/>" title="<fmt:message key='new'/>" src="../../img/icons/add.png"/>
            </a>
        </td>
    </tr>
</table>
</body>
</html>