<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="sk.mka.web.controller.OsobaController"%>

<h1><fmt:message key="person.form.title"/></h1>
<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="person">
        <h2>Errors</h2>
        <div class="formerror">
            <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
            </ul>
        </div>
    </spring:hasBindErrors>
</div>


<c:url var="url" value="/save/person.do" />
<form:form action="${url}" commandName="<%=OsobaController.OSOBA%>" method="post">
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
            <label for="firstName"><fmt:message key="person.form.firstName"/>:</label>
            <span class="input"><form:input path="firstName" /></span>
        </div>
        <div class="form-row">
            <label for="lastName"><fmt:message key="person.form.lastName"/>:</label>
            <span class="input"><form:input path="lastName" /></span>
        </div>
        <div class="form-row">
            <label for="login"><fmt:message key="person.form.login"/>:</label>
            <span class="input"><form:input path="login" /></span>
        </div>
        <div class="form-row">
            <label for="password"><fmt:message key="person.form.password"/>:</label>
            <span class="input"><form:password path="password" /></span>
        </div>
        <div class="form-row">
            <label for="password-repete"><fmt:message key="person.form.password"/>:</label>
            <span class="input"><form:password path="password_repeat" /></span>
        </div>





        <div class="form-buttons">
            <div class="button"><input type="submit" value="<fmt:message key="button.save"/>" /></div>
        </div>
    </fieldset>
</form:form>