<%-- 
    Document   : Perfil
    Created on : 11 sept 2024, 2:21:23
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        
    </head>
    <body>
        <jsp:include page="/includes/Headers.jsp"/>
        <%
            //HttpSession session = request.getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute("userName");
                if (userName != null) {
                    out.println("Bienvenido, " + userName);
                } else {
                    out.println("No estás logueado.");
                }
            } else {
                out.println("No estás logueado.");
            }
        %>
        <h1>Hello World!</h1>
    </body>
    <jsp:include page="/includes/Footer.jsp"/>
</html>
