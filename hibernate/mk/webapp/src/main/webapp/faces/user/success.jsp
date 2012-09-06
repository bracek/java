package faces.user;

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congratulations</title>
    </head>
    <body>

        <h1>Congratulations</h1>
        <f:view>
            <h:form>
                <p>You've successfully registered with jAstrologer.</p>
                <p>Your name is <h:outputText value="#{UserBean.name}" /></p>
                <p>Your birthday is <h:outputText value="#{UserBean.birthday}" /></p>
            </h:form>
        </f:view>

    </body>
</html>