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
                    <th>Fecha de suscripcion</th>
                    <th>Nombre de la Revista</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="com" items="${subs}">
                    <tr>
                        <td>${com.userLec}</td>
                        <td>${com.fecha}</td>
                        <td>${com.nomRevista}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="nuevaRevistasSvt" class="btn btn-primary">Volver al Inicio</a>
        
    </div>
</body>
</html>
