<%-- 
    Document   : RepoComentario
    Created on : 19 sept 2024, 10:09:36
    Author     : xavi
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Reporte de Comentarios</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1>Reporte de Comentarios</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nombre de Usuario</th>
                    <th>Comentario</th>
                    <th>Nombre de la Revista</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="com" items="${listaComentarios}">
                    <tr>
                        <td>${com.userCom}</td>
                        <td>${com.contenido}</td>
                        <td>${com.nomRevista}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="nuevaRevistasSvt" class="btn btn-primary">Volver al Inicio</a>
        
    </div>
</body>
</html>
