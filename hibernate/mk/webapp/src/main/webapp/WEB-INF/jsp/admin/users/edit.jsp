<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="sk.mka.web.controller.UsersController"%>

<h1><fmt:message key="users.edit.title.head" /></h1>
<fieldset>
    <legend><fmt:message key="users.edit.legend" /></legend>
    <c:url var="url" value="/users/save.do" />
    <form:form action="${url}" commandName="<%=UsersController.USERS%>" method="post">
        <form:hidden path="username" />
        <table border="1">

            afea
            <tr>
                <td>
                    <form:label path="username" for="username">
                    <fmt:message key="form.name" />:</form:label>
                </td>
                <td>
                    <div class="form-error-message"><form:errors path="username"/></div>
                    <form:input id="username" path="username" cssErrorClass="form-error-field"/>
                </td>
            </tr>

            <tr><td class="tableFooter"></td></tr>
            <tr>
                <td>
                    <a id="back" href="list.do"> <img alt="<fmt:message key='back'/>" title="<fmt:message key='back'/>" src="../img/icons/back.png" /> </a>
                </td>
                <td>
                    <div class="form-buttons">
                        <div class="button"><input type="image" src="../img/icons/accept.png" value="<fmt:message key="button.save"/>" /></div>
                    </div>
                </td>

            </tr>
        </table>
    </form:form>
</fieldset>

