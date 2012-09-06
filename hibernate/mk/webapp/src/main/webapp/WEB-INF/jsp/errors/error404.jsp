<%@ page import="org.springframework.security.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
  <head><title>Skillnet - Error 404</title></head>
<body>
  customized 404

  <%
       String attributes = "";
      try{ attributes = SecurityContextHolder.getContext().getAuthentication().getDetails().toString(); }
      catch (Exception e) {

      }

   %>
   <strong>User Attributes : </strong><br/>
  <%= attributes %>

</body>
</html>