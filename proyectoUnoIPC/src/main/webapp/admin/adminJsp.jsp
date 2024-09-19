<%-- 
    Document   : adminJsp
    Created on : 15 sept 2024, 18:51:20
    Author     : xavi
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
    </head>
    <body>
        <jsp:include page="/includes/Headers.jsp"/>

        <div class="barra-izquierda">
            <jsp:include page="/includes/barraAI.jsp"/>
        </div>

        <div class="container">
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Actualizar Precios de Anuncios</h1>
                    <form action="${pageContext.servletContext.contextPath}/actualizarPrecios" method="POST">
                        <p class="col-md-8 fs-4">Seleccione el tipo de anuncio, el tiempo y su nuevo precio:</p>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tipoAnuncio" name="tipoAnuncio" required >
                            <option value="" disabled selected>Seleccione un tipo de anuncio</option>
                            <option value="TEXTO">TEXTO</option>
                            <option value="IMGTXT">TEXTO E IMAGEN</option>
                            <option value="VIDEO">VIDEO</option>
                        </select>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tiempoAnuncio" name="tiempoAnuncio" required >
                            <option value="" disabled selected>Seleccione un periodo</option>
                            <option value="precio_un_dia">1 día</option>
                            <option value="precio_tres_dias">3 días</option>
                            <option value="precio_una_semana">1 semana</option>
                            <option value="precio_dos_semanas">2 semanas</option>
                        </select>
                        <label for="precioNuevo" class="sr-only">Nuevo Precio:</label>
                        <input id="precioNuevo" name="precioNuevo" type="number" min="0" step="0.01" class="form-control" placeholder="Precio nuevo" required >
                        <br>
                        <button class="btn btn-primary btn-lg" type="submit">Actualizar precio</button>
                    </form>
                </div>
            </div>

                    <br><!-- comment -->
                    <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                        <div class="container-fluid py-5">
                            <h1 class="display-5 fw-bold">Costo de Revista</h1>
                            <form action="${pageContext.servletContext.contextPath}/precioRevistaSvt" method="post">
    <label for="revista" class="form-label">Seleccione la revista:</label>
    <select class="form-select form-select-lg mb-3" id="revista" name="revista" required>
        <option value="" disabled selected>Seleccione una revista</option>
        <c:forEach var="revista" items="${revistas}">
            <option value="${revista.codigo}">${revista.nombreRevista}</option>
        </c:forEach>
    </select>
    <br><!-- comment -->

    <label for="precio" class="form-label">Precio de la revista por día</label>
    <input id="precioRevista" name="precioRevista" type="number" min="1" step="0.01" class="form-control" placeholder="Precio de la revista" required>

    <br><!-- comment -->
    <button class="btn btn-primary btn-lg" type="submit">Actualizar precio</button>
</form>
                        </div>
                    </div>
                    <br><!-- comment -->
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Reportes</h1>

                    <p class="col-md-8 fs-4">Seleccione el reporete que desea ver:</p>
                    <br><!-- comment -->
                    <table>
                        <tr>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de ganancias</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de anuncios comprados</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de las 5 revistas más populares</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de las 5 revistas más comentadas</button> </th>
                        </tr>
                    </table>

                </div>
            </div>
            <br><!-- comment -->
        </div>
        <div class="barra-derecha">
            <jsp:include page="/includes/barraAD.jsp"/>
        </div>
        <!-- Footer -->
        <jsp:include page="/includes/Footer.jsp"/>
    </body>
</html>
